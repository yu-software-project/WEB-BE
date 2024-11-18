package SwProject.domain.volunteer.service;

import SwProject.Exception.collections.business.UserAlreadyExistsException;
import SwProject.Exception.collections.business.PasswordNotMatchException;
import SwProject.Exception.collections.business.UsernameNotFoundException;
import SwProject.SpringSecurity.jwt.JwtTokenProvider;
import SwProject.SpringSecurity.jwt.dto.AccessTokenDto;
import SwProject.SpringSecurity.jwt.dto.AllJwtTokenDto;
import SwProject.SpringSecurity.jwt.dto.RefreshTokenDto;
import SwProject.businessProcess.auth.app.dto.AppSignInDto;
import SwProject.businessProcess.auth.app.dto.AppSignUpDto;
import SwProject.businessProcess.auth.app.dto.CheckExitsVoluteerDto;
import SwProject.businessProcess.util.UtilService;
import SwProject.domain.volunteer.model.Volunteer;
import SwProject.domain.volunteer.repository.VolunteerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class VoluteerServiceImpl implements VoluteerService {
    private final VolunteerRepository volunteerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UtilService utilService;

    @Override
    public void checkExits(CheckExitsVoluteerDto checkExits ) {
        //해당 멤버가 존재하는지 확인
        Optional<Volunteer> foundbyEmail = this.volunteerRepository.findByEmailId(checkExits.getEmail());
        Optional<Volunteer> foundByPhoneNUm = this.volunteerRepository.findByPhoneNum(checkExits.getPhonNum());
        if(foundByPhoneNUm.isPresent() || foundbyEmail.isPresent()) throw new UserAlreadyExistsException();
    }

    @Override
    public void signUp(AppSignUpDto appSignUpDto) {
        String encodePassword = passwordEncoder.encode(appSignUpDto.getPassword());
        LocalDate localDateBirth = LocalDate.parse(appSignUpDto.getBirth(), DateTimeFormatter.ofPattern("yyMMdd"));

        Volunteer volunteer = Volunteer.builder()
                .name(appSignUpDto.getName())
                .emailId(appSignUpDto.getEmailId())
                .password(encodePassword)
                .phoneNum(appSignUpDto.getPhoneNum())
                .birth(localDateBirth)
                .role(Volunteer.UserRoleEnum.USER)
                .build();

        volunteer.setGender(appSignUpDto.isGender());
        volunteerRepository.save(volunteer);

    }

    @Override
    public AllJwtTokenDto signIn(AppSignInDto appSignInDto) {
        Optional<Volunteer> foundVoluteer = volunteerRepository.findByEmailId(appSignInDto.getEmail());

        if(foundVoluteer.isEmpty()) throw new UsernameNotFoundException();
        if(!passwordEncoder.matches(appSignInDto.getPassword(), foundVoluteer.get().getPassword())){
            throw new PasswordNotMatchException();
        }

        AccessTokenDto accessTokenDto = jwtTokenProvider.createAccessToken(foundVoluteer.get().getPhoneNum(), String.valueOf(foundVoluteer.get().getRole()));
        RefreshTokenDto refreshTokenDto = jwtTokenProvider.createRefreshToken(foundVoluteer.get().getPhoneNum(), String.valueOf(foundVoluteer.get().getRole()));

        return AllJwtTokenDto
                .builder()
                .accessTokenDto(accessTokenDto)
                .refreshTokenDto(refreshTokenDto)
                .build();
    }

    @Override
    public Volunteer getVolunteeerPK(Authentication authentication) {
        return volunteerRepository.getVolunteerPk(authentication);
    }


}
