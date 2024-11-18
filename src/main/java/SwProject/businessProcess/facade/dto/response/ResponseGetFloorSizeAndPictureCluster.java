package SwProject.businessProcess.facade.dto.response;

import SwProject.domain.facility.floorPictureCluster.model.FloorPictureCluster;
import SwProject.domain.facility.floorSize.model.FloorSize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseGetFloorSizeAndPictureCluster {
    List<FloorSize> floorSizeList;
    List<FloorPictureCluster> floorPictureClusterList;
}
