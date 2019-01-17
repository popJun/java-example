package upload.example.demo.service.impl;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import upload.example.demo.service.UploadService;
import upload.example.demo.util.FileUtil;
import upload.example.demo.util.TarUtil;

import java.io.*;

@Service
public class UploadServiceImpl implements UploadService{
    private final int BYTES_PER_SLICE = 5 * 1024 * 1024;
    @Override
    public int checkSlice(String md5,String fileName, String index, String total,String savePath) {
        int result = 1;
        //临时文件目录
        String tempDir = savePath + "/temp/" + fileName;
        String filePar = fileName + "_" + index + ".part";
        File temDirFile = new File(tempDir);
        //第一种情况：没有临时目录则无需进行下一步分片的校验
        if (!temDirFile.exists()) {
            return 2;
        }
        if (temDirFile.listFiles().length == 0) {
            return 2;
        }
        File[] files = temDirFile.listFiles();
        //第二种情况临时目录分片总数>当前文件的分片数
        //可以直接删除该文件夹下的临时文件
        if (files.length > Integer.parseInt(total)) {
            try {
                FileUtils.deleteDirectory(temDirFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //该临时文件夹下的文件
        FileInputStream fileInputStream = null;
        try {
            for (File file : files) {
                //如果存在名字和当前上传分片的名字相同则比对md5值
                if (file.getName().equals(filePar)) {
                    fileInputStream = new FileInputStream(file);
                    String temFileMd5 = TarUtil.getMd5(fileInputStream);//"获取文件md5"
                    if (temFileMd5.length() != 32) {//不足32位开头需要补0
                        for (int i = 1; i <= (32 - temFileMd5.length()); i++) {
                            temFileMd5 = "0" + temFileMd5;
                        }
                    }
                    if (temFileMd5.equals(md5)) {
                        result = 0;
                        break;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    @Override
    public int uploadProduce(MultipartFile file, String fileName, Integer index,String savePath) {
        int result = 0;
        //格式化fileName
        fileName = fileName.trim().substring(0, fileName.indexOf(".tar.gz"));
        //避免相同时间下不同用户上传同一个文件
        String tempDir = savePath + "/temp/"+ fileName;
        //创建临时路径
        FileUtil.createDirectory(new File(tempDir));
        String filePar = tempDir + "/" + fileName + "_" + index + ".part";
        File tempDirFile = new File(tempDir);
        try {
            //保存分片文件
            FileUtil.saveFile(file.getInputStream(), filePar);
        } catch (IOException e) {
            e.printStackTrace();
        }
        synchronized (this) {
            result = tempDirFile.listFiles().length;
        }
        return result;
    }

    @Override
    public int combineFile(String fileName, String total,String savePath) {
        RandomAccessFile randomAccessFile = null;
        //避免相同时间下不同用户上传同一个文件
        fileName = fileName.trim().substring(0, fileName.indexOf(".tar.gz"));
        String tempDir = savePath + "/temp/" + fileName;
        try {
            // 得到 destTempFile 就是最终的文件
            File destTempFile = new File(savePath, fileName + ".tar.gz");
            if (!destTempFile.exists()) {
                File fileDir = new File(savePath);
                fileDir.mkdirs();
                destTempFile.createNewFile();
            }
            //合并分片
            randomAccessFile = new RandomAccessFile(destTempFile, "rw");
            for (int i = 0; i < Integer.parseInt(total); i++) {
                File partFile = new File(tempDir, fileName + "_" + i + ".part");
                byte[] bytes = FileUtil.getByte(partFile);
                //从哪个位置开始添加
                randomAccessFile.seek(i * BYTES_PER_SLICE);
                randomAccessFile.write(bytes);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (randomAccessFile != null) {
                try {
                    randomAccessFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //上传成功后还需要在数据库保存相应的信息
        //删除临时文件 --合并文件需要时间，所以删除临时文件固定放在最后一步。
        try {
            File tempDirFile = new File(tempDir);
            FileUtils.deleteDirectory(tempDirFile);
        } catch (IOException e) {
        }
        return 0;
    }


}
