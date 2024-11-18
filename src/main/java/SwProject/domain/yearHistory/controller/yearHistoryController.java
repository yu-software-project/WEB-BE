package SwProject.domain.yearHistory.controller;

import SwProject.Exception.collections.InputValid.BindingErrors;
import SwProject.config.constant.ControllerConstants;
import SwProject.domain.yearHistory.dto.decadeYear.RequestDecadeDataDto;
import SwProject.domain.yearHistory.facade.YearFacadeService;
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

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/history")
public class yearHistoryController {
    private final YearFacadeService yearFacadeService;

    @Transactional
    @Operation(summary = "연혁 수정 api", description = "연혁 정보 수정할 시에 대한 api")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success",
                    content = {@Content(schema = @Schema(implementation = String.class, example = "연혁이 업데이트 완료되었습니다."))}),
            @ApiResponse(responseCode = "400", description = "Not found"),
    })
    @PostMapping("/update")
    public ResponseEntity<?> updateYearHistory(@RequestBody @Valid List<RequestDecadeDataDto> yearDataList , BindingResult bindingResult){
        handleBindingErrors(bindingResult);
        yearFacadeService.createYearHistory(yearDataList);

        return new ResponseEntity<>(ControllerConstants.completeUpdateYearHistory, HttpStatus.OK);
    }


    public void handleBindingErrors(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BindingErrors();
        }
    }


}
