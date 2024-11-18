package SwProject.domain.facility.floorPicutre.model;

import SwProject.domain.facility.floorPictureCluster.model.FloorPictureCluster;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@AllArgsConstructor(access =  AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Table(name = "floor_picture")
public class FloorPicture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "floor_picture_id")
    @JsonIgnore
    private Long floorPictureId;

    @Column(nullable = false)
    private int floor; //해당 이미지의 층수를 나타낼 데이터

    @Column(nullable = false, length = 255)
    private String purpose;

    @Column(nullable = false)
    private int imageIndex; //해당 사진이 floor층에서 몇 번째 사진인지 나타낼 데이터 정보(0~3으로 표시)

    @Column(nullable = false, unique = true, length = 255)
    private String pictureUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "floor_picture_cluster_id")
    @JsonIgnore
    private FloorPictureCluster floorPictureCluster;
}

