package SwProject.SpringSecurity.authentication;

import SwProject.Exception.collections.business.UnAuthenticatedUserAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtils {

    public Authentication getAuthentication(){ // SecurityContextHolder에서 인증 정보를 얻어 현재 사용자의 이메일 확인하기
        Authentication authentication = (Authentication) SecurityContextHolder.getContext().getAuthentication();

        if(!authentication.isAuthenticated()){ //인증된 상태가 아닐 때, 에러 처리
            throw new UnAuthenticatedUserAccessException();
        }

        return authentication;
    }

}
