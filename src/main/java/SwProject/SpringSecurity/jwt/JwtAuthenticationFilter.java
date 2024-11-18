package SwProject.SpringSecurity.jwt;

import SwProject.Exception.collections.business.TokenMissingException;
import SwProject.Exception.dto.ExceptionDto;
import SwProject.Exception.message.CommonExceptionMessage;
import io.jsonwebtoken.*;
import SwProject.Exception.message.TokenExceptonMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtTokenProvider jwtTokenProvider;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestURI = request.getRequestURI(); //요청된 API 경로

        if (!requiregsAuthentication(requestURI)) { //허용 된 URL 요청이라면 토큰 검증없이 진행
            filterChain.doFilter(request, response);
            return;
        }

        try {
            String token = jwtTokenProvider.resolveToken(request);

            if (token == null) {
                throw new TokenMissingException();
            }

            String subjectClaim = jwtTokenProvider.validateAdminAccessTokenData(token);
            //사용자의 인증된 정보를 이용해 인증 토큰을 생성하고, 이 토큰을 보안 컨텍스트에 설정하는 과정을 수행!
            //각 요청이 별도의 스레드에서 처리되므로, 요청이 끝날 때마다 자동으로 해당 스레드의 보안 컨텍스트 정보가 초기화 됨
            //이후의 작업에서 SecurityContextHolder를 통해 현재 사용자의 인증 정보를 얻을 수 있음
            this.authentication(subjectClaim);
        } catch (TokenMissingException e) {
            writeJsonToResponse(
                    response,
                    TokenExceptonMessage.TOKEN_MISSING_HEADER,
                    HttpStatus.UNAUTHORIZED.value()
            );
            return;
        } catch (SecurityException e) { // 비밀키 일치 X 처리
            writeJsonToResponse(
                    response,
                    TokenExceptonMessage.SignatureNotMatchException,
                    HttpStatus.UNAUTHORIZED.value()
            );
            return;

        } catch (ExpiredJwtException e) { //토큰 기간 만료시 처리
            writeJsonToResponse(
                    response,
                    TokenExceptonMessage.JwtAccessTokenExpired,
                    HttpStatus.UNAUTHORIZED.value()
            );
            return;
        } catch (UsernameNotFoundException e) { //해당 사용자 이메일의 계정이 존재하지 않는 경우
            writeJsonToResponse(
                    response,
                    CommonExceptionMessage.UsernameNotFoundException,
                    HttpStatus.UNAUTHORIZED.value()
            );
            return;
        } catch (JwtException e) { // 토큰 자체가 잘못된 경우
            writeJsonToResponse(
                    response,
                    TokenExceptonMessage.InvalidJwtToken,
                    HttpStatus.UNAUTHORIZED.value()
            );
            return;
        } catch (Exception e) { //그 외 모든 예외 처리
            writeJsonToResponse(
                    response,
                    TokenExceptonMessage.UndefinedException,
                    HttpStatus.UNAUTHORIZED.value()
            );
            return;
        }
        filterChain.doFilter(request, response);
    }

    public void authentication(String identifier) {
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(identifier);
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        userDetails, null,
                        userDetails.getAuthorities()
                )
        );
    }

    public void writeJsonToResponse( // 반환 할 HTTP Response 를 설정
                                     HttpServletResponse httpServletResponse, //HTTP 응답
                                     String errorMessage, //에러 메시지
                                     Integer statusCode //상태 코드
    ) throws IOException {
        ExceptionDto expireExceptionDto = ExceptionDto
                .builder()
                .success("false")
                .errorMessage(errorMessage)
                .occurredTime(LocalDateTime.now())
                .statusCode(statusCode)
                .build();

        // Content Type 설정
        httpServletResponse.setContentType("application/json");
        httpServletResponse.setCharacterEncoding("UTF-8");

        // HTTP 응답 상태 코드 설정
        httpServletResponse.setStatus(statusCode);

        // ExceptionDto 객체를 JSON 문자열로 변환
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false); // 날짜/시간을 문자열로 직렬화
        String jsonResponse = objectMapper.writeValueAsString(expireExceptionDto);

        // JSON 응답 작성
        httpServletResponse.getWriter().write(jsonResponse);
    }

    private boolean requiregsAuthentication(String requestURI) {
        // 로그인, 회원가입 등의 엔드포인트는 토큰 없이 접근 가능
        Set<String> openUrlPatterns = new HashSet<>(Arrays.asList(
                "/api/auth",
                "/api/verify",
                "/static/**",
                "/swagger-ui/",
                "/swagger-resources",
                "/v3/api-docs",
                "/api-docs",
                "/swagger-ui/",
                "/v3/api-docs/swagger-config",
                "/favicon.ico"  // 추가된 허용 경로
        ));
        return openUrlPatterns.stream().noneMatch(requestURI::startsWith);
    }


}
