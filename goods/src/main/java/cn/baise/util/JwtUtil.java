package cn.baise.util;

import io.jsonwebtoken.*;

import java.util.Date;
import java.util.UUID;

public class JwtUtil {
    //有效期一个小时
    private static int time = 1000*60*60;
    //加密加盐
    private static String signature= "fsjdfkhkjsahfkjlkfsd";
    public static String createToken(){
        JwtBuilder jwtBuilder = Jwts.builder();
        String jwtToken = jwtBuilder
                //头
                .setHeaderParam("typ","JWT")
                .setHeaderParam("alg","HS512")
                //内容
                .claim("username","tom")
                .claim("role","admin")
                .setSubject("admin-text")
                .setExpiration(new Date(System.currentTimeMillis()+time))
                .setId(UUID.randomUUID().toString())
                //盐法
                .signWith(SignatureAlgorithm.HS512,signature)
                //用点拼接
                .compact();

        return jwtToken;
    }
    public static boolean checkToken(String token){
        if (token == null){
            return false;
        }
        try {
            //捕获,看看是否可以正常解析
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(signature).parseClaimsJws(token);
        }catch (Exception e){
            return false;
        }
        return true;
    }
}
