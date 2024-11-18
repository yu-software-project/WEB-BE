package SwProject.domain.manager.service;

import SwProject.SpringSecurity.jwt.dto.AllJwtTokenDto;
import SwProject.businessProcess.auth.web.dto.WebSignUpDto;
import SwProject.businessProcess.facade.dto.ManagerRegisterDto;
import SwProject.businessProcess.auth.web.dto.WebSignInDto;

public interface ManagerService {
    void checkExits(WebSignUpDto.ceoInfo manager);
    void register(WebSignUpDto.ceoInfo manager, ManagerRegisterDto managerRegisterDto);
    AllJwtTokenDto signIn(WebSignInDto webSignInDto);
}
