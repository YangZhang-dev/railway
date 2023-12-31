package com.zzys.railway.framework.starter.user.utils;

import com.alibaba.fastjson2.JSON;
import com.zzys.railway.framework.starter.base.ApplicationContextHolder;
import com.zzys.railway.framework.starter.user.config.UserAutoConfigurationProperties;
import com.zzys.railway.framework.starter.user.core.UserInfoDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.zzys.railway.framework.starter.base.constant.UserConstant.*;

/**
 * JWT 工具类
 *
 * @author YangZhang
 * @createTime 2023/09/21/ 17:08
 */
@Slf4j
public class JWTUtil {
    // TODO 测试有效性
    static UserAutoConfigurationProperties properties = ApplicationContextHolder.getBean(UserAutoConfigurationProperties.class);
    private static final long EXPIRATION = properties.getEXPIRATION();
    public static final String TOKEN_PREFIX = properties.getTOKEN_PREFIX();
    public static final String ISS = properties.getISS();
    public static final String SECRET = properties.getSECRET();

    /**
     * 生成用户 Token
     *
     * @param userInfo 用户信息
     * @return 用户访问 Token
     */
    public static String generateAccessToken(UserInfoDTO userInfo) {
        Map<String, Object> customerUserMap = new HashMap<>();
        customerUserMap.put(USER_ID_KEY, userInfo.getUserId());
        customerUserMap.put(USER_NAME_KEY, userInfo.getUsername());
        customerUserMap.put(REAL_NAME_KEY, userInfo.getRealName());
        String jwtToken = Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .setIssuedAt(new Date())
                .setIssuer(ISS)
                .setSubject(JSON.toJSONString(customerUserMap))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION * 1000))
                .compact();
        return TOKEN_PREFIX + jwtToken;
    }

    /**
     * 解析用户 Token
     *
     * @param jwtToken 用户访问 Token
     * @return 用户信息
     */
    public static UserInfoDTO parseJwtToken(String jwtToken) {
        if (StringUtils.hasText(jwtToken)) {
            String actualJwtToken = jwtToken.replace(TOKEN_PREFIX, "");
            try {
                Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(actualJwtToken).getBody();
                Date expiration = claims.getExpiration();
                if (expiration.after(new Date())) {
                    String subject = claims.getSubject();
                    return JSON.parseObject(subject, UserInfoDTO.class);
                }
            } catch (ExpiredJwtException ignored) {
                // token过期不做处理
            } catch (Exception ex) {
                log.error("JWT Token解析失败，请检查", ex);
            }
        }
        return null;
    }
}
