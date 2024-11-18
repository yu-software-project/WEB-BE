package SwProject.businessProcess.facade.service;

import SwProject.SpringSecurity.authentication.SecurityUtils;
import SwProject.businessProcess.auth.web.dto.WebSignUpDto;
import SwProject.businessProcess.facade.dto.*;
import SwProject.businessProcess.facade.dto.request.RequestUpdateGreetingOrRouteInfoDto;
import SwProject.businessProcess.facade.dto.response.HomeInAppResponseDto;
import SwProject.businessProcess.facade.dto.response.ResponseAddressAndRouteInfoDto;
import SwProject.businessProcess.facade.dto.response.ResponseGetFloorSizeAndPictureCluster;
import SwProject.businessProcess.facade.dto.response.ResponseGetGreetingsAndYearHistoryDto;
import SwProject.domain.RecruitmentManagement.domain.recruitment.dto.RecruitmentReservationDto;
import SwProject.domain.RecruitmentManagement.domain.recruitment.model.Recruitment;
import SwProject.domain.RecruitmentManagement.domain.recruitmentAccept.service.RecruitmentAcceptService;
import SwProject.domain.center.childCenter.dto.get.ResponseChildCenterFacilityInfoDto;
import SwProject.domain.center.childCenter.model.ChildCenter;
import SwProject.domain.center.childCenter.service.ChildCenterService;
import SwProject.domain.facility.facilityIntroduction.model.FacilityIntroduction;
import SwProject.domain.facility.facilityIntroduction.service.FacilityService;
import SwProject.domain.facility.floorPictureCluster.model.FloorPictureCluster;
import SwProject.domain.facility.floorPictureCluster.service.FloorPictureClusterService;
import SwProject.domain.facility.floorSize.model.FloorSize;
import SwProject.domain.facility.floorSize.service.FloorSizeService;
import SwProject.domain.greetings.domain.Greetings;
import SwProject.domain.greetings.service.GreetingsService;
import SwProject.domain.manager.service.ManagerService;
import SwProject.domain.RecruitmentManagement.domain.recruitment.dto.RecruitmentSummaryDto;
import SwProject.domain.RecruitmentManagement.domain.recruitment.service.RecruitmentService;
import SwProject.domain.routeInfo.domain.RouteInfo;
import SwProject.domain.routeInfo.dto.UpdateRouteInfoDto;
import SwProject.domain.routeInfo.service.RouteInfoService;
import SwProject.domain.scrapRecruitment.dto.ResponseRecruitmentWithScrapToAppDto;
import SwProject.domain.scrapRecruitment.dto.ScrapRecruitmentDto;
import SwProject.domain.scrapRecruitment.model.ScrapRecruitment;
import SwProject.domain.scrapRecruitment.service.ScrapRecruitmentService;
import SwProject.domain.volunteer.model.Volunteer;
import SwProject.domain.volunteer.service.VoluteerService;
import SwProject.domain.yearHistory.model.DecadeYear;
import SwProject.domain.yearHistory.service.decadeYear.DecadeYearService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DatabaseFacadeServiceImpl implements DatabaseFacadeService{
    private final RouteInfoService routeInfoService;
    private final GreetingsService greetingsService;
    private final FacilityService facilityService;
    private final ManagerService managerService;
    private final ChildCenterService childCenterService;
    private final FloorPictureClusterService floorPictureClusterService;
    private final SecurityUtils securityUtils;
    private final DecadeYearService decadeYearService;
    private final FloorSizeService floorSizeService;
    private final RecruitmentService recruitmentService;
    private final RecruitmentAcceptService recruitmentAcceptService;
    private final VoluteerService voluteerService;
    private final ScrapRecruitmentService scrapRecruitmentService;


    @Override
    @Transactional
    public void createDbWhenSignUp(WebSignUpDto webSignUpDto, String s3urlDto) throws IOException {
        //1. 찾아오는 길 db 생성
        RouteInfo routeInfo = routeInfoService.createRouteInfo();
        //2. 인삿말 db 생성
        Greetings greetings = greetingsService.createGreetings();
        //3. 시설소개 db 생성
        FacilityIntroduction facility = facilityService.createFacilityIntroduction();

        //4. Center db 생성 위해, foreignKey로 담길 db들 하나의 dto로 묶기
        CenterForeignKeyDto centerForeignKeyDto = CenterForeignKeyDto.builder()
                .facility(facility)
                .greetings(greetings)
                .routeInfo(routeInfo)
                .build();

        //5. ChildCenter db 생성 및 관계 설정
        ManagerRegisterDto managerRegisterDto = childCenterService.register(webSignUpDto.getCenterInfo(), centerForeignKeyDto, s3urlDto);

        //6. manager db 생성 및 관계 설정
        managerService.register(webSignUpDto.getCeoInfo(), managerRegisterDto);
    }

//    @Override
//    public void createDbWhenUpdateFloorPicute(CreateDbWhenUpdateFloorPictureDto createDbDto) {
//        // 1번. 해당 층에 대한 새로운 floorPictureCluster db 생성.
//        floorPictureClusterService.createFloorPictureCluster(createDbDto.getFacilityIntroduction(), createDbDto.getFloorPictureDto().getFloor());
//        // 2번. floorPicutureDb도 생성해서 FloorPictureDto find의 정보를 db에 넣어줌. + 1번에서 만들어준 floorPictureCluster db의 pk값을 외래키로 설정
//        FloorPictureListUpdateRequest floorPictureListUpdateRequest = floorPictureService.createFloorPicture(createDbDto.getFloorPictureDto(), createDbDto.getAuthentication());
//        // 3번. 1번에서 만들어준 floorPictureCluster 내부의 floorPictureList에 2번에서 만들어준 floorPicuture를 넣어줌
//        floorPictureClusterService.createFloorPictureList(floorPictureListUpdateRequest);
//    }

//    @Override
//    public void updateDbWhenModifyFloorPicture ( FloorPictureDto floorPictureDto, Authentication authentication ) {
//        //기존 객체 삭제 및 수정
//        FloorPictureListUpdateRequest updateFloorPicture = floorPictureService.updateFloorPicture(floorPictureDto, authentication);
//        //floorCluster에 재연결
//        floorPictureClusterService.createFloorPictureList(updateFloorPicture);
//    }

    @Override
    public void updateGreetings(RequestUpdateGreetingOrRouteInfoDto requestUpdateGreetingDto) {
        ChildCenter fechedChildCenter = getChildCenterPk();
        Greetings excitingGreeting = fechedChildCenter.getGreetings();
        excitingGreeting.update(requestUpdateGreetingDto.getMemo());
    }

    @Override
    public UpdateRouteInfoDto updateRouteInfo(RequestUpdateGreetingOrRouteInfoDto routeInfoDto) {
        ChildCenter fechedChildCenter = getChildCenterPk();

        RouteInfo oldRouteInfo =  fechedChildCenter.getRouteInfo();

        RouteInfo newRouteInfo = RouteInfo.builder()
                .memo(routeInfoDto.getMemo())
                .build();

        UpdateRouteInfoDto updateRouteInfoDto = UpdateRouteInfoDto.builder()
                .oldRouteInfo(oldRouteInfo)
                .newRouteInf0(newRouteInfo)
                .build();

        return updateRouteInfoDto;
    }

    @Override
    public ResponseGetGreetingsAndYearHistoryDto getGreetingsandYearHistory() {

        ChildCenter fechedChildCenter = getChildCenterPk();
        List<DecadeYear> decadeYearList = decadeYearService.findAllDecadeYearDesc(fechedChildCenter);

        ResponseGetGreetingsAndYearHistoryDto responseGetGreetingsAndHistoryInfo = ResponseGetGreetingsAndYearHistoryDto.builder()
                .greeting(fechedChildCenter.getGreetings())
                .decadeYearList(decadeYearList)
                .build();

        return responseGetGreetingsAndHistoryInfo;
    }

    @Override
    public ResponseAddressAndRouteInfoDto getAddressAndRouteInfo() {
        ChildCenter fetchedChildCenter = getChildCenterPk();

        ResponseAddressAndRouteInfoDto responseAddressAndRouteInfoDto = ResponseAddressAndRouteInfoDto.builder()
                .Address(fetchedChildCenter.getRoadAddress()+" "+fetchedChildCenter.getDetailAddress())
                .routeInfo(fetchedChildCenter.getRouteInfo())
                .build();

        return responseAddressAndRouteInfoDto;

    }

    @Override
    public ResponseGetFloorSizeAndPictureCluster getFailciltySizeAndFicture() {
        ChildCenter fetchedChildCenter = getChildCenterPk();
        List<FloorSize> floorSizes = floorSizeService.getAllFloorSize(fetchedChildCenter.getFacilityIntroduction());
        List<FloorPictureCluster> floorPictureClusters = floorPictureClusterService.getAllFloorPictureCluster(fetchedChildCenter.getFacilityIntroduction());

        ResponseGetFloorSizeAndPictureCluster response = ResponseGetFloorSizeAndPictureCluster.builder()
                .floorSizeList(floorSizes)
                .floorPictureClusterList(floorPictureClusters)
                .build();

        return response;

    }

    @Override
    public ResponseChildCenterFacilityInfoDto getChildCenterFailcilityInfo(Long childCenterId) {
        ChildCenter fechedChildCenter =  childCenterService.findById(childCenterId);
        List<DecadeYear> decadeYearList = decadeYearService.findAllDecadeYearDesc(fechedChildCenter);

        ResponseChildCenterFacilityInfoDto response = ResponseChildCenterFacilityInfoDto.builder()
                .greeting(fechedChildCenter.getGreetings())
                .decadeYearList(decadeYearList)
                .routeInfo(fechedChildCenter.getRouteInfo())
                .build();

        return response;
    }

    @Override
    public List<RecruitmentSummaryDto> getChildCenterRecruitmentPreveiw(Long childCenterId) {
        ChildCenter fechedChildCenter =  childCenterService.findById(childCenterId);
        return recruitmentService.getRecruitmentSummariesByChildCenter(fechedChildCenter);
    }


    @Override
    @Transactional(readOnly = true)
    public RecruitmentReservationDto getRecruitmentReservation(Long id) {
        Recruitment recruitment = recruitmentService.getRecruitmentById(id);
        ChildCenter childCenter = recruitment.getChildCenter();
        List<LocalDate> getRecruitmentAllDates = recruitmentService.getRecruitmentAllDates(recruitment);

        ScrapRecruitmentDto scrapRecruitmentDto = ScrapRecruitmentDto.builder()
                .volunteer(getVoluteerPk())
                .recruitment(recruitment)
                .build();

        ScrapRecruitment fetchedScrapRecruitment = scrapRecruitmentService.findScrapRecruitment(scrapRecruitmentDto);

        return RecruitmentReservationDto.builder()
                .id(recruitment.getId())
                .centerName(childCenter.getCenterName())
                .recruitmentName(recruitment.getName())
                .recruitmentStartDate(recruitment.getRecruitmentStartDate())
                .recruitmentEndDate(recruitment.getRecruitmentEndDate())
                .startTime(recruitment.getStartTime())
                .endTime(recruitment.getEndTime())
                .totalApplicants(recruitment.getTotalApplicants())
                .currentApplicants(recruitment.getCurrentApplicants())
                .recruitmentDates(getRecruitmentAllDates)
                .detailInfo(recruitment.getDetailInfo())
                .isScrap(fetchedScrapRecruitment!=null)
                .build();
    }

    @Override
    public  HomeInAppResponseDto getHomeInApp() {
        Volunteer volunteer = getVoluteerPk();

        int recruitmentCountByVoluteer = recruitmentAcceptService.countCompletedRecruitmentsByVolunteer(volunteer);

        // 스크랩된 봉사공고 3개를 최신순으로 가져오기
        List<ScrapRecruitment> scrapRecruitments = scrapRecruitmentService.findTop3ByVolunteerOrderByIdDesc(volunteer);

        List<ResponseRecruitmentWithScrapToAppDto> responseScrapRecruitments = scrapRecruitments.stream()
                .map(scrap -> ResponseRecruitmentWithScrapToAppDto.builder()
                        .id(scrap.getRecruitment().getId())
                        .recruitmentName(scrap.getRecruitment().getName())
                        .childCenterName(scrap.getRecruitment().getChildCenter().getCenterName())
                        .recruitmentStartDate(scrap.getRecruitment().getRecruitmentStartDate())
                        .isScrap(true)
                        .build())
                .collect(Collectors.toList());

        return HomeInAppResponseDto.builder()
                .volunteerName(volunteer.getName())
                .recruitmentCountByVolunteer(recruitmentCountByVoluteer)
                .scrapRecruitments(responseScrapRecruitments)
                .build();
    }

    private ChildCenter getChildCenterPk() {
        Authentication authentication = securityUtils.getAuthentication();
        return childCenterService.getChildCenterPk(authentication);
    }

    private Volunteer getVoluteerPk(){
        Authentication authentication = securityUtils.getAuthentication();
        return voluteerService.getVolunteeerPK(authentication);
    }

}
