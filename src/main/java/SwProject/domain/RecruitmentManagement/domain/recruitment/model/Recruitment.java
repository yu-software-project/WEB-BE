package SwProject.domain.RecruitmentManagement.domain.recruitment.model;

import SwProject.domain.RecruitmentManagement.domain.recruitment.dto.RequestRecruitmentDto;
import SwProject.domain.center.childCenter.model.ChildCenter;
import SwProject.domain.RecruitmentManagement.domain.recruitmentWaiting.model.RecruitmentWaiting;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static SwProject.config.constant.DbInitConstants.initRecruitmentDetailInfo;

@Entity
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Table(name = "recruitment")
public class Recruitment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String name; //봉사이름 -> 나중에 상세정보를 찾을 때 해당 제목을 기준으로 찾을 것

    @Column(nullable = false)
    private LocalDate recruitmentStartDate; //모집시작일 -> 기본값 오늘 날짜로 설정

    @Column(nullable = false)
    private LocalDate recruitmentEndDate; //모집종료일

    @Column(nullable = false)
    private boolean isTimeExits; // *시간 여부

    @Column(nullable = false, length = 255)
    private LocalTime startTime; //봉사시작시간

    @Column(nullable = false, length = 255)
    private LocalTime endTime; //봉사종료시간

    @Column(nullable = false, length = 255)
    private LocalDate startDate; //봉사시작일

    @Column(nullable = false, length = 255)
    private LocalDate endDate; //봉사종료일

    @Column(nullable = false)
    private boolean isRepeatedDate; // *반복 여부

    @Embedded
    @Column(nullable = false)
    private DaysOfWeek repeatedDays; // 요일별 반복 여부

    @Column(nullable = false)
    private int view; //조회

    @Column(nullable = false)
    private int totalApplicants; //모집인원

    @Column(nullable = false)
    private int currentApplicants; //현재 신청인원 -> 기본값 : 0

    @Column(nullable = false, columnDefinition = "TEXT")
    private String detailInfo; //해당 봉사 공고의 상제 정보. -> 초기값 : 상수변수 만들어야 함

    @OneToMany(mappedBy = "recruitment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecruitmentWaiting> recruitmentWaitings;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "child_center_id")
    private ChildCenter childCenter;

    // currentApplicants 증가 메서드
    public void incrementCurrentApplicants() {
        if (this.currentApplicants < this.totalApplicants) {
            this.currentApplicants++;
        } else {
            throw new IllegalStateException("Cannot accept more volunteers than the total applicants allowed.");
        }
    }

    // currentApplicants 감소 메서드
    public void decrementCurrentApplicants() {
        if (this.currentApplicants > 0) {
            this.currentApplicants--;
        } else {
            throw new IllegalStateException("Cannot have fewer than zero current applicants.");
        }
    }

    public void updateRecruitment(RequestRecruitmentDto updateDto, DaysOfWeek daysOfWeek) {
        this.name=updateDto.getName();
        this.recruitmentEndDate=updateDto.getRecruitmentEndDate();
        this.isTimeExits = updateDto.isTimeExits();
        this.startTime=updateDto.getStartTime();
        this.endTime=updateDto.getEndTime();
        this.startDate=updateDto.getStartDate();
        this.endDate=updateDto.getEndDate();
        this.totalApplicants=updateDto.getTotalApplicants();
        this.repeatedDays=daysOfWeek;
        String detailInfo = updateDto.getDetailInfo();
        if(detailInfo==null || detailInfo==""){
            detailInfo=initRecruitmentDetailInfo;
        }
        this.detailInfo=detailInfo;
    }

}


//isRepeatedDate(반복설정) true인 경우 -> 요일별 반복 여부 List에 클라이언트의 값을 입력합니다.
//isRepeatedDate(반복설정) false인 경우 -> 모든 요일이 false인 List를 값으로 넣습니다.

//isTimeExits(시간설정) true인 경우 -> 봉사 시작 시간 (startTime)과 봉사 종료 시간 (endTime)에 값을 입력합니다.
//isTimeExits(시간설정) false인 경우 -> 봉사 시작 시간과 봉사 종료 시간에 값을 입력하지 않으며, 임의의 음수 값을 설정할 수 있습니다.

