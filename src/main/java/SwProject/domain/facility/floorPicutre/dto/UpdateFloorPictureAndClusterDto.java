package SwProject.domain.facility.floorPicutre.dto;

import SwProject.domain.facility.floorPictureCluster.model.FloorPictureCluster;
import SwProject.domain.facility.floorPicutre.model.FloorPicture;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdateFloorPictureAndClusterDto {
    FloorPicture floorPicture;
    FloorPictureCluster floorPictureCluster;
}
