package SwProject.businessProcess.auth.web.controller;

import SwProject.businessProcess.auth.web.dto.CenterDetailInfoReq;
import SwProject.businessProcess.auth.web.dto.CenterNameListReq;
import SwProject.businessProcess.auth.web.dto.CenterRegisterRefusalReason;
import SwProject.businessProcess.facade.service.AdminFacadeService;
import SwProject.config.constant.ControllerConstants;
import SwProject.domain.manager.model.Manager;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/admin")
@RequiredArgsConstructor
@Slf4j
public class AdminController implements AdminControllerApi {
    private final AdminFacadeService adminFacadeService;

    //승인 대기 기관 목록 반환
    @Override
    @GetMapping("/waiting/center/summary")
    public ResponseEntity<?> showWaitingCenterSummary() {
        List<CenterNameListReq> centersSummary = adminFacadeService.showCenterSummary(Manager.ApprovalStatus.NOT_APPROVED);
        return new ResponseEntity<>(centersSummary, HttpStatus.OK);
    }

    //승인 대기 특정 기관 상세 정보 반환
    @Override
    @GetMapping("waiting/center/detail/{center_id}")
    public ResponseEntity<?> showWaitingCenterDetail(Long center_id) {
        CenterDetailInfoReq centersDetail = adminFacadeService.showCenterDetail(center_id, Manager.ApprovalStatus.NOT_APPROVED);
        return new ResponseEntity<>(centersDetail, HttpStatus.OK);
    }

    //승인 완료 기관 목록 반환
    @Override
    @GetMapping("/center/summary")
    public ResponseEntity<?> showCenterSummary() {
        List<CenterNameListReq> centersSummary = adminFacadeService.showCenterSummary(Manager.ApprovalStatus.APPROVED);
        return new ResponseEntity<>(centersSummary, HttpStatus.OK);
    }

    //승인 완료 기관 상세 정보 반환
    @Override
    @GetMapping("/center/detail/{center_id}")
    public ResponseEntity<?> showCenterDetail(Long center_id) {
        CenterDetailInfoReq centersDetail = adminFacadeService.showCenterDetail(center_id, Manager.ApprovalStatus.APPROVED);
        return new ResponseEntity<>(centersDetail, HttpStatus.OK);
    }

    //특정 기관 삭제
    @Override
    @DeleteMapping("/center/delete/{center_id}")
    public ResponseEntity<?> deleteCenter(Long center_id) {
        adminFacadeService.deleteCenter(center_id);
        return new ResponseEntity<>(ControllerConstants.completeDeleteCenterByAdmin, HttpStatus.OK);
    }

    //특정 기관 회원 가입 거부
    @Override
    @PostMapping("/center/register/refusal")
    public ResponseEntity<?> refuseCenterRegister(CenterRegisterRefusalReason refusalReason, BindingResult bindingResult) throws MessagingException {
        adminFacadeService.refuseCenterRegister(refusalReason);
        return new ResponseEntity<>(ControllerConstants.completeNotPermitCenter, HttpStatus.OK);
    }

    //특정 기관 회원 가입 승인
    @Override
    @PostMapping("/center/register/permit/{center_id}")
    public ResponseEntity<?> permitCenterRegister(Long center_id) {
        adminFacadeService.permitCenterRegister(center_id);
        return new ResponseEntity<>(ControllerConstants.completePermitCenter, HttpStatus.OK);
    }

}
