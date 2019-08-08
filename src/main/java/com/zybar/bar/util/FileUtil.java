package com.zybar.bar.util;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 刘佳昇
 * @Date 2019/8/8 15:29
 */

@Component
public class FileUtil {

    /**
     * 文件上传
     *
     * @param file
     * @return
     * flag 1-pdf 2-img 3-mp3
     *
     */
    public String fileUpload(MultipartFile file,Integer flag) {
        //判断文件是否为空
        if (file.isEmpty()) {
            return "-1";
        }

        String fileName = file.getOriginalFilename();

        String prePath ="";
        switch(flag){
            case 1:
//                pdf
                prePath="D:/working/bar/src/main/resources/static/file/pdf/";
                break;
            case 2:
//                img
                prePath="D:/working/bar/src/main/resources/static/file/img/";
                break;
            case 3:
//                mp3
                prePath="D:/working/bar/src/main/resources/static/file/mp3/";
                break;
        }
        //加个时间戳，尽量避免文件名称重复
        String path = prePath + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + fileName;
        File dest = new File(path);

        //判断文件是否已经存在
        if (dest.exists()) {
            return "-2";
        }

        //判断文件父目录是否存在
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdir();
        }

        try {
            file.transferTo(dest); //保存文件
        } catch (IOException e) {
            return "-3";
        }

        return path;
    }

    /**
     * 文件下载
     *
     * @param response
     * @param filePathName
     * @return
     */
    public String downloadFile(HttpServletResponse response, String filePathName) {
        File file = new File(filePathName);
        if (!file.exists()) {
            return "-1";
        }

        response.reset();
        response.setHeader("Content-Disposition", "attachment;fileName=" + filePathName);

        try {
            InputStream inStream = new FileInputStream(filePathName);
            OutputStream os = response.getOutputStream();

            byte[] buff = new byte[1024];
            int len = -1;
            while ((len = inStream.read(buff)) > 0) {
                os.write(buff, 0, len);
            }
            os.flush();
            os.close();

            inStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            return "-2";
        }

        return "0";
    }

}
