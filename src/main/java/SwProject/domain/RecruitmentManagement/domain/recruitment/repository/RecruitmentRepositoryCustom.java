package SwProject.domain.RecruitmentManagement.domain.recruitment.repository;

import SwProject.domain.RecruitmentManagement.domain.recruitment.model.Recruitment;
import SwProject.domain.center.childCenter.model.ChildCenter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RecruitmentRepositoryCustom {
    Page<Recruitment> findAllWithPagination(ChildCenter childCenter, Pageable pageable);
    Page<Recruitment> findByNameWithPagination(String recruitmentName, ChildCenter fetchedChildCenter, Pageable pageable);
}
