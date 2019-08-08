package com.zybar.bar.service;

import com.zybar.bar.model.App;
import com.zybar.bar.util.Result;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 刘佳昇
 * @Date 2019/8/8 19:55
 */

public interface AppService {
    Result insertApp(MultipartFile imgFile, App app);
    Result selectApp();
    Result deleteApp(Integer id);
}
