package com.zybar.bar.service.impl;

import com.zybar.bar.dao.PaperMapper;
import com.zybar.bar.model.Paper;
import com.zybar.bar.service.PaperService;
import com.zybar.bar.util.PageCheck;
import com.zybar.bar.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author 刘佳昇
 * @Date 2019/8/8 16:53
 */

@Service
public class PaperServiceImpl implements PaperService {

    @Autowired
    PaperMapper paperMapper;

    @Override
    public Integer insertPaper(String paperPath, String paperName) {
        Paper paper = new Paper();
        paper.setPdfUrl(paperPath);
        paper.setPaperName(paperName);
        paper.setUpdateTime(new SimpleDateFormat("yy-HH-mm HH:mm:ss").format(new Date()));
        paperMapper.insert(paper);
        return 0;
    }

    @Override
    public Result selectAllPaper(String paperName, int page, int limit) {
        page = PageCheck.checkPage(page);
        limit = PageCheck.checkLimit(limit);
        int start = PageCheck.calculateStart(page,limit);
        int count = paperMapper.getCount(paperName);
        return Result.createSuccessResult(count,paperMapper.selectAllPaper(paperName,start,limit));
    }
}
