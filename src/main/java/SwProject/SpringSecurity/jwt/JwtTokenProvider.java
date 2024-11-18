package SwProject.SpringSecurity.jwt;

import SwProject.SpringSecurity.jwt.dto.AccessTokenDto;
import SwProject.SpringSecurity.jwt.dto.RefreshTokenDto;
import SwProject.SpringSecurity.jwt.redis.model.RedisRefreshTokenDto;
import SwProject.businessProcess.redis.dto.TokenSubAndRoleDto;
import SwProject.businessProcess.redis.service.JwtRedisService;
import SwProject.config.constant.JwtConstants;
import SwProject.config.spring.JwtProps;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;

import java.util.Date;

import static SwProject.config.constant.JwtConstants.BEARER_TYPE;

@Component
@Slf4j
public class JwtTokenProvider {
    private SecretKey secretKey;
    private Long refreshTokenExpirationMinutes;
    private Long accessTokenExpirationMinutes;
    private final JwtRedisService redisService;

    public JwtTokenProvider(JwtProps jwtProps, JwtRedisService redisService) {
        refreshTokenExpirationMinutes = jwtProps.getRefreshTokenExpirationMinutes();
        accessTokenExpirationMinutes = jwtProps.getAccessTokenExpirationMinutes();
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(jwtProps.getSecretKey()));
        this.redisService = redisService;
    }

    //헤더에서 토큰 추출 -> Authorization: Bearer <token>
    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_TYPE)) {
            return bearerToken.substring(7);
        }
        return null;
    }
    //헤더에 포함되어 날아온 Authorization을 가져온다
    //페이로드는 클레임의 집합이며, 클레임은 JWT에 포함된 특정 정보.
    // 클레임은 토큰 발급자, 발급 대상, 발급 시각, 만료 시각 등을 나타낼 수 있다.
    public String validateAdminAccessTokenData(String token) {
        // SignatureException :  비밀키 일치 X 처리
        //ExpiredJwtException :  만료 exception 처리
        Jws<Claims> claimsJws = Jwts.parser().verifyWith(secretKey).build()
                .parseSignedClaims(token); //페이로드 내의 클레임 객체 파싱

        return claimsJws.getPayload().getSubject();
    }

    public AccessTokenDto createAccessToken(String id, String roles) {
        JwtBuilder token = Jwts.builder();

        Date now = new Date();
        Date accessExpiredTime = new Date(now.getTime() + accessTokenExpirationMinutes );

        String jwt = Jwts.builder()
                .header().type(JwtConstants.JWT)
                .and()
                .claims()
                .subject(id)
                .add("role",roles) //추가 클레임
                .issuedAt(now)
                .expiration(accessExpiredTime)
                .and()
                .signWith(secretKey, Jwts.SIG.HS256)
                .compact();

        return AccessTokenDto.builder()
                .accessToken(jwt)
                .accessTokenExpiredTime(accessExpiredTime)
                .build();

    }

    public RefreshTokenDto createRefreshToken(String id, String roles ) {
        JwtBuilder token = Jwts.builder();

        Date now = new Date();
        Date refreshExpiredTime = new Date(now.getTime() + refreshTokenExpirationMinutes );


        String jwt = Jwts.builder()
                .header().type(JwtConstants.JWT)
                .and()
                .claims()
                .subject(id)
                .add("role",roles)
                .issuedAt(now)
                .expiration(refreshExpiredTime)
                .and()
                .signWith(secretKey, Jwts.SIG.HS256)
                .compact();

        RedisRefreshTokenDto refreshTokenDto = RedisRefreshTokenDto.builder()
                .id(id)
                .refrehToken(jwt)
                .build();

        redisService.save(refreshTokenDto);

        return RefreshTokenDto.builder()
                .refreshToken(jwt)
                .refreshTokenExpiredTime(refreshExpiredTime)
                .build();

    }

    public boolean validateRefreshTokenInRedis(String token) {
        // SignatureException :  비밀키 일치 X 처리
        //ExpiredJwtException :  만료 exception 처리
        Jws<Claims> claimsJws = Jwts.parser().verifyWith(secretKey).build()
                .parseSignedClaims(token);

        //sub와 권한 가져옴
        String sub = claimsJws.getPayload().getSubject();
        if(redisService.find(sub).equals(null)) return false;

        //토큰이 만료된 거라면 false, 아니면 true 반환
        return !claimsJws.getPayload().getExpiration().before(new Date());
    }

    public TokenSubAndRoleDto getSubjectAndRoleFromRefresh (String token) {
        //토큰 기반 회원 정보 추출
        //jwt parser 을 통해 SecretKey를 설정
        Jws<Claims> claimsJws = Jwts.parser().verifyWith(secretKey).build()
                .parseSignedClaims(token);

        //claim을 추출해서 토큰을 생성할때 넣었던 sub 값과 권한 추출.
        String sub = claimsJws.getPayload().getSubject();
        String role = (String) claimsJws.getPayload().get("role");

        TokenSubAndRoleDto tokenSubAndRoleDto = TokenSubAndRoleDto.builder()
                .accountId(sub)
                .role(role)
                .build();
        //account ID 와 roles 을 반환

        return tokenSubAndRoleDto;
    }

}

