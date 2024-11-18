package SwProject.domain.center.childCenter.service;

import SwProject.Exception.collections.business.CenterNotFoundException;
import SwProject.Exception.collections.business.ChildCenterAlreadyExitsException;
import SwProject.Exception.collections.business.DuplicateUniqueKeyException;
import SwProject.businessProcess.auth.web.dto.WebSignUpDto;
import SwProject.businessProcess.facade.dto.CenterForeignKeyDto;
import SwProject.businessProcess.facade.dto.ManagerRegisterDto;
import SwProject.domain.center.childCenter.dto.put.RequestFindWordDto;
import SwProject.domain.center.childCenter.repository.ChildCenterRepository;
import SwProject.domain.center.childCenter.model.ChildCenter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChildCenterServiceImpl implements ChildCenterService {
    private final ChildCenterRepository childCenterRepository;

    @Override
    public void checkExits(WebSignUpDto.centerInfo childCenterDto) {
        Optional<ChildCenter> found = childCenterRepository.findByPhoneNumId(childCenterDto.getPhoneNum());
        if (found.isPresent()) throw new ChildCenterAlreadyExitsException();
    }

    @Override
    public ChildCenter getChildCenterPk(Authentication authentication) {
        return childCenterRepository.getChildCenterPk(authentication);
    }

    @Override
    public List<ChildCenter> findChildCenter(RequestFindWordDto requestDto) {

        List<ChildCenter> centers = childCenterRepository.findByRoadAddressContaining(requestDto.getFindWord());

        if(!centers.isEmpty()) return centers;

        centers = childCenterRepository.findByCenterNameContaining(requestDto.getFindWord());

        if(centers.isEmpty()) throw new CenterNotFoundException();

        return centers;

    }

    @Override
    public ChildCenter findById(Long id) {
        ChildCenter childCenter = childCenterRepository.findById(id)
                .orElseThrow(() -> new CenterNotFoundException());

        return childCenter;
    }

    @Override
    public ManagerRegisterDto register(WebSignUpDto.centerInfo centerInfo, CenterForeignKeyDto centerForeignKeyDto, String s3url) {

        ChildCenter childCenter = ChildCenter.builder()
                .phoneNumId(centerInfo.getPhoneNum())
                .ceoName(centerInfo.getCeoName())
                .centerName(centerInfo.getCenterName())
                .roadAddress(centerInfo.getRoadAddress())
                .detailAddress(centerInfo.getDetailAddress())
                .certificate(s3url)
                .facilityIntroduction(centerForeignKeyDto.getFacility())
                .greetings(centerForeignKeyDto.getGreetings())
                .routeInfo(centerForeignKeyDto.getRouteInfo())
                .build();

        try {
            childCenterRepository.save(childCenter);
        } catch (DataIntegrityViolationException e) {
            // 예외가 발생했을 때 처리할 코드
            throw new DuplicateUniqueKeyException();
        }

        ManagerRegisterDto managerRegisterDto = ManagerRegisterDto.builder()
                .childCenter(childCenter)
                .build();

        return managerRegisterDto;
    }


}
