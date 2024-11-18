package SwProject.domain.facility.floorPictureCluster.model;

import SwProject.domain.facility.facilityIntroduction.model.FacilityIntroduction;
import SwProject.domain.facility.floorPicutre.model.FloorPicture;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Table(name = "floor_picture_cluster")
public class FloorPictureCluster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "floor_picture_cluster_id")
    @JsonIgnore
    private Long id;

    @Column(nullable = false)
    private int floor;

    @OneToMany(mappedBy = "floorPictureCluster", fetch = FetchType.LAZY)
    private List<FloorPicture> floorPictureList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "facility_introduction_id")
    @JsonIgnore
    private FacilityIntroduction facilityIntroduction;
}
