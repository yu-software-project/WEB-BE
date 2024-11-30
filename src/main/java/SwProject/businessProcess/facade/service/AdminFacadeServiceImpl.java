package SwProject.businessProcess.facade.service;

import SwProject.businessProcess.auth.service.Email.EmailService;
import SwProject.businessProcess.auth.web.dto.CenterDetailInfoReq;
import SwProject.businessProcess.auth.web.dto.CenterNameListReq;
import SwProject.businessProcess.auth.web.dto.CenterRegisterRefusalReason;
import SwProject.domain.center.childCenter.model.ChildCenter;
import SwProject.domain.center.childCenter.service.ChildCenterService;
import SwProject.domain.manager.model.Manager;
import SwProject.domain.manager.service.ManagerService;
import jakarta.mail.MessagingException;

import java.util.List;

public class AdminFacadeServiceImpl implements AdminFacadeService {
    private EmailService emailService;
    private ManagerService managerService;
    private ChildCenterService childCenterService;

    @Override
    public List<CenterNameListReq> showCenterSummary(Manager.ApprovalStatus approvalStatus) {
        return childCenterService.showCenterSummary(approvalStatus);
    }

    @Override
    public CenterDetailInfoReq showCenterDetail(Long centerId) {
        Manager manager = managerService.findByCenterId(centerId);
        ChildCenter childCenter = manager.getChildCenter();

        CenterDetailInfoReq centerDetailInfo = new CenterDetailInfoReq(
                childCenter.getCeoName(),               // CEO 이름
                childCenter.getCenterName(),            // 센터 이름
                childCenter.getPhoneNumId(),            // 전화번호 ID
                childCenter.getTotalAddress(),  // 주소 (roadAddress + detailAddress)
                childCenter.getCertificate(),           // 인증서 정보
                manager.getEmailId(),                   // 매니저 이메일
                manager.getPhoneNum()                   // 매니저 전화번호
        );

        return centerDetailInfo;
    }

    @Override
    public void deleteCenter(Long center_id) {
        ChildCenter foundCenter = childCenterService.findById(center_id);
        foundCenter.offActivate();
    }

    @Override
    public void refuseCenterRegister(CenterRegisterRefusalReason refusalReason)  throws MessagingException {
        Manager manager = managerService.findByCenterId(refusalReason.centerId());
        manager.updateApprovalStatusReason(refusalReason.reason());

        emailService.sendCenterRegisterNotApprovalReason(manager.getEmailId(), refusalReason.reason());
    }

    @Override
    public void permitCenterRegister(Long center_id) {
        ChildCenter foundCenter = childCenterService.findById(center_id);
        foundCenter.onActivate();
        Manager manager = managerService.findByCenterId(center_id);
        manager.updateApprovalStatusReason("회원가입 승인이 완료되었습니다.");
    }
}
