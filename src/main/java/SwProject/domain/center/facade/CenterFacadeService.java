package SwProject.domain.center.facade;

import SwProject.domain.center.childCenter.dto.put.RequestFindWordDto;
import SwProject.domain.center.childCenter.dto.put.ResponseChildCenterDetailDto;
import SwProject.domain.center.childCenter.dto.put.ResponseChildCenterToAppDto;
import SwProject.domain.center.childCenter.model.ChildCenter;
import SwProject.domain.center.likeChildCenter.dto.RequestLikeCenterDto;

import java.util.List;

public interface CenterFacadeService {
    void addLikeCenter(RequestLikeCenterDto rerequestLikeCenterDto);
    List<ResponseChildCenterToAppDto> searchCenterWithLike();
    List<ChildCenter> findChildCenter(RequestFindWordDto requestFindWordDto);
    List<ResponseChildCenterDetailDto> convertResponseToWeb(List<ChildCenter> centers);
}
