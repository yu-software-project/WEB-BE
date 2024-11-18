package SwProject.domain.greetings.dto;

import SwProject.domain.greetings.domain.Greetings;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UpdateGreetingsDto {
    Greetings oldGreeting;
    Greetings newGreeting;
}
