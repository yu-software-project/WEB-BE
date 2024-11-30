package SwProject.domain.RecruitmentManagement.domain.recruitmentWaiting.dto;

import SwProject.domain.volunteer.model.Volunteer;

public record RecruitmentWaitingUserInfoDto (
        String name,
        Volunteer.Gender gender,
        String birthDate,
        String phoneNum,
        String selfIntroduction
)
{}
