package SwProject.domain.yearHistory.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@AllArgsConstructor(access= AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Table(name = "year_history")
public class YearHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 유일한 식별자

    @NotNull
    @Column
    private int displayIndex; // 해당 데이터의 인덱스를 나타냄-> 수정 및 get 요청에서 요긴하게 쓰일 예정

    @NotNull
    @Column
    private int year;

    @Column(nullable = false, length = 255)
    private String memo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "decade_year_id")
    @JsonIgnore // JSON 직렬화 및 역직렬화에서 해당 필드를 무시합니다.
    private DecadeYear decadeYear;

    public void update(int year, String memo){
        this.year=year;
        this.memo=memo;
    }

}
