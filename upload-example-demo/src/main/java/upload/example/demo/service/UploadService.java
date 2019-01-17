package upload.example.demo.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
    /**
     * 用于判断分片
     *
     * @return 0代表该分片后台已经存在不需要上传，1代表需要上传,2代表所有分片均无需在进行校验
     */
    int checkSlice(String md5,String fileName, String index, String total,String savePath);
    /**
     * 用于分片上传
     *
     */
    int uploadProduce( MultipartFile file, String fileName, Integer index,String savePath);

    /**
     * 用于分片合并
     * @return
     */
    int combineFile(String fileName,String total,String savePath);
}
