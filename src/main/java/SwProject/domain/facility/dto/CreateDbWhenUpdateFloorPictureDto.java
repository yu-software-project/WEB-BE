package SwProject.domain.facility.dto;

import SwProject.domain.facility.facilityIntroduction.model.FacilityIntroduction;
import SwProject.domain.facility.floorPicutre.dto.FloorPictureDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.Authentication;

@Data
@AllArgsConstructor
@Builder
public class CreateDbWhenUpdateFloorPictureDto {
    FloorPictureDto floorPictureDto;
    FacilityIntroduction facilityIntroduction;
    Authentication authentication;
}
