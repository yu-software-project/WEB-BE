package SwProject.domain.facility.dto;

import SwProject.domain.facility.floorPictureCluster.model.FloorPictureCluster;
import SwProject.domain.facility.floorPicutre.model.FloorPicture;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class FloorPictureListUpdateRequest {
    FloorPictureCluster floorPictureCluster;
    FloorPicture floorPicture;
}
