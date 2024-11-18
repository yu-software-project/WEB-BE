package SwProject.domain.facility.dto;

import SwProject.domain.facility.facilityIntroduction.model.FacilityIntroduction;
import SwProject.domain.facility.floorSize.model.FloorSize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class FacilityFloorSizeUpdateRequest {
    FacilityIntroduction facilityIntroduction;
    FloorSize floorSize;
}
