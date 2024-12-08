package SwProject.businessProcess.facade.service;

import SwProject.businessProcess.auth.web.dto.CenterDetailInfoReq;
import SwProject.businessProcess.auth.web.dto.CenterNameListReq;
import SwProject.businessProcess.auth.web.dto.CenterRegisterRefusalReason;
import SwProject.domain.manager.model.Manager;
import jakarta.mail.MessagingException;

import java.util.List;

public interface AdminFacadeService {
    List<CenterNameListReq> showCenterSummary (Manager.ApprovalStatus approvalStatus);
    CenterDetailInfoReq showCenterDetail(Long childId, Manager.ApprovalStatus approvalStatus);
    void deleteCenter(Long center_id);
    void refuseCenterRegister(CenterRegisterRefusalReason refusalReason)  throws MessagingException;
    void permitCenterRegister(Long center_id);
}
