package SwProject.domain.scrapRecruitment.repository;

import SwProject.domain.scrapRecruitment.dto.ScrapRecruitmentDto;
import SwProject.domain.scrapRecruitment.model.ScrapRecruitment;

public interface ScrapRecruitmentRepositoryCustom {
    ScrapRecruitment findScrapRecruitment(ScrapRecruitmentDto recruitmentDto);
}
