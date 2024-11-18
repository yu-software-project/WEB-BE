package SwProject.businessProcess.auth.service.Facade.app;

import SwProject.SpringSecurity.jwt.dto.AllJwtTokenDto;
import SwProject.businessProcess.auth.app.dto.AppSignInDto;
import SwProject.businessProcess.auth.app.dto.AppSignUpDto;
import SwProject.businessProcess.auth.app.dto.CheckExitsVoluteerDto;
import SwProject.businessProcess.util.UtilService;
import SwProject.domain.volunteer.service.VoluteerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthAppFacadeServiceImpl implements AuthAppFacadeService{
    private final VoluteerService voluteerService;
    private final UtilService utilService;

    @Override
    @Transactional
    public void authSignUp(AppSignUpDto appSignUpDto) {
        CheckExitsVoluteerDto checkExits = CheckExitsVoluteerDto.builder()
                .email(appSignUpDto.getEmailId())
                .phonNum(appSignUpDto.getPhoneNum())
                .build();

        //1. 해당 봉사자 존재 여부 확인
        voluteerService.checkExits(checkExits);
        //2. 비밀번호 일치 여부
        utilService.checkPassword(appSignUpDto.getPassword(), appSignUpDto.getPasswordCheck());
        //2. 회원가입
        voluteerService.signUp(appSignUpDto);
    }

    @Override
    public AllJwtTokenDto authSignIn(AppSignInDto appSignInDto) {
        return voluteerService.signIn(appSignInDto);
    }
}
