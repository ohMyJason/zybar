package com.zybar.bar.controller;

import com.zybar.bar.dao.PaperMapper;
import com.zybar.bar.model.Paper;
import com.zybar.bar.service.PaperService;
import com.zybar.bar.util.FileUtil;
import com.zybar.bar.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author 刘佳昇
 * @Date 2019/8/8 11:20
 * 机构研报
 */

//加了这个就不用加Responsebody了
@RestController
public class PaperController {

    @Autowired
    FileUtil fileUtil;

    @Autowired
    PaperService paperService;

    @Autowired
    PaperMapper paperMapper;

    /**
     * 上传pdf文件
     * @param file 文件对象
     * @param paper  paper实体对象，主要为了得到文件名也就是标题
     * @return
     */
    @PostMapping("/uploadPaperFile")
    @ResponseBody
    public Result uploadFile(@RequestParam("fileName") MultipartFile file, Paper paper) {
        String filePath = fileUtil.fileUpload(file,1);
        if (!filePath.equals("-1")&&!filePath.equals("-2")&&!filePath.equals("-3")){

            if (paperService.insertPaper(filePath,paper.getPaperName())==0){
                return Result.createSuccessResult();
            }else {
                return Result.createByFailure("上传失败，请联系管理员");
            }
        }else{
            return Result.createByFailure("pdf文件上传错误，请重试。");
        }

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



    /**
     * 返回所有的paper文件
     * @return
     */
    @PostMapping("/getAllPaper")
    public Result getAllPaper(){
        return  Result.createSuccessResult(paperService.selectAllPaper());
    }

    /**
     * 待写，文件删除
     */
    @PostMapping("/deletePaper")
    public Result deletePaper(@RequestParam(name = "paperId") Integer paperId){
        try {
            Paper paper = paperMapper.selectByPrimaryKey(paperId);
            fileUtil.deleteFile(paper.getPdfUrl());
            int col = paperMapper.deleteByPrimaryKey(paperId);
            if (col>0){
                return Result.createSuccessResult();
            }else {
                return Result.createByFailure("删除失败");
            }
        }catch (Exception e){
            return Result.createByFailure("出错");
        }
    }

}
