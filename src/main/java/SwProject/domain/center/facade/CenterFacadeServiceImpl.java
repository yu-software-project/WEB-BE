package SwProject.domain.center.facade;

import SwProject.SpringSecurity.authentication.SecurityUtils;
import SwProject.domain.center.childCenter.dto.put.RequestFindWordDto;
import SwProject.domain.center.childCenter.dto.put.ResponseChildCenterDetailDto;
import SwProject.domain.center.childCenter.dto.put.ResponseChildCenterToAppDto;
import SwProject.domain.center.childCenter.model.ChildCenter;
import SwProject.domain.center.childCenter.service.ChildCenterService;
import SwProject.domain.center.likeChildCenter.dto.LikeCenterInfoDto;
import SwProject.domain.center.likeChildCenter.dto.RequestLikeCenterDto;
import SwProject.domain.center.likeChildCenter.model.LikeCenter;
import SwProject.domain.center.likeChildCenter.service.LikeCenterService;
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
public class CenterFacadeServiceImpl implements CenterFacadeService {
    private final VoluteerService voluteerService;
    private final SecurityUtils securityUtils;
    private final ChildCenterService childCenterService;
    private final LikeCenterService likeCenterService;

    @Override
    public void addLikeCenter(RequestLikeCenterDto requestLikeCenterDto) {
       Volunteer volunteer = getVolunteerPk();
       ChildCenter childCenter = childCenterService.findById(requestLikeCenterDto.getChildCenterId());

       LikeCenterInfoDto likeCenterInfoDto = LikeCenterInfoDto.builder()
               .childCenter(childCenter)
               .volunteer(volunteer)
               .build();

        //이미 관심등록 안 된 경우, 관심등록
        LikeCenter fethedLikeCenter = likeCenterService.findLikeCenter(likeCenterInfoDto);

        if(fethedLikeCenter==null){
            likeCenterService.createLikeCenter(likeCenterInfoDto);
        }
        //이미 관심등록 한 경우, 관심등록 해제
        else{
            likeCenterService.deleteLikeCenter(fethedLikeCenter);
        }

    }

    @Override
    public List<ResponseChildCenterToAppDto> searchCenterWithLike() {
        Volunteer volunteer = getVolunteerPk();
        // 현재 자원봉사자가 좋아요한 보육원 목록을 가져옴
        List<LikeCenter> likedCenters = likeCenterService.findByVolunteer(volunteer);

        // LikeCenter 엔티티를 ResponseChildCenterToAppDto로 변환
        return likedCenters.stream()
                .map(likeCenter -> ResponseChildCenterToAppDto.builder()
                        .id(likeCenter.getChildCenter().getId())
                        .centerName(likeCenter.getChildCenter().getCenterName())
                        .centerPhoneNum(likeCenter.getChildCenter().getPhoneNumId())
                        .roadAddress(likeCenter.getChildCenter().getRoadAddress())
                        .detailAddress(likeCenter.getChildCenter().getDetailAddress())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<ChildCenter> findChildCenterByWord(RequestFindWordDto requestFindWordDto) {
        return childCenterService.findChildCenterByWord(requestFindWordDto);
    }

    @Override
    public List<ResponseChildCenterDetailDto> convertResponseToWeb(List<ChildCenter> centers) {

        Volunteer volunteer = getVolunteerPk();
        List<LikeCenter> likedCenters = likeCenterService.findByVolunteer(volunteer);

        // LikeCenter 리스트를 Set으로 변환하여 쉽게 비교할 수 있도록 함
        Set<Long> likedCenterIds = likedCenters.stream()
                .map(likeCenter -> likeCenter.getChildCenter().getId())
                .collect(Collectors.toSet());

        return centers.stream()
                .map(center -> ResponseChildCenterDetailDto.builder()
                        .id(center.getId())
                        .centerName(center.getCenterName())
                        .roadAddress(center.getRoadAddress())
                        .detailAddress(center.getDetailAddress())
                        .phoneNumber(center.getPhoneNumId())
                        .isLike(likedCenterIds.contains(center.getId())) // ID가 likedCenterIds에 있는지 여부를 검사
                        .build())
                .collect(Collectors.toList());
    }

    private Volunteer getVolunteerPk() {
        Authentication authentication = securityUtils.getAuthentication();
        return voluteerService.getVolunteeerPK(authentication);
    }
}
