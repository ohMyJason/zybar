package com.zybar.bar.service;

import com.zybar.bar.model.App;
import com.zybar.bar.util.Result;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 刘佳昇
 * @Date 2019/8/8 19:55
 */

public interface AppService {
    Result insertApp( App app);
    Result selectApp(String name, int page, int limit);
    Result deleteApp(Integer id);

    Result selectApp();
}
