package SwProject.domain.facility.floorPicutre.service;

import SwProject.domain.facility.floorPicutre.model.FloorPicture;
import SwProject.domain.facility.floorPicutre.repository.FloorPictureRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class FloorPictureServiceImpl implements FloorPictureService {
    private final FloorPictureRepository floorPictureRepository;


//    @Override
//    public FloorPictureListUpdateRequest createFloorPicture(FloorPictureDto floorPictureDto, Authentication authentication) {
//        //1. 시설소개 pk값 가져옴
//        FacilityIntroduction facilityIntroduction = facilityIntroRepository.getFacilityPk(authentication); //여기서 현재 이메일과 인증된 이메일 같은 지 검사함
//
//        //2.시설소개 pk 값을 층별사진모음 pk 찾는 함수에 인자로 넣어줌
//        FloorPictureCluster fetchedFloorPictureCluster = floorPictureClusterRepository.getFloorPicutreClusterPk(facilityIntroduction);
//
//        //3. db 업데이트
//        FloorPicture newFloorPicture = FloorPicture.builder()
//                .floor(floorPictureDto.getFloor())
//                .purpose(floorPictureDto.getPurpose())
//                .imageIndex(floorPictureDto.getImageIndex())
//                .floorPictureCluster(fetchedFloorPictureCluster)
//                .build();
//
//        floorPictureRepository.save(newFloorPicture);
//
//        //3. 양방향 매핑 위해 floorPicureClusterPk와 업데이트 된 db 반환
//        FloorPictureListUpdateRequest floorPictureListCreateRequest = FloorPictureListUpdateRequest.builder()
//                .floorPictureCluster(fetchedFloorPictureCluster)
//                .floorPicture(newFloorPicture)
//                .build();
//
//        return floorPictureListCreateRequest;
//    }

    @Override
    public void updateFloorPicture(FloorPicture updateFloorPicture) {
        FloorPicture oldFloorPicture = floorPictureRepository.updateFloorPicture(updateFloorPicture);

        floorPictureRepository.delete(oldFloorPicture);
        floorPictureRepository.save(updateFloorPicture);

    }

//    @Override
//    public FloorPictureListUpdateRequest updateFloorPicture (FloorPictureDto updateTargetFloorPicture, Authentication authentication) {
//        // 1번. dto의 행(floor)과 열(이미지인덱스)의 정보를 가진 동일 객체 찾기 및 삭제
//        // -> floorPicture db에서 null 처리 시, 가비지 콜렉터로 인해 해당 객체를 참조하고 있던 FloorPictureCluster 속에서도 사라질 것
//        UpdateFloorPictureDto updateFloorPicture = floorPictureRepository.updateFloorPicture(updateTargetFloorPicture);
//
//        // 2번. 기존 객체 업데이트
//        floorPictureRepository.delete(updateFloorPicture.getOldFloorPicture());
//        floorPictureRepository.save(updateFloorPicture.getNewFloorPicture());
//
//        //3. 양방향 매핑 위해 floorPicureClusterPk와 업데이트 된 db 반환
//        FloorPictureListUpdateRequest floorPictureListUpdateRequest = FloorPictureListUpdateRequest.builder()
//                .floorPictureCluster(updateFloorPicture.getOldFloorPicture().getFloorPictureCluster())
//                .floorPicture(updateFloorPicture.getNewFloorPicture())
//                .build();
//
//        //새롭게 clusterMapping 위해 반환
//        return floorPictureListUpdateRequest;
//
//    }

    @Override
    public void createAllFloorPictureList(List<FloorPicture> floorPictureList) {
        List<FloorPicture> newFloorPictures = floorPictureList.stream()
                .filter(floorPicture -> !isFloorPictureExists(floorPicture))
                .collect(Collectors.toList());

        if (!newFloorPictures.isEmpty()) {
            floorPictureRepository.saveAll(newFloorPictures);
        }
    }

    private boolean isFloorPictureExists(FloorPicture floorPicture) {
        return floorPictureRepository.existsByFloorAndImageIndexAndFloorPictureCluster(
                floorPicture.getFloor(),
                floorPicture.getImageIndex(),
                floorPicture.getFloorPictureCluster()
        );
    }


}
