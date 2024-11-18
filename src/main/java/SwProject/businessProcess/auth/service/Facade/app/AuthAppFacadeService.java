package SwProject.businessProcess.auth.service.Facade.app;

import SwProject.SpringSecurity.jwt.dto.AllJwtTokenDto;
import SwProject.businessProcess.auth.app.dto.AppSignInDto;
import SwProject.businessProcess.auth.app.dto.AppSignUpDto;

public interface AuthAppFacadeService {
    void authSignUp(AppSignUpDto appSignUpDto);
    AllJwtTokenDto authSignIn (AppSignInDto appSignInDto);
}
