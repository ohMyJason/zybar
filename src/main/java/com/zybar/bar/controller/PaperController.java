package com.zybar.bar.controller;

import com.zybar.bar.model.Paper;
import com.zybar.bar.service.PaperService;
import com.zybar.bar.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author 刘佳昇
 * @Date 2019/8/8 11:20
 */

//加了这个就不用加Responsebody了
@RestController
public class PaperController {

    @Autowired
    FileUtil fileUtil;

    @Autowired
    PaperService paperService;

    /**
     * 上传pdf文件
     * @param file 文件对象
     * @param paper  paper实体对象，主要为了得到文件名也就是标题
     * @return
     */
    @PostMapping("/uploadPaperFile")
    @ResponseBody
    public String uploadFile(@RequestParam("fileName") MultipartFile file, Paper paper) {
        String filePath = fileUtil.fileUpload(file);
        return paperService.insertPaper(filePath,paper.getPaperName());
    }

    /**
     * 下载文件
     * @param response
     * @param filePathName
     * @return
     */
    @PostMapping("/downloadPaperFile")
    @ResponseBody
    public String downloadFile(HttpServletResponse response, @RequestParam("fileName") String filePathName) {
        return fileUtil.downloadFile(response, filePathName);
    }

    @PostMapping("/getAllPaper")
    public List getAllPaper(){
        return  paperService.selectAllPaper();
    }

}
