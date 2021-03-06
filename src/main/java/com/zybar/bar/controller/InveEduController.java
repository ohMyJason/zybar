package com.zybar.bar.controller;

import com.zybar.bar.dao.InveEduMapper;
import com.zybar.bar.model.InveEdu;
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
 * @Date 2019/8/16 0:28
 */
@RestController
public class InveEduController {
    @Autowired
    InveEduMapper inveEduMapper;

    @Autowired
    FileUtil fileUtil;

    /**
     * 添加投资者教育
     *
     * @param inveEdu
     * @return
     */
    @PostMapping("/insertInveEdu")
    public Result insertInveEdu(InveEdu inveEdu) {
        try {
            int col = inveEduMapper.insertSelective(inveEdu);
            if (col > 0) {
                return Result.createSuccessResult();
            } else {
                return Result.createByFailure("上传错误-003,请联系管理员");
            }
        } catch (Exception e) {
            return Result.createByFailure("上传错误-002，请联系管理员");
        }
    }


    /**
     * 上传pdf
     * @param file
     * @return
     */
    @PostMapping("/uploadInveEdu")
    public Result uploadInveEdu(@RequestParam(name = "file") MultipartFile file) {
        try {
            String path = fileUtil.fileUpload(file, 1);
            return Result.createSuccessResult(path);
        } catch (Exception e) {
            return Result.createByFailure("上传错误-002，请联系管理员");
        }
    }


    /**
     * 获取全部投资者教育
     *
     * @return
     */
    @PostMapping("/getAllInveEdu")
    public Result getAllInveEdu(String name, int page, int limit) {
        try {
            page = PageCheck.checkPage(page);
            limit = PageCheck.checkLimit(limit);
            int start = PageCheck.calculateStart(page, limit);
            List<InveEdu> inveEdus = inveEduMapper.selectAll(name, start, limit);
            return Result.createSuccessResult(inveEduMapper.getCount(name), inveEdus);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Result.createByFailure("数据错误-003，联系管理员");
        }
    }

    /**
     * 用户端获取
     * @return
     */
    @PostMapping("/userGetAllInveEdu")
    public Result getAllInveEdu() {
        int page = 1;
        int limit =1000;
        String name = null;
        try {
            page = PageCheck.checkPage(page);
            limit = PageCheck.checkLimit(limit);
            int start = PageCheck.calculateStart(page, limit);
            List<InveEdu> inveEdus = inveEduMapper.selectAll(name, start, limit);
            return Result.createSuccessResult(inveEduMapper.getCount(name), inveEdus);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Result.createByFailure("数据错误-003，联系管理员");
        }
    }


    /**
     * 删除投资者教育
     *
     * @param inveEdu
     * @return
     */
    @PostMapping("/deleteInveEduById")
    public Result deleteInveEduById(InveEdu inveEdu) {
        try {
            fileUtil.deleteFile(inveEdu.getPdfUrl());
            inveEduMapper.deleteByPrimaryKey(inveEdu.getPaperId());
            return Result.createSuccessResult();
        } catch (Exception e) {
            return Result.createByFailure("删除失败-0023，");
        }
    }


}
