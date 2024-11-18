package SwProject.domain.yearHistory.model;

import SwProject.domain.center.childCenter.model.ChildCenter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@Getter
@AllArgsConstructor(access= AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Table(name = "decade_year")
public class DecadeYear {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 유일한 식별자

    @NotNull
    @Column
    private int decadeStartYear;

    @OneToMany(mappedBy = "decadeYear", fetch =  FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("displayIndex ASC")
    private List<YearHistory> yearList;

    @ManyToOne
    @JoinColumn(name = "child_center_id")
    @JsonIgnore // JSON 직렬화 및 역직렬화에서 해당 필드를 무시합니다.
    private ChildCenter childCenter;
}