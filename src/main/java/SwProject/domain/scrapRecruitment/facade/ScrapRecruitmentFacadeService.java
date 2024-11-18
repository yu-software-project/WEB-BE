package SwProject.domain.scrapRecruitment.facade;

import SwProject.domain.center.childCenter.dto.put.RequestFindWordDto;
import SwProject.domain.scrapRecruitment.dto.RequestScrapRecruitmentDto;
import SwProject.domain.scrapRecruitment.dto.ResponseRecruitmentWithScrapToAppDto;

import java.util.List;

public interface ScrapRecruitmentFacadeService {
    void scrapRecruitment(RequestScrapRecruitmentDto requestScrapRecruitmentDto);
    List<ResponseRecruitmentWithScrapToAppDto> searchRecruitmentWithScrap(RequestFindWordDto requestFindWordDto);
    List<ResponseRecruitmentWithScrapToAppDto> getScrappedRecruitments();
}
