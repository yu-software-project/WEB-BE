package SwProject.domain.greetings.service;

import SwProject.Exception.collections.business.DuplicateUniqueKeyException;
import SwProject.businessProcess.util.UtilService;
import SwProject.config.constant.DbInitConstants;
import SwProject.domain.greetings.domain.Greetings;
import SwProject.domain.greetings.dto.UpdateGreetingsDto;
import SwProject.domain.greetings.repository.GreetingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GreetingsServiceImpl implements GreetingsService{
    private final GreetingRepository greetingRepository;
    private final UtilService utilService;

    @Override
    public Greetings createGreetings() throws IOException { //회원가입 시, 이미 연동된 db를 생성함
        String imageUrl = utilService.getInitImagePath();

        Greetings greetings = Greetings.builder()
                .pictureUrl(imageUrl)
                .memo(DbInitConstants.greetingsInitMessage)
                .build();

        try {
            greetingRepository.save(greetings);
        } catch (DataIntegrityViolationException e) {
            // 유니크 키 겹쳤을 때 에러 발생
            throw new DuplicateUniqueKeyException();
        }

        return greetings;
    }

    @Override
    public void updateGreetings(UpdateGreetingsDto updateGreetingsDto) {
        greetingRepository.delete(updateGreetingsDto.getOldGreeting());
        greetingRepository.save(updateGreetingsDto.getNewGreeting());
    }

    @Override
    public List<Greetings> getAllGreetins() {
        return greetingRepository.findAll();
    }

}
