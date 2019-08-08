package com.zybar.bar.service.impl;

import com.zybar.bar.dao.PaperMapper;
import com.zybar.bar.model.Paper;
import com.zybar.bar.service.PaperService;
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
    public String insertPaper(String paperPath, String paperName) {
        Paper paper = new Paper();
        paper.setPdfUrl(paperPath);
        paper.setPaperName(paperName);
        paper.setUpdateTime(new SimpleDateFormat("yy-HH-mm HH:mm:ss").format(new Date()));
        paperMapper.insert(paper);
        return "0";
    }

    @Override
    public List<Paper> selectAllPaper() {
        return paperMapper.selectAllPaper();
    }
}
