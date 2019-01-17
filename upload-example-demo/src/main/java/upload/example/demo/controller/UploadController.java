package upload.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import upload.example.demo.service.UploadService;

import java.util.HashMap;
import java.util.Map;

@Controller
public class UploadController {
     private static String savePath = "F://upload";
     @Autowired
     private UploadService uploadService;
     /**
     * 用于判断分片
     *
     * @return 0代表该分片后台已经存在不需要上传，1代表需要上传,2代表所有分片均无需在进行校验
     */
    @RequestMapping(value = "/checkSlice", method = RequestMethod.POST, produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public int checkSlice(String md5, String fileName, String index, String total) {
        int result = uploadService.checkSlice(md5, fileName, index, total, savePath);
        return result;
    }

    /**
     * 用于分片上传
     *
     * @param file
     * @param fileName
     * @param index
     * @return
     */
    @RequestMapping(value = "/uploadProduce", method = RequestMethod.POST, produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public int uploadProduce(@RequestParam(value = "fileUpload") MultipartFile file, String fileName, Integer index) {
        System.out.println("11");
        int result = uploadService.uploadProduce(file, fileName, index, savePath);
        return result;
    }

    /**
     * 合并文件
     *
     * @return
     */
    @RequestMapping("/combineFile")
    @ResponseBody
    public int combineFile(String fileName,String total) {
        int result = uploadService.combineFile(fileName, total, savePath);
        return result;
    }
    /**
     * 用于校验升级包 --将文件md5/名称上传和数据库文件md5/名称对比
     *
     * @return
     */
    @RequestMapping(value = "/checkPack", method = RequestMethod.POST, produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public Map checkPack(String fileName, String fileMd5) {
        Map map = new HashMap();
        map.put("type",4);
        return map;
    }
}
