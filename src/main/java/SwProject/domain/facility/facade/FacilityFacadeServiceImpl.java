package SwProject.domain.facility.facade;

import SwProject.Exception.collections.InputValid.InvalidClientRequest;
import SwProject.Exception.collections.IoException.ImageInputException;
import SwProject.SpringSecurity.authentication.SecurityUtils;
import SwProject.businessProcess.s3.S3Service;
import SwProject.domain.center.childCenter.model.ChildCenter;
import SwProject.domain.center.childCenter.service.ChildCenterService;
import SwProject.domain.facility.dto.FacilityFloorSizeUpdateRequest;
import SwProject.domain.facility.facilityIntroduction.model.FacilityIntroduction;
import SwProject.domain.facility.facilityIntroduction.repository.FacilityIntroRepository;
import SwProject.domain.facility.facilityIntroduction.service.FacilityService;
import SwProject.domain.facility.floorPictureCluster.model.FloorPictureCluster;
import SwProject.domain.facility.floorPictureCluster.service.FloorPictureClusterService;
import SwProject.domain.facility.floorPicutre.dto.FloorPictureDto;
import SwProject.domain.facility.floorPicutre.model.FloorPicture;
import SwProject.domain.facility.floorPicutre.service.FloorPictureService;
import SwProject.domain.facility.floorSize.dto.UpdateFloorSizeDto;
import SwProject.domain.facility.floorSize.model.FloorSize;
import SwProject.domain.facility.floorSize.service.FloorSizeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class FacilityFacadeServiceImpl implements FacilityFacadeService {
    private final SecurityUtils securityUtils;
    private final FacilityService facilityService;
    private final FloorSizeService floorSizeService;
    private final FloorPictureClusterService floorPictureClusterService;
    private final FacilityIntroRepository facilityIntroRepository;
    private final FloorPictureService floorPictureService;
    private final S3Service s3Service;
    private final ChildCenterService childCenterService;

    @Transactional
    @Override
    public void createOrUpdateFloorSize(List<UpdateFloorSizeDto> updateFloorSizeDtoList) {
        ChildCenter fetchedChildCenter = getChildCenterPk();

        //1. 인증된 이메일 가져오기
        Authentication authenticationEmail = securityUtils.getAuthentication();

        for(UpdateFloorSizeDto updateFloorSizeDto : updateFloorSizeDtoList){

            FloorSize fetchedFlooSize = floorSizeService. isExitsFloorSize(updateFloorSizeDto.getDisplayIndex(), fetchedChildCenter );

            FacilityFloorSizeUpdateRequest facilityFloorSizeUpdateRequest;
            //1-1. 수정인 경우 - 기존객체 삭제 및 db 생성
            if(fetchedFlooSize!=null) {
                facilityFloorSizeUpdateRequest = floorSizeService.updateFloorSize(fetchedFlooSize, updateFloorSizeDto, authenticationEmail);
            }
            //1-2. 생성인 경우 - db 생성
            else{
                facilityFloorSizeUpdateRequest = floorSizeService.createFloorSize(updateFloorSizeDto, authenticationEmail);
            }

            //2. 시설db에 양방향 매핑 위한 db 업데이트
            facilityService.updateFacilityFloorSizeList(facilityFloorSizeUpdateRequest);
        }

    }

//    @Transactional
//    @Override
//    public void createOrUpdateFloorPicture (List<FloorPictureDto> createFloorPictureDto, List<MultipartFile> multipartFile) {
//        // 1. 인증된 이메일 가져오기
//        Authentication authentication = securityUtils.getAuthenticationEmail();
//        // 2. 해당 이메일로 관리자->보육원->시설소개 pk 찾아오기
//        FacilityIntroduction facilityIntroduction = facilityIntroRepository.getFacilityPk(authentication);
//        //3. 각 객체 매핑시키기
//
//        //3-1 hashMap 변환
//        Map<String, MultipartFile> multipartFileMap = convertHashMap(multipartFile);
//
//        // TODO 1 : 최적화가 무조건 필요한 부분. 반복문 안에 find를(db접근 함수-jpa) 넣는 건 좋지않다. -> db의 접근빈도수가 너무 늘어버림 -> 네트워크 많아짐 -> 멀티스레드임 -> 성능악화 최고원인
//        // TODO 2 : SRP가 안되어있는 것 같음. 고칠 필요가 있어보임.
//        // 차라리 floorPictureDto에 대한 값을 db로 한 번의 네트워크로 모두 조회를 하되, 해당 결과를 List에 담아서 반환하자.
//        //3-2 각 dto 매핑
//        for (FloorPictureDto find : createFloorPictureDto) { //db 접근 횟수를 줄여야한다. 네트워크를 줄이는 습관을 길러야함. -> bulk 조회..? -> 한 번에 조회해오는 기능 ->
//            MultipartFile file = multipartFileMap.get(find.getImageName());
//
//            if (file == null) {
//                throw new InvalidClientRequest();
//            }
//
//            FloorPictureCluster floorPictureCluster = floorPictureClusterService.findByFloor(find.getFloor());
//
//            // 1. dto 속 floor의 floorPictureCluster DB 존재하는 경우 -> 수정
//            if (floorPictureCluster != null) {
//                databaseFacadeService.updateDbWhenModifyFloorPicture(find, authentication);
//            }
//            // 2. dto 속 floor의 floorPictureCluster DB 존재하지 않는 경우 -> 생성
//            else {
//
//                CreateDbWhenUpdateFloorPictureDto createDbDto = CreateDbWhenUpdateFloorPictureDto.builder()
//                        .floorPictureDto(find)
//                        .facilityIntroduction(facilityIntroduction)
//                        .authentication(authentication)
//                        .build();
//
//                databaseFacadeService.createDbWhenUpdateFloorPicute(createDbDto);
//            }
//        }
//
//    }


    @Transactional
    @Override //수정본
    public void createFloorPicture (List<FloorPictureDto> createFloorPictureDto, List<MultipartFile> multipartFile) throws ImageInputException {
        // 1. 인증된 이메일 가져오기
        Authentication authentication = securityUtils.getAuthentication();
        // 2. 해당 이메일로 관리자->보육원->시설소개 pk 찾아오기
        FacilityIntroduction facilityIntroduction = facilityIntroRepository.getFacilityPk(authentication);
        //3. 각 객체 매핑시키기

        //3-1 hashMap 변환
        Map<String, MultipartFile> multipartFileMap = convertHashMap(multipartFile);

        // TODO 1 : 최적화가 무조건 필요한 부분. 반복문 안에 find를(db접근 함수-jpa) 넣는 건 좋지않다. -> db의 접근빈도수가 너무 늘어버림 -> 네트워크 많아짐 -> 멀티스레드임 -> 성능악화 최고원인
        // TODO 2 : SRP가 안되어있는 것 같음. 고칠 필요가 있어보임.
        // 차라리 floorPictureDto에 대한 값을 db로 한 번의 네트워크로 모두 조회를 하되, 해당 결과를 List에 담아서 반환하자.
        //3-2 각 dto 매핑
        List<FloorPicture> createFloorPictureList = new ArrayList<>(createFloorPictureDto.size());

        for (FloorPictureDto find : createFloorPictureDto) { //db 접근 횟수를 줄여야한다. 네트워크를 줄이는 습관을 길러야함. -> bulk 조회..? -> 한 번에 조회해오는 기능 ->
            MultipartFile file = multipartFileMap.get(find.getImageName());

            if (file == null) {
                throw new InvalidClientRequest();
            }

            String s3ImageUrl = s3Service.uploadImageToS3(file);

            FloorPictureCluster fetchedFloorPictureCluster = floorPictureClusterService.findByFloor(find.getFloor(), getChildCenterPk());

            // 1. dto 속 floor의 floorPictureCluster DB 없으면 -> flooClusterDb 생성
            if (fetchedFloorPictureCluster == null) {
                FloorPictureCluster newFloorPictureCluster = floorPictureClusterService.createFloorPictureCluster(facilityIntroduction, find.getFloor());

                FloorPicture newFloorPicture = FloorPicture.builder()
                        .floor(find.getFloor())
                        .purpose(find.getPurpose())
                        .imageIndex(find.getImageIndex())
                        .floorPictureCluster(newFloorPictureCluster)
                        .pictureUrl(s3ImageUrl)
                        .build();

                //양방향 매핑
                List<FloorPicture> floorPictureList = newFloorPictureCluster.getFloorPictureList();
                floorPictureList.add(newFloorPicture);

                createFloorPictureList.add(newFloorPicture);
            }
            // 2. dto 속 floor의 floorPictureCluster DB 존재 -> imageindex에 해당하는 객체가 없다는 뜻 -> FloorPicture db 생성
            else {
                FloorPicture newFloorPicture = FloorPicture.builder()
                        .floor(find.getFloor())
                        .purpose(find.getPurpose())
                        .imageIndex(find.getImageIndex())
                        .floorPictureCluster(fetchedFloorPictureCluster)
                        .pictureUrl(s3ImageUrl)
                        .build();

                createFloorPictureList.add(newFloorPicture);

                //양방향 매핑
                List<FloorPicture> floorPictureList = fetchedFloorPictureCluster.getFloorPictureList();
                floorPictureList.add(newFloorPicture);

            }
        }
        floorPictureService.createAllFloorPictureList(createFloorPictureList);
    }


    @Override
    public void updateFloorPicuture(List<FloorPictureDto> updateFloorPictureDtoList, List<MultipartFile> multipartFiles) throws ImageInputException {

        ChildCenter childCenter = getChildCenterPk();

        //hashMap 변환
        Map<String, MultipartFile> multipartFileMap = convertHashMap(multipartFiles);

        for (FloorPictureDto find : updateFloorPictureDtoList) { //db 접근 횟수를 줄여야한다. 네트워크를 줄이는 습관을 길러야함. -> bulk 조회..? -> 한 번에 조회해오는 기능 ->
            MultipartFile file = multipartFileMap.get(find.getImageName());

            if (file == null) {
                throw new InvalidClientRequest();
            }

            String s3ImageUrl = s3Service.uploadImageToS3(file);

            FloorPictureCluster fetchedFloorPictureCluster = floorPictureClusterService.findByFloor(find.getFloor(), childCenter );

            FloorPicture updateFloorPicture = FloorPicture.builder()
                    .floor(find.getFloor())
                    .purpose(find.getPurpose())
                    .imageIndex(find.getImageIndex())
                    .floorPictureCluster(fetchedFloorPictureCluster)
                    .pictureUrl(s3ImageUrl)
                    .build();

            floorPictureService.updateFloorPicture(updateFloorPicture);

            //양방향 매핑
            List<FloorPicture> floorPictureList = fetchedFloorPictureCluster.getFloorPictureList();
            floorPictureList.add(updateFloorPicture);
        }

    }


    private Map<String, MultipartFile> convertHashMap(List<MultipartFile> multipartFile) {

        Map<String, MultipartFile> multipartFileMap = new HashMap<>();

        for (MultipartFile file : multipartFile) {
            multipartFileMap.put(file.getOriginalFilename(), file); // <파일이름, 파일> 으로 hashMap 변환
        }

        return multipartFileMap;
    }

    private ChildCenter getChildCenterPk(){
        Authentication authentication = securityUtils.getAuthentication();
        return childCenterService.getChildCenterPk(authentication);
    }

}
