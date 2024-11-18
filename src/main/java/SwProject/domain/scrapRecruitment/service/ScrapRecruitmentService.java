package SwProject.domain.scrapRecruitment.service;


import SwProject.domain.scrapRecruitment.dto.ScrapRecruitmentDto;
import SwProject.domain.scrapRecruitment.model.ScrapRecruitment;
import SwProject.domain.volunteer.model.Volunteer;

import java.util.List;

public interface ScrapRecruitmentService {
    ScrapRecruitment findScrapRecruitment(ScrapRecruitmentDto scrapRecruitmentDto);
    void createScrapRecruitment(ScrapRecruitmentDto fethedScrapRecruitmentDto);
    void deleteScrapRecruitment(ScrapRecruitment srapRecruitment);
    List<ScrapRecruitment> findByVolunteer(Volunteer volunteer);
    List<ScrapRecruitment> findTop3ByVolunteerOrderByIdDesc(Volunteer volunteer);
}
