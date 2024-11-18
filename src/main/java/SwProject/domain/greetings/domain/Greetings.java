package SwProject.domain.greetings.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Table(name = "greetings")
public class Greetings {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "greetings_id")
    private Long id;

    @JsonIgnore
    @Column(nullable = false, unique = true, length = 255)
    private String pictureUrl;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String memo;

    public void update(String memo) {
        this.pictureUrl=pictureUrl;
        this.memo=memo;
    }

}
