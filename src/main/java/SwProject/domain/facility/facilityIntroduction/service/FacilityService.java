package SwProject.domain.facility.facilityIntroduction.service;

import SwProject.domain.facility.dto.FacilityFloorSizeUpdateRequest;
import SwProject.domain.facility.facilityIntroduction.model.FacilityIntroduction;

public interface FacilityService {
    FacilityIntroduction createFacilityIntroduction();
    void updateFacilityFloorSizeList(FacilityFloorSizeUpdateRequest facilityFloorSizeUpdateRequest);
}
