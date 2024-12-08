package SwProject.domain.manager.service;

import SwProject.SpringSecurity.jwt.dto.AllJwtTokenDto;
import SwProject.businessProcess.auth.web.dto.WebSignUpDto;
import SwProject.businessProcess.facade.dto.ManagerRegisterDto;
import SwProject.businessProcess.auth.web.dto.WebSignInDto;
import SwProject.domain.center.childCenter.model.ChildCenter;
import SwProject.domain.manager.model.Manager;

import java.util.List;

public interface ManagerService {
    void checkExits(WebSignUpDto.ceoInfo manager);
    void register(WebSignUpDto.ceoInfo manager, ManagerRegisterDto managerRegisterDto);
    AllJwtTokenDto signIn(WebSignInDto webSignInDto);
    Manager findByCenterIdAndApproval(Long centerId, Manager.ApprovalStatus approvalStatus);
    void deleteManager(Manager manager);
}
