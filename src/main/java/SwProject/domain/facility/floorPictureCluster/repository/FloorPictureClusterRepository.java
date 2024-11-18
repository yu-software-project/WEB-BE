package SwProject.domain.facility.floorPictureCluster.repository;

import SwProject.domain.facility.floorPictureCluster.model.FloorPictureCluster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;


public interface FloorPictureClusterRepository extends JpaRepository<FloorPictureCluster, CrudRepository>, FloorPictureClusterRepositoryCustom {
}
