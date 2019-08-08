package com.zybar.bar.service;

import com.zybar.bar.model.Paper;

import java.util.List;

/**
 * @author 刘佳昇
 * @Date 2019/8/8 15:43
 */

public interface PaperService {
    String insertPaper(String paperPath,String paperName);

    List<Paper> selectAllPaper();
}
