package SwProject.domain.RecruitmentManagement.domain.recruitment.dto;

import SwProject.domain.RecruitmentManagement.domain.recruitment.model.Recruitment;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RecruitmentPageDto {
    private List<Recruitment> recruitments;
    private long totalElements;
    private int totalPages;
}