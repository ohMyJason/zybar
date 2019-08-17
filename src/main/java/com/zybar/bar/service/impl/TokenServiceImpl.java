package com.zybar.bar.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.zybar.bar.model.User;
import com.zybar.bar.service.TokenService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 刘佳昇
 * @Date 2019/8/14 15:26
 */
@Service
public class TokenServiceImpl implements TokenService {

    @Override
    public String getToken(User user) {
        String token = "";
        token = JWT.create().withAudience(""+user.getUserId())
                .sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }

    @Override
    public String getUserId(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        String userId = JWT.decode(token).getAudience().get(0);
        return userId;
    }

}
