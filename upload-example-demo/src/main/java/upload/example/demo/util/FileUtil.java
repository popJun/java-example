package upload.example.demo.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.*;

/**
 * Created by zhongzh on 14-1-24.
 */
public class FileUtil {

    public static void deleteDirectory(String sPath) {
        File dirFile = new File(sPath);
        if (dirFile.exists() && dirFile.isDirectory()) {
            File[] files = dirFile.listFiles();
            for (File file : files) {
                if (file.isFile() && file.exists()) {
                    file.delete();
                }
            }
            dirFile.delete();
        }
    }

    public static void delete(String sPath) {
        File dirFile = new File(sPath);
        if (dirFile.exists() && dirFile.isDirectory()) {
            File[] files = dirFile.listFiles();
            for (File file : files) {
                if (file.exists() && file.isDirectory()) {
                    delete(file.getAbsolutePath());
                }
                if (file.isFile() && file.exists()) {
                    file.delete();
                }
            }
            dirFile.delete();
        }
    }

    public static void createDirectory(File file) {
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    public static byte[] getByte(File file) throws Exception {
        byte[] bytes = null;
        if (file != null) {
            InputStream is = new FileInputStream(file);
            int length = (int) file.length();
            if (length > Integer.MAX_VALUE) {
                return null;
            }
            bytes = new byte[length];
            int offset = 0;
            int numRead = 0;
            while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
                offset += numRead;
            }
            if (offset < bytes.length) {
                return null;
            }
            is.close();
        }
        return bytes;
    }



    public static boolean deleteFileOrDirectory(File dirFile) {
        // 如果dir对应的文件不存在，则退出
        if (!dirFile.exists()) {
            return false;
        }

        if (dirFile.isFile()) {
            return dirFile.delete();
        } else {

            for (File file : dirFile.listFiles()) {
                deleteFileOrDirectory(file);
            }
        }

        return dirFile.delete();
    }

    public static File saveFile(InputStream inputStream, String targetPath){
        File temp = null;
        temp = new File(targetPath);
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try{
            bis = new BufferedInputStream(inputStream);
            bos = new BufferedOutputStream(new FileOutputStream(temp));                            //把文件流转为文件，保存在临时目录
            int len = 0;
            byte[] buf = new byte[10*1024];                                                    //缓冲区
            while((len=bis.read(buf)) != -1){
                bos.write(buf, 0, len);
            }
            bos.flush();
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            try {
                if(bos!=null) bos.close();
                if(bis!=null) bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return temp;
    }

    public static void writeOutStream(String sourcePath, OutputStream os) throws Exception{
        File file = new File(sourcePath);
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(new FileInputStream(file));
            bos = new BufferedOutputStream(os);
            int len = 0;
            byte[] buf = new byte[10*1024];                                                    //缓冲区
            while((len=bis.read(buf)) != -1){
                bos.write(buf, 0, len);
            }
            bos.flush();
        }finally{
            try {
                if(bos!=null) bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(bis!=null) bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void copyFile(File fromFile,File toFile) throws IOException{
//        FileInputStream ins = new FileInputStream(fromFile);
//        FileOutputStream out = new FileOutputStream(toFile);
        BufferedInputStream ins = new BufferedInputStream(new FileInputStream(fromFile));
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(toFile));
        byte[] b = new byte[1024];
        int n=0;
        while((n=ins.read(b))!=-1){
            out.write(b, 0, n);
        }

        ins.close();
        out.close();
    }

    public static void copyFile(String sourceFilePath, String targetFilePath) throws Exception{
        File sourceFile = new File(sourceFilePath);
        File targetFile = new File(targetFilePath);
        if (sourceFile.exists() && sourceFile.isFile()){
            BufferedInputStream ins = new BufferedInputStream(new FileInputStream(sourceFile));
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(targetFile));
            byte[] b = new byte[1024];
            int n=0;
            while((n=ins.read(b))!=-1){
                out.write(b, 0, n);
            }

            ins.close();
            out.close();
        }
    }

    public static ResponseEntity<byte[]> buildResponseEntity(File file) throws IOException {
        byte[] body = null;
        //获取文件
        InputStream is = new FileInputStream(file);
        byte[] content = new byte[is.available()];
        is.read(content);
        body = new byte[content.length];
        for (int i = 0; i < content.length; i++) {
            body[i] = content[i];
        }
        HttpHeaders headers = new HttpHeaders();
        //设置文件类型
        headers.add("Content-Disposition", "attchement;filename=" + file.getName());
        //设置Http状态码
        HttpStatus statusCode = HttpStatus.OK;
        //返回数据
        ResponseEntity<byte[]> entity = new ResponseEntity<byte[]>(body, headers, statusCode);
        return entity;
    }
    /**文件重命名
     * @param path 文件目录
     * @param oldname  原来的文件名
     * @param newname 新文件名
     */
    public static void renameFile(String path,String oldname,String newname){
        if(!oldname.equals(newname)){//新的文件名和以前文件名不同时,才有必要进行重命名
            File oldfile=new File(path+"/"+oldname);
            File newfile=new File(path+"/"+newname);
            if(!oldfile.exists()){
                return;//重命名文件不存在
            }
            if(newfile.exists())//若在该目录下已经有一个文件和新文件名相同，则不允许重命名
                System.out.println(newname+"已经存在！");
            else{
                oldfile.renameTo(newfile);
            }
        }else{
            System.out.println("新文件名和旧文件名相同...");
        }
    }
}