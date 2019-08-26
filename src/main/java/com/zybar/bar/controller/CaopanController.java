package com.zybar.bar.controller;

import com.zybar.bar.dao.CaopanMapper;
import com.zybar.bar.model.Caopan;
import com.zybar.bar.util.FileUtil;
import com.zybar.bar.util.PageCheck;
import com.zybar.bar.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author 刘佳昇
 * @Date 2019/8/11 17:25
 */
@RestController
public class CaopanController {
    @Autowired
    CaopanMapper caopanMapper;

    @Autowired
    FileUtil fileUtil;

    /**
     * 插入操盘经典
     *
     * @param
     * @param caopan
     * @return
     */
    @PostMapping("/insertCaopan")
    public Result insertCaopan(Caopan caopan) {
        caopan.setUpdateTime(fileUtil.getCurrTime());
        if (caopanMapper.insertSelective(caopan) > 0) {
            return Result.createSuccessResult(caopan);
        } else {
            return Result.createByFailure("数据库操作失败，请联系管理员");
        }

    }


    /**
     * 上传操盘经典pdf
     * @param file
     * @return
     */
    @PostMapping("/uploadCaopan")
    public Result uploadCaopan(@RequestParam(name = "file") MultipartFile file) {
        String path = fileUtil.fileUpload(file, 1);
        if (!path.equals("-1") && !path.equals("-2") && !path.equals("-3")) {
            return Result.createSuccessResult(path);
        } else {
            return Result.createByFailure("文件上传失败");
        }
    }

    /**
     * 查询所有操盘经典
     *
     * @return
     */
    @PostMapping("/getAllCaopan")
    public Result getAllCaoPan(String name, int page, int limit) {
        page = PageCheck.checkPage(page);
        limit = PageCheck.checkLimit(limit);
        int start = PageCheck.calculateStart(page, limit);
        List<Caopan> caopans = caopanMapper.selectAll(name, start, limit);
        if (caopans.size() > 0) {
            return Result.createSuccessResult(caopanMapper.getCount(name), caopans);
        } else {
            return Result.createByFailure("没有数据");
        }
    }

    /**
     * 用户端获取
     * @return
     */
    @PostMapping("/userGetAllCaopan")
    public Result getAllCaoPan() {
        int page = 1;
        int limit =1000;
        String name = null;
        page = PageCheck.checkPage(page);
        limit = PageCheck.checkLimit(limit);
        int start = PageCheck.calculateStart(page, limit);
        List<Caopan> caopans = caopanMapper.selectAll(name, start, limit);
        if (caopans.size() > 0) {
            return Result.createSuccessResult(caopanMapper.getCount(name), caopans);
        } else {
            return Result.createByFailure("没有数据");
        }
    }

    /**
     * 删除操盘经典
     *
     * @param caopanId
     * @return
     */
    @PostMapping("/deleteCaopan")
    public Result deleteCaopan(@RequestParam(name = "caopanId") Integer caopanId) {
        try {
            Caopan caopan = caopanMapper.selectByPrimaryKey(caopanId);
            String pdfUrl = caopan.getPdfUrl();
            String isDelete = fileUtil.deleteFile(pdfUrl);
            if (isDelete.equals("0") || isDelete.equals("-2")) {
                int col = caopanMapper.deleteByPrimaryKey(caopanId);
                if (col > 0) {
                    return Result.createSuccessResult();
                } else {
                    return Result.createByFailure("删除失败,请重试");
                }
            } else {
                return Result.createByFailure("文件删除失败，请重试");
            }
        } catch (Exception e) {
            return Result.createByFailure("出现错误");
        }
    }


}
