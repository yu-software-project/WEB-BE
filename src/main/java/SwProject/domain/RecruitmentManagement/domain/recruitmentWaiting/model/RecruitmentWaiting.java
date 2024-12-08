package SwProject.domain.RecruitmentManagement.domain.recruitmentWaiting.model;

import SwProject.domain.RecruitmentManagement.domain.recruitment.model.Recruitment;
import SwProject.domain.volunteer.model.Volunteer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Table(name = "recruitment_waiting")
public class RecruitmentWaiting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recruitment_waiting_id")
    private Long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "volunteer_id")
    private Volunteer volunteer;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "recruitment_id")
    private Recruitment recruitment;

    @Column
    private String selfIntroduction; //한 줄 소개

}
