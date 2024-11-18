package SwProject.businessProcess.auth.service.Facade.web;

import SwProject.SpringSecurity.jwt.dto.AllJwtTokenDto;
import SwProject.businessProcess.auth.web.dto.WebSignInDto;
import SwProject.businessProcess.auth.web.dto.WebSignUpDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AuthWebFacadeService {
    void authSignup(WebSignUpDto webSignUpDto, MultipartFile multipartFile) throws IOException;
    AllJwtTokenDto authSignIn(WebSignInDto signDto);
}

//"파사드 패턴" : 복잡한 서브시스템에 대한 간편한 인터페이스를 제공하는 디자인 패턴
// 이 패턴은 클라이언트와 복잡한 서브시스템 간의 의존성을 줄이고, 서브시스템을 더 쉽게 사용할 수 있도록 한다.