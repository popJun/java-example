package upload.example.demo.util;

import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * 为系统升级服务的 tar.gz解压工具
 */
public class TarUtil {
    /**
     * 获取指定文件的md5
     * @param filePath 文件绝对路径
     * @return MD5值
     */
    public static String getMd5(String filePath){
        File file = new File(filePath);
        BigInteger bi = null;
        FileInputStream fi = null;
        try {
            fi = new FileInputStream(file);
            bi = null;
            byte[] buffer = new byte[1024 * 4];
            int len = 0;
            MessageDigest md = MessageDigest.getInstance("MD5");
            while ( (len = fi.read(buffer)) != -1){
                md.update(buffer, 0, len);
            }
            byte[] b = md.digest();
            bi = new BigInteger(1, b);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (fi != null){
                try {
                    fi.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if (bi == null) {
            return "";
        }

        return bi.toString(16);
    }
    public static String getMd5(InputStream inputStream){
        BigInteger bi = null;
        BufferedInputStream fi = null;
        try {
            fi = new BufferedInputStream(inputStream);
            bi = null;
            byte[] buffer = new byte[1024 * 4];
            int len = 0;
            MessageDigest md = MessageDigest.getInstance("MD5");
            while ( (len = fi.read(buffer)) != -1){
                md.update(buffer, 0, len);
            }
            byte[] b = md.digest();
            bi = new BigInteger(1, b);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (fi != null){
                try {
                    fi.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if (bi == null) {
            return "";
        }

        return bi.toString(16);
    }

    public static class CachePackageContent {
        private String version;
        private String websiteAndRule;
        private String cacheptProperties;
        private String speedConfig;

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getWebsiteAndRule() {
            return websiteAndRule;
        }

        public void setWebsiteAndRule(String websiteAndRule) {
            this.websiteAndRule = websiteAndRule;
        }

        public String getCacheptProperties() {
            return cacheptProperties;
        }

        public void setCacheptProperties(String cacheptProperties) {
            this.cacheptProperties = cacheptProperties;
        }

        public String getSpeedConfig() {
            return speedConfig;
        }

        public void setSpeedConfig(String speedConfig) {
            this.speedConfig = speedConfig;
        }
    }


}
