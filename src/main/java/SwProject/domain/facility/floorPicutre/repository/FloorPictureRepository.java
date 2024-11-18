package SwProject.domain.facility.floorPicutre.repository;

import SwProject.domain.facility.floorPictureCluster.model.FloorPictureCluster;
import SwProject.domain.facility.floorPicutre.model.FloorPicture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface FloorPictureRepository extends JpaRepository<FloorPicture, CrudRepository>,  FloorPictureRepositoryCustom {
    boolean existsByFloorAndImageIndexAndFloorPictureCluster(int floor, int imageIndex, FloorPictureCluster floorPictureCluster);
}
