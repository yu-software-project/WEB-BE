package SwProject.businessProcess.facade.service;

import SwProject.businessProcess.auth.web.dto.WebSignUpDto;
import SwProject.businessProcess.facade.dto.request.RequestUpdateGreetingOrRouteInfoDto;
import SwProject.businessProcess.facade.dto.response.HomeInAppResponseDto;
import SwProject.businessProcess.facade.dto.response.ResponseAddressAndRouteInfoDto;
import SwProject.businessProcess.facade.dto.response.ResponseGetFloorSizeAndPictureCluster;
import SwProject.businessProcess.facade.dto.response.ResponseGetGreetingsAndYearHistoryDto;
import SwProject.domain.RecruitmentManagement.domain.recruitment.dto.RecruitmentReservationDto;
import SwProject.domain.center.childCenter.dto.get.ResponseChildCenterFacilityInfoDto;
import SwProject.domain.RecruitmentManagement.domain.recruitment.dto.RecruitmentSummaryDto;
import SwProject.domain.routeInfo.dto.UpdateRouteInfoDto;

import java.io.IOException;
import java.util.List;

public interface DatabaseFacadeService {
    void createDbWhenSignUp(WebSignUpDto webSignUpDto, String s3Url) throws IOException;
    void updateGreetings(RequestUpdateGreetingOrRouteInfoDto GreetingDto) throws IOException;
    UpdateRouteInfoDto updateRouteInfo(RequestUpdateGreetingOrRouteInfoDto routeInfoDto);
    ResponseGetGreetingsAndYearHistoryDto getGreetingsandYearHistory();
    ResponseAddressAndRouteInfoDto getAddressAndRouteInfo();
    ResponseGetFloorSizeAndPictureCluster getFailciltySizeAndFicture();
    ResponseChildCenterFacilityInfoDto getChildCenterFailcilityInfo(Long childCenterId);
    List<RecruitmentSummaryDto> getChildCenterRecruitmentPreveiw(Long childCenterId);
    RecruitmentReservationDto getRecruitmentReservation(Long recruitmentId);
    HomeInAppResponseDto getHomeInApp();
}

