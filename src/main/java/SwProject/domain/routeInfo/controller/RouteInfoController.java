package SwProject.domain.routeInfo.controller;

import SwProject.Exception.collections.InputValid.BindingErrors;
import SwProject.businessProcess.facade.dto.request.RequestUpdateGreetingOrRouteInfoDto;
import SwProject.businessProcess.facade.service.DatabaseFacadeService;
import SwProject.config.constant.ControllerConstants;
import SwProject.domain.routeInfo.dto.UpdateRouteInfoDto;
import SwProject.domain.routeInfo.service.RouteInfoService;
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
@RequestMapping("api/routeinfo")
public class RouteInfoController {
    private final DatabaseFacadeService databaseFacadeService;
    private final RouteInfoService routeInfoService;

    @Transactional
    @Operation(summary = "routeInfo update Api", description = "찾아오는 길 수정 api")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success",
                    content = {@Content(schema = @Schema(implementation = String.class, example = "찾아오는길이 업데이트 완료되었습니다."))}),
            @ApiResponse(responseCode = "400", description = "Not found"),
    })
    @PutMapping("update") //인삿말 db 변경 -> 이미 회원가입때 만들어서 다 update라고 치면 됨
    public ResponseEntity<?> updateRouteInfo(@RequestBody @Valid RequestUpdateGreetingOrRouteInfoDto requestUpdateRouteInfoDto,
                                             BindingResult bindingResult )  {
        handleBindingErrors(bindingResult);
        UpdateRouteInfoDto updateRouteInfoDto = databaseFacadeService.updateRouteInfo(requestUpdateRouteInfoDto);
        routeInfoService.updateRouteInfo(updateRouteInfoDto);

        return new ResponseEntity<>(ControllerConstants.completeUpdateRouteInfo, HttpStatus.OK);
    }

    public void handleBindingErrors(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BindingErrors();
        }
    }

}
