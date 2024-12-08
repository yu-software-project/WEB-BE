package SwProject.domain.center.childCenter.model;

import SwProject.Exception.collections.business.BusinessException;
import SwProject.domain.facility.facilityIntroduction.model.FacilityIntroduction;
import SwProject.domain.greetings.domain.Greetings;
import SwProject.domain.routeInfo.domain.RouteInfo;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Table(name = "child_center")
public class ChildCenter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "child_center_id")
    private Long id;

    @Column(nullable = false, unique = true, length = 255)
    private String phoneNumId;

    @Column(nullable = false, length = 255)
    private String ceoName;

    @Column(nullable = false, unique = true, length = 255)
    private String centerName;

    @Column(nullable = false, unique = true, length = 255)
    private String roadAddress;

    @Column(nullable = false, unique = true, length = 255)
    private String detailAddress;

    @Column(nullable = false, unique = true, length = 255)
    private String certificate;

    @OneToOne(cascade = CascadeType.REMOVE) //해당 db삭제시, 연결된 db 모두 삭제됨
    @JoinColumn(name = "facility_introduction_id")
    private FacilityIntroduction facilityIntroduction;

    @OneToOne(cascade = CascadeType.REMOVE) //해당 db삭제시, 연결된 db 모두 삭제됨
    @JoinColumn(name = "greetings_id")
    private Greetings greetings;

    @OneToOne(cascade = CascadeType.REMOVE) //해당 db삭제시, 연결된 db 모두 삭제됨
    @JoinColumn(name = "route_info_id")
    private RouteInfo routeInfo;

    public String getTotalAddress() {
        return roadAddress+detailAddress;
    }


}
