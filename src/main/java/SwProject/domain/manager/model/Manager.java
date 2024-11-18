package SwProject.domain.manager.model;

import SwProject.domain.center.childCenter.model.ChildCenter;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Table(name = "manager")
public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "manager_id")
    private Long id;

    @Column(nullable = false, unique = true, length = 255)
    private String emailId;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(nullable = false, unique = true, length = 255)
    private String phoneNum;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private ManagerRoleEnum role;

    @OneToOne(cascade = CascadeType.REMOVE) //해당 db삭제시, 연결된 db 모두 삭제됨
    @JoinColumn(name = "child_center_id")
    private ChildCenter childCenter;

    public enum ManagerRoleEnum {
        User, ADMIN
    }

}
