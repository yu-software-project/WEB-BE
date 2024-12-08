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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminFacadeServiceImpl implements AdminFacadeService {
    private final EmailService emailService;
    private final ManagerService managerService;
    private final ChildCenterService childCenterService;

    @Override
    public List<CenterNameListReq> showCenterSummary(Manager.ApprovalStatus approvalStatus) {
        return childCenterService.showCenterSummary(approvalStatus);
    }

    @Override
    public CenterDetailInfoReq showCenterDetail(Long centerId, Manager.ApprovalStatus approvalStatus) {
        Manager manager = managerService.findByCenterIdAndApproval(centerId, approvalStatus);
        ChildCenter childCenter = manager.getChildCenter();

        CenterDetailInfoReq centerDetailInfo = new CenterDetailInfoReq(
                childCenter.getCeoName(),               // CEO 이름
                childCenter.getCenterName(),            // 센터 이름
                childCenter.getPhoneNumId(),            // 전화번호 ID
                childCenter.getTotalAddress(),          // 주소 (roadAddress + detailAddress)
                childCenter.getCertificate(),           // 인증서 정보
                manager.getEmailId(),                   // 매니저 이메일
                manager.getPhoneNum()                   // 매니저 전화번호
        );

        return centerDetailInfo;
    }

    @Override
    @Transactional
    public void deleteCenter(Long center_id) {
        Manager manager = managerService.findByCenterIdAndApproval(center_id, Manager.ApprovalStatus.APPROVED);
        managerService.deleteManager(manager);
    }

    @Override
    @Transactional
    public void refuseCenterRegister(CenterRegisterRefusalReason refusalReason)  throws MessagingException {
        Manager manager = managerService.findByCenterIdAndApproval(refusalReason.centerId(), Manager.ApprovalStatus.NOT_APPROVED);
        emailService.sendCenterRegisterNotApprovalReason(manager.getEmailId(), refusalReason.reason());
        managerService.deleteManager(manager);
    }

    @Override
    @Transactional
    public void permitCenterRegister(Long center_id) {
        Manager manager = managerService.findByCenterIdAndApproval(center_id, Manager.ApprovalStatus.NOT_APPROVED);
        manager.setApprovalStatus(); //해당 매니저 허용
    }
}
