package SwProject.domain.facility.floorSize.service;

import SwProject.domain.center.childCenter.model.ChildCenter;
import SwProject.domain.facility.dto.FacilityFloorSizeUpdateRequest;
import SwProject.domain.facility.facilityIntroduction.model.FacilityIntroduction;
import SwProject.domain.facility.facilityIntroduction.repository.FacilityIntroRepository;
import SwProject.domain.facility.floorSize.dto.UpdateFloorSizeDto;
import SwProject.domain.facility.floorSize.model.FloorSize;
import SwProject.domain.facility.floorSize.repository.FloorSizeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FloorSizeServiceImpl implements FloorSizeService{
    private final FacilityIntroRepository facilityIntroRepository;
    private final FloorSizeRepository floorSizeRepository;

    @Override
    public FacilityFloorSizeUpdateRequest createFloorSize(UpdateFloorSizeDto updateFloorSizeDto, Authentication authentication) {
        //1. 해당 이메일로 관리자db->보육원db로 외래키를 타고 들어가 보육원 필드에 저장된 시설소개의 기본키 가져오기
         FacilityIntroduction facilityIntroduction  = facilityIntroRepository.getFacilityPk(authentication);

        //2. 가져온 기본키와 외부에서 받은 updateFloorSizeDto로 floorPicutre db 저장하기
        FloorSize floorSize = FloorSize.builder()
                .floor(updateFloorSizeDto.getFloor())
                .area(updateFloorSizeDto.getArea())
                .purpose(updateFloorSizeDto.getPurpose())
                .remark(updateFloorSizeDto.getRemark())
                .displayIndex(updateFloorSizeDto.getDisplayIndex())
                .facilityIntroduction(facilityIntroduction)
                .build();

        //3. 층별db에 내용 저장
        floorSizeRepository.save(floorSize);

        //4. 양방향 매핑 위해 dto 생성
        FacilityFloorSizeUpdateRequest facilityFloorSizeUpdateRequest = FacilityFloorSizeUpdateRequest.builder()
                .facilityIntroduction(facilityIntroduction)
                .floorSize(floorSize)
                .build();

        return facilityFloorSizeUpdateRequest;
    }

    public FloorSize isExitsFloorSize(int displayIndex, ChildCenter fetchedChildCenter) {
        return floorSizeRepository.isExitsFloorSize(displayIndex, fetchedChildCenter);
    }

    @Override
    public FacilityFloorSizeUpdateRequest updateFloorSize(FloorSize oldFloorSize, UpdateFloorSizeDto newFloorSizeDto, Authentication authentication) {

        //기존 객체 제거
        floorSizeRepository.delete(oldFloorSize);

        FloorSize newFloorSize = FloorSize.builder()
                .floor(newFloorSizeDto.getFloor())
                .area(newFloorSizeDto.getArea())
                .purpose(newFloorSizeDto.getPurpose())
                .remark(newFloorSizeDto.getRemark())
                .displayIndex(newFloorSizeDto.getDisplayIndex())
                .facilityIntroduction(oldFloorSize.getFacilityIntroduction())
                .build();

        floorSizeRepository.save(newFloorSize);


        FacilityFloorSizeUpdateRequest facilityFloorSizeUpdateRequest = FacilityFloorSizeUpdateRequest.builder()
                .facilityIntroduction(oldFloorSize.getFacilityIntroduction())
                .floorSize(newFloorSize)
                .build();

        return facilityFloorSizeUpdateRequest;
    }

    @Override
    public List<FloorSize> getAllFloorSize(FacilityIntroduction facilityIntroduction) {
        return floorSizeRepository.findByFacilityIntroductionOrderByDisplayIndexAsc(facilityIntroduction);
    }

}
