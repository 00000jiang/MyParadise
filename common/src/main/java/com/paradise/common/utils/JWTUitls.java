package com.paradise.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.paradise.common.errorcode.BaseErrorCode;
import com.paradise.common.exception.ClientException;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jrf
 * @date 2023-3-30 13:23
 */
public class JWTUitls {

    private static final long EXPIRE_TIME = 60000L;//单位为秒
    private static final String TOKEN_SECRET = "lanlinker-paradise";

    /**
     * 生成token
     * @param user
     * @return
     */
    public static String sign(String user){
        String token = null;
        try {
            Date expireDate = new Date(System.currentTimeMillis() + EXPIRE_TIME * 1000);
            Map<String, Object> map = new HashMap<>(16);
            map.put("alg", "HS256");
            map.put("type", "JWT");
            token = JWT.create()
                    .withHeader(map)//添加头部
                    .withIssuer("auth0")
                    .withClaim("user", user)//载体中设置uid
                    .withExpiresAt(expireDate)//设置过期时间
                    .withIssuedAt(new Date())//设置签发时间
                    .sign(Algorithm.HMAC256(TOKEN_SECRET));//secret加密
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (JWTCreationException e) {
            e.printStackTrace();
        }
        return token;
    }

    /**
     * 校验token并解析token
     */
    public static boolean verifyToken(String token){
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).withIssuer("auth0").build();
            DecodedJWT decodedJWT = verifier.verify(token);
            return true;
        } catch (JWTVerificationException e) {
            e.printStackTrace();
            return false;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据token字符串，得到用户
     **/
    public static Map<String, Claim> getUser() throws Exception {
        String token = HeaderUtils.getToken();
        if (StringUtils.isEmpty(token)) {
            throw new ClientException(BaseErrorCode.A0010.message(),BaseErrorCode.A0010);
        }
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).withIssuer("auth0").build();
        DecodedJWT decodedJWT = verifier.verify(token);
        return decodedJWT.getClaims();
    }
}
