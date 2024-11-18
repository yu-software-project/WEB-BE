package SwProject.domain.facility.floorSize.service;

import SwProject.domain.center.childCenter.model.ChildCenter;
import SwProject.domain.facility.dto.FacilityFloorSizeUpdateRequest;
import SwProject.domain.facility.facilityIntroduction.model.FacilityIntroduction;
import SwProject.domain.facility.floorSize.dto.UpdateFloorSizeDto;
import SwProject.domain.facility.floorSize.model.FloorSize;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface FloorSizeService {
    FacilityFloorSizeUpdateRequest createFloorSize(UpdateFloorSizeDto updateFloorSizeDto, Authentication authentication);
    FloorSize  isExitsFloorSize(int displayIndex, ChildCenter fetchedChildCenter);
    FacilityFloorSizeUpdateRequest updateFloorSize(FloorSize oldFloorSize, UpdateFloorSizeDto newFloorSizeDto, Authentication authentication);
    List<FloorSize> getAllFloorSize(FacilityIntroduction facilityIntroduction);
}
