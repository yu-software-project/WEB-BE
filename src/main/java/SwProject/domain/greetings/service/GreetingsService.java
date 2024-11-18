package SwProject.domain.greetings.service;

import SwProject.Exception.collections.IoException.ImageInputException;
import SwProject.domain.greetings.domain.Greetings;
import SwProject.domain.greetings.dto.UpdateGreetingsDto;

import java.io.IOException;
import java.util.List;

public interface GreetingsService {
    Greetings createGreetings() throws IOException;

    void updateGreetings(UpdateGreetingsDto updateGreetingsDto) throws ImageInputException;
    List<Greetings> getAllGreetins();
}
