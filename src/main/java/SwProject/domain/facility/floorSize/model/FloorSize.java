package SwProject.domain.facility.floorSize.model;

import SwProject.domain.facility.facilityIntroduction.model.FacilityIntroduction;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@AllArgsConstructor(access =  AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Table(name = "floor_size")
public class FloorSize {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "floor_size_id")
    @JsonIgnore
    private Long id;

    @Column(nullable = false, length = 255)
    private String floor;

    @Column(nullable = false, length = 255)
    private int area;

    @Column(nullable = false, length = 255)
    private String purpose;

    @Column(nullable = false, length = 255)
    private String remark;

    @Column(nullable = false, length = 255)
    private int displayIndex; //실제 표에 나타날 위치인덱스

    @ManyToOne
    @JoinColumn(name = "facility_introduction_id")
    @JsonIgnore
    private FacilityIntroduction facilityIntroduction; //외래키가 있는 현재 테이블이 둘 관계의 주인이다!
}
