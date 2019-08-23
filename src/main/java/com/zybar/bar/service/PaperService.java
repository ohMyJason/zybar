package com.zybar.bar.service;

import com.zybar.bar.model.Paper;
import com.zybar.bar.util.Result;

import java.util.List;

/**
 * @author 刘佳昇
 * @Date 2019/8/8 15:43
 */

public interface PaperService {
    Integer insertPaper(String paperPath,String paperName);

    Result selectAllPaper(String paperName, int page, int limit);
}
