package SwProject.domain.facility.floorPictureCluster.service;


import SwProject.domain.center.childCenter.model.ChildCenter;
import SwProject.domain.facility.facilityIntroduction.model.FacilityIntroduction;
import SwProject.domain.facility.floorPictureCluster.model.FloorPictureCluster;
import SwProject.domain.facility.floorPictureCluster.repository.FloorPictureClusterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class FloorPictureClusterServiceimpl implements FloorPictureClusterService {
    private final FloorPictureClusterRepository floorPictureClusterRepository;

    @Override
    public FloorPictureCluster createFloorPictureCluster(FacilityIntroduction facilityIntroduction, int floor) {

        //1. floorPictureCluster db 생성
        FloorPictureCluster floorPictureCluster = FloorPictureCluster.builder()
                .floor(floor)
                .floorPictureList(new ArrayList<>())
                .facilityIntroduction(facilityIntroduction)
                .build();

        floorPictureClusterRepository.save(floorPictureCluster);

        return floorPictureCluster;
    }

    @Override
    public FloorPictureCluster findByFloor(int floor, ChildCenter childCenter) {
        FloorPictureCluster floorPictureCluster =  floorPictureClusterRepository.findByFloor(floor, childCenter);
        return floorPictureCluster;
    }

    @Override
    public List<FloorPictureCluster> getAllFloorPictureCluster(FacilityIntroduction facilityIntroduction) {
        return floorPictureClusterRepository.getAllFloorPicutre(facilityIntroduction);
    }



    // 해당 도메인 그 자체를 반환하는게 더 자연스러움
    // dto를 쓰는게 좋은 습관이긴 하나, findByFloor 같은 경우는 cluster 하나만 가져오는 목적이지?
    // 해당 서비스의 목적이 floorPictureCluster에 관한 것만 가져오겠지
    // 즉, floorPicutreCluster 서비스에서 floorPicutreCluster 도메인을 가져오는 건 사실 당연함
    // 해당 도메인에 관한 서비스, 레포지토리이기 때문에 불변성을 걱정할 필요도 없다.
    // 즉 목적성이 뚜렷하다. 목적성과 결과가 달라질 일이 없음
    // ㅇㅅㅇ....! dto 를 쓰는 이유..
    // 만약 해당 서비스가 여러 도메인을 의존하고 있고, 여러 레포지토리에서 여러개의 필드를 조합해 가져와야 할 경우엔 dto를 쓰는게 맞음
    // 비즈니스 로직에서 필요한 entity를 가져오는 작업. 이 아니라면

    // 해당 함수의 목적:findByFloor의 함수에 대한 값을 가져오는 것.
    // 헉ㅠㅠ


//    @Override
//    public void createFloorPictureList(FloorPictureListUpdateRequest floorPictureListUpdateRequest) {
//        floorPictureClusterRepository.createFloorPictureList(floorPictureListUpdateRequest.getFloorPictureCluster(),
//                floorPictureListUpdateRequest.getFloorPicture());
//    }

}


///궁금증 2

// 파사드패턴에서 직접 floorPictureClusterRepository를 주입받으면 당연히 안될 것 같았음
// 그래서 floorPictureCluster의 서비스 레이어에서만, 해당 레포지토리를 접근하는게 맞는 거 같았음
// 근데? 해당 함수가 진짜 별게 없음. 걍 jpa 함수 불러주기가 끝
// 그치


// db 값 반환이아니라

//String 객체 반환 -> .... 잘 모르개ㅔㅆ다 ..dto 매핑 해줘야 하나... 싶어