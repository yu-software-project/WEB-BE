package SwProject.domain.center.likeChildCenter.dto;

import SwProject.domain.center.childCenter.model.ChildCenter;
import SwProject.domain.volunteer.model.Volunteer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LikeCenterInfoDto {
    Volunteer volunteer;
    ChildCenter childCenter;
}
