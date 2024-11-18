package SwProject.domain.volunteer.model;

import SwProject.domain.RecruitmentManagement.domain.recruitmentWaiting.model.RecruitmentWaiting;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Table(name = "volunteer")
public class Volunteer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "volunteer_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Column(nullable = false, unique = true, length = 255)
    private String emailId;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(nullable = false, unique = true, length = 255)
    private String phoneNum;

    @Column(nullable = false)
    private LocalDate birth;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private UserRoleEnum role;

    @OneToMany(mappedBy = "volunteer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecruitmentWaiting> recruitmentWaitings;

    public enum UserRoleEnum {
        USER, ADMIN
    }

    public enum Gender {
        MAIL, FEMAIL
    }

    public void setGender(boolean gender) {
     if(gender) this.gender = Gender.MAIL;
     else this.gender = Gender.FEMAIL;
    }

}
