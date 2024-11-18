package SwProject.domain.center.likeChildCenter.model;

import SwProject.domain.center.childCenter.model.ChildCenter;
import SwProject.domain.volunteer.model.Volunteer;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Table(name = "like_center")
public class LikeCenter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_center_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "volunteer_id", nullable = false)
    private Volunteer volunteer;

    @ManyToOne
    @JoinColumn(name = "child_center_id", nullable = false)
    private ChildCenter childCenter;

}
