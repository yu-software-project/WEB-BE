package SwProject.domain.RecruitmentManagement.controller.app;

import SwProject.Exception.collections.InputValid.BindingErrors;
import SwProject.config.constant.ControllerConstants;
import SwProject.domain.RecruitmentManagement.domain.recruitmentWaiting.dto.RequestAssignmentDto;
import SwProject.domain.RecruitmentManagement.facade.RecruitmentFacadeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/app/recruitment")
public class AppRecruitmentController {
    private final RecruitmentFacadeService recruitmentFacadeService;

    @Transactional
    @Operation(summary = "App signUp Api summary", description = "봉사자가 대기신청 누를 시 사용될 api")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success",
                    content = {@Content(schema = @Schema(implementation = String.class, example = "봉사공고 신청이 완료되었습니다."))}),
            @ApiResponse(responseCode = "400", description = "Not found"),
    })
    @PostMapping("/waiting/request")
    public ResponseEntity<?> createRecruitmentWaiting(@RequestBody @Valid RequestAssignmentDto requestRecruitmentDto, BindingResult bindingResult) {
        handleBindingErrors(bindingResult);
        recruitmentFacadeService.addVolunteerToRecruitment(requestRecruitmentDto);
        return new ResponseEntity<>(ControllerConstants.completecreateRecruitmentWaiting , HttpStatus.OK);
    }

    public void handleBindingErrors(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BindingErrors();
        }
    }

}
