package SwProject.domain.scrapRecruitment.facade;

import SwProject.SpringSecurity.authentication.SecurityUtils;
import SwProject.domain.RecruitmentManagement.domain.recruitment.model.Recruitment;
import SwProject.domain.RecruitmentManagement.domain.recruitment.service.RecruitmentService;
import SwProject.domain.center.childCenter.dto.put.RequestFindWordDto;
import SwProject.domain.scrapRecruitment.dto.RequestScrapRecruitmentDto;
import SwProject.domain.scrapRecruitment.dto.ResponseRecruitmentWithScrapToAppDto;
import SwProject.domain.scrapRecruitment.dto.ScrapRecruitmentDto;
import SwProject.domain.scrapRecruitment.model.ScrapRecruitment;
import SwProject.domain.scrapRecruitment.service.ScrapRecruitmentService;
import SwProject.domain.volunteer.model.Volunteer;
import SwProject.domain.volunteer.service.VoluteerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScrapRecruitmentFacadeServiceImpl implements ScrapRecruitmentFacadeService{
    private final VoluteerService voluteerService;
    private final SecurityUtils securityUtils;
    private final RecruitmentService recruitmentService;
    private final ScrapRecruitmentService scrapRecruitmentService;

    @Override
    public void scrapRecruitment(RequestScrapRecruitmentDto requestScrapRecruitmentDto) {
        Volunteer volunteer = getVolunteerPk();
        Recruitment recruitment = recruitmentService.getRecruitmentById(requestScrapRecruitmentDto.getRecruitmentId());

        ScrapRecruitmentDto scrapRecruitmentDto = ScrapRecruitmentDto.builder()
                .recruitment(recruitment)
                .volunteer(volunteer)
                .build();

        //이미 스크랩 안 된 경우, 스크랩 등록
        ScrapRecruitment fethedScrapRecruitment = scrapRecruitmentService.findScrapRecruitment(scrapRecruitmentDto);

        if(fethedScrapRecruitment ==null){
            scrapRecruitmentService.createScrapRecruitment(scrapRecruitmentDto);
        }
        //이미 관심등록 한 경우, 관심등록 해제
        else{
            scrapRecruitmentService.deleteScrapRecruitment(fethedScrapRecruitment);
        }

    }

    @Override
    public List<ResponseRecruitmentWithScrapToAppDto> searchRecruitmentWithScrap(RequestFindWordDto requestFindWordDto) {
        Volunteer volunteer = getVolunteerPk();

        // 클라이언트로부터 받아온 검색어와 해당하는 제목을 가진 봉사공고글 리스트를 가져옴
        List<Recruitment> recruitmentsByWord = recruitmentService.findRecruitmentByWord(requestFindWordDto);

        // 현재 자원봉사자가 스크랩한 봉사공고 목록을 가져옴
        List<ScrapRecruitment> scrapRecruitments = scrapRecruitmentService.findByVolunteer(volunteer);

        // 스크랩한 봉사공고 ID들을 Set으로 변환
        Set<Long> scrappedRecruitmentIds = extractScrappedRecruitmentIds(scrapRecruitments);

        // 각 봉사공고에 대해 사용자가 스크랩했는지 여부를 포함한 DTO 생성
        return recruitmentsByWord.stream()
                .map(recruitment -> toResponseDto(recruitment, scrappedRecruitmentIds))
                .collect(Collectors.toList());
    }

    @Override
    public List<ResponseRecruitmentWithScrapToAppDto> getScrappedRecruitments() {
        Volunteer volunteer = getVolunteerPk();

        // 현재 자원봉사자가 스크랩한 봉사공고 목록을 가져옴
        List<ScrapRecruitment> scrapRecruitments = scrapRecruitmentService.findByVolunteer(volunteer);

        // 스크랩한 봉사공고 ID들을 Set으로 변환
        Set<Long> scrappedRecruitmentIds = extractScrappedRecruitmentIds(scrapRecruitments);

        // 스크랩한 봉사공고 리스트를 가져옴
        List<Recruitment> scrappedRecruitments = scrapRecruitments.stream()
                .map(ScrapRecruitment::getRecruitment)
                .collect(Collectors.toList());

        // 각 봉사공고에 대해 사용자가 스크랩했는지 여부를 포함한 DTO 생성
        return scrappedRecruitments.stream()
                .map(recruitment -> toResponseDto(recruitment, scrappedRecruitmentIds))
                .collect(Collectors.toList());
    }

    private Volunteer getVolunteerPk() {
        Authentication authentication = securityUtils.getAuthentication();
        return voluteerService.getVolunteeerPK(authentication);
    }

    private Set<Long> extractScrappedRecruitmentIds(List<ScrapRecruitment> scrapRecruitments) {
        return scrapRecruitments.stream()
                .map(scrap -> scrap.getRecruitment().getId())
                .collect(Collectors.toSet());
    }

    private ResponseRecruitmentWithScrapToAppDto toResponseDto(Recruitment recruitment, Set<Long> scrappedRecruitmentIds) {
        return ResponseRecruitmentWithScrapToAppDto.builder()
                .id(recruitment.getId())
                .recruitmentName(recruitment.getName())
                .childCenterName(recruitment.getChildCenter().getCenterName())
                .recruitmentStartDate(recruitment.getRecruitmentStartDate())
                .isScrap(scrappedRecruitmentIds.contains(recruitment.getId()))
                .build();
    }

}
