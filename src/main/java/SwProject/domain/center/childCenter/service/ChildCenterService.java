package SwProject.domain.center.childCenter.service;

import SwProject.businessProcess.auth.web.dto.CenterDetailInfoReq;
import SwProject.businessProcess.auth.web.dto.CenterNameListReq;
import SwProject.businessProcess.auth.web.dto.WebSignUpDto;
import SwProject.businessProcess.facade.dto.CenterForeignKeyDto;
import SwProject.businessProcess.facade.dto.ManagerRegisterDto;
import SwProject.domain.center.childCenter.dto.put.RequestFindWordDto;
import SwProject.domain.center.childCenter.model.ChildCenter;
import SwProject.domain.manager.model.Manager;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface ChildCenterService {
    ManagerRegisterDto register(WebSignUpDto.centerInfo childCenter, CenterForeignKeyDto centerForeignKeyDto, String s3url);
    void checkExits(WebSignUpDto.centerInfo childCenter);
    ChildCenter getChildCenterPk(Authentication authentication);
    List<ChildCenter> findChildCenterByWord(RequestFindWordDto requestFindWordDto);
    ChildCenter findById(Long id);
    List<CenterNameListReq> showCenterSummary(Manager.ApprovalStatus approvalStatus);
}
