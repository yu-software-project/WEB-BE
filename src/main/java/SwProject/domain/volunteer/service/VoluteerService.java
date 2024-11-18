package SwProject.domain.volunteer.service;

import SwProject.SpringSecurity.jwt.dto.AllJwtTokenDto;
import SwProject.businessProcess.auth.app.dto.AppSignInDto;
import SwProject.businessProcess.auth.app.dto.AppSignUpDto;
import SwProject.businessProcess.auth.app.dto.CheckExitsVoluteerDto;
import SwProject.domain.volunteer.model.Volunteer;
import org.springframework.security.core.Authentication;

public interface VoluteerService {
    void checkExits(CheckExitsVoluteerDto checkExits );
    void signUp(AppSignUpDto appSignUpDto);
    AllJwtTokenDto signIn(AppSignInDto appSignInDto);
    Volunteer getVolunteeerPK(Authentication authentication);
}
