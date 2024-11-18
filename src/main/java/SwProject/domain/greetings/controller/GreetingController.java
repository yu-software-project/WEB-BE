package SwProject.domain.greetings.controller;

import SwProject.Exception.collections.InputValid.BindingErrors;
import SwProject.businessProcess.facade.dto.request.RequestUpdateGreetingOrRouteInfoDto;
import SwProject.businessProcess.facade.service.DatabaseFacadeService;
import SwProject.config.constant.ControllerConstants;
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

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/greeting")
public class GreetingController {
    private final DatabaseFacadeService databaseFacadeService;

    @Transactional
    @Operation(summary = "greeting update Api", description = "인사말 업로드 api")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success",
                    content = {@Content(schema = @Schema(implementation = String.class, example = "인삿말이 업데이트 완료되었습니다."))}),
            @ApiResponse(responseCode = "400", description = "Not found"),
    })
    @PutMapping("/update") //인삿말 db 변경 -> 이미 회원가입때 만들어서 다 update라고 치면 됨
    public ResponseEntity<?> updateGreeting(@RequestBody @Valid  RequestUpdateGreetingOrRouteInfoDto requestUpdateGreetingDto, BindingResult bindingResult) throws IOException {
        handleBindingErrors(bindingResult);

        databaseFacadeService.updateGreetings(requestUpdateGreetingDto);

        return new ResponseEntity<>(ControllerConstants.completeUpdateGreetings, HttpStatus.OK);
    }

    public void handleBindingErrors(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BindingErrors();
        }
    }

}
