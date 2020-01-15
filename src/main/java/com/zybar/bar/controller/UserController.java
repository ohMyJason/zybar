package com.zybar.bar.controller;


import com.auth0.jwt.JWT;
import com.zybar.bar.annotations.UserLoginToken;
import com.zybar.bar.dao.UserMapper;
import com.zybar.bar.model.User;
import com.zybar.bar.service.TokenService;
import com.zybar.bar.service.UserService;
import com.zybar.bar.util.FileUtil;
import com.zybar.bar.util.PageCheck;
import com.zybar.bar.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 刘佳昇
 * @Date 2019/7/26 14:23
 */


@RestController
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    TokenService tokenService;
    @Autowired
    UserMapper userMapper;
    @Autowired
    FileUtil fileUtil;


    /**
     * loginName ：登录名
     * password ： 密码
     * userId : zqq的登录页面
     * @param param
     * @return
     */
    //登录 -> 检查用户名密码与数据库中的记录是否匹配
    @PostMapping("/login")
    public Result login(@RequestBody Map<String,String> param) {
//        User userForBase = userService.findByUsername(user);
//        上面是根据用户名登录，这里是根据id登录
        User userForBase;
        if (param.get("userId")==null||param.get("userId").equals("")){

             userForBase = userMapper.selectByUserNameOrLoginNameOrId(param.get("loginName"));
        }else {
             userForBase = userMapper.selectByUserNameOrLoginNameOrId(param.get("userId"));
        }

        if (userForBase == null) {
            return Result.createByFailure("登录失败,用户不存在");
        } else {
            if (!userForBase.getPassword().equals(param.get("password"))) {
                return Result.createByFailure("登录失败,密码错误");
            } else {
                String token = tokenService.getToken(userForBase);
                List<Object> listObject = new ArrayList<>();
                listObject.add(userForBase);
                listObject.add(token);
                return Result.createSuccessResult(2, listObject);
            }
        }
    }

    @UserLoginToken
    @PostMapping("/getMessage")
    public String getMessage() {
        try {
            return "你已通过验证";
        } catch (Exception e) {
            return "请登录";
        }
    }

    @UserLoginToken
    @PostMapping("/testGetUserId")
    public Result testGetUserId(HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getHeader("token");// 从 http 请求头中取出 token
        String userId = JWT.decode(token).getAudience().get(0);
        User user = userService.findUserById(Integer.parseInt(userId));
        return Result.createSuccessResult(user);
    }


    @CrossOrigin
    @PostMapping("/getAllUser")
    public Result getAllUser(){
        //懒得在写一个无参mapper了
        int start = 1;
        int limit = 10000;
       return  Result.createSuccessResult(userMapper.getAllUser(start, limit, null,null));
    }

    //这里多了个getUser接口给后台用由于上个api是无参的，无法满足后台的分页要求，加上上个接口用到好多处，如果改的话会乱掉，答应我，下次设计api的时候用点心（：D）
    @PostMapping("/backGetAllUser")
    public Result backGetAllUser(int page,int limit,String username,Integer role){
        page = PageCheck.checkPage(page);
        limit = PageCheck.checkLimit(limit);
        int start = PageCheck.calculateStart(page, limit);
        int count = userMapper.getCount(username,role);
        List<User> allUser = userMapper.getAllUser(start, limit, username,role);
        return Result.createSuccessResult(count,allUser);
    }

    @PostMapping("/getUserByToken")
    public Result getUserById(HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getHeader("token");
        String userId = JWT.decode(token).getAudience().get(0);
        User user = userMapper.selectByPrimaryKey(Integer.parseInt(userId));
        return Result.createSuccessResult(user);
    }

    @PostMapping("/getUserById")
    public Result getUserById(int userId){
        return Result.createSuccessResult(userMapper.selectByPrimaryKey(userId));
    }


    @PostMapping("/uploadUserImg")
    public Result uploadUserImg(@RequestParam(name = "file")MultipartFile file){
        try {
            String url = fileUtil.fileUpload(file, 5);
            return Result.createSuccessResult(url);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return Result.createByFailure();
        }
    }

    @PostMapping("/updateUserByToken")
    public Result updateUser(User user,HttpServletRequest httpServletRequest){
        String token =httpServletRequest.getHeader("token");
        String userId = JWT.decode(token).getAudience().get(0);
        user.setUserId(Integer.parseInt(userId));
        User baseUser = userMapper.selectByPrimaryKey(user.getUserId());

        if (user.getUsername()==null||user.getUsername().equals("")){
            user.setUsername(baseUser.getUsername());
        }
        if (user.getPhotoUrl()==null||user.getPhotoUrl().equals("")){
            user.setPhotoUrl(baseUser.getPhotoUrl());
        }
        if (user.getPassword()==null||user.getPassword().equals("")){
            user.setPassword(baseUser.getPassword());
        }

        try {
            int col = userMapper.updateByPrimaryKeySelective(user);
            if (col>0){
                return Result.createSuccessResult();
            }else {
                return Result.createByFailure();
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return Result.createByFailure("异常");
        }
    }

    @PostMapping("/updateUserById")
    public Result updateUserById(User user){
        try {

            User userByLoginName = userMapper.selectCountByLoginName(user.getLoginName());
            if (userByLoginName!=null && !userByLoginName.getUserId().equals(user.getUserId())){
                return Result.createByFailure("登录名与其他登录名重复");
            }

            int col = userMapper.updateByPrimaryKeySelective(user);




            if (col>0){
                return Result.createSuccessResult();
            }else {
                return Result.createByFailure();
            }
        }catch (Exception e){
           e.printStackTrace();
            return Result.createByFailure("异常");
        }
    }

    @PostMapping("/deleteUserById")
    public  Result deleteUserById(int userId){
        try {
            int col = userMapper.deleteByPrimaryKey(userId);
            if (col>0){
                return Result.createSuccessResult();
            }else {
                return Result.createByFailure("无此用户");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return Result.createByFailure("异常");
        }
    }

    @PostMapping("/insertUser")
    public Result insertUser(User user){
        try {
            userMapper.insertSelective(user);
            return Result.createSuccessResult(user);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return Result.createByFailure("异常");
        }
    }


}
