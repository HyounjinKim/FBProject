package com.firstproject.project.project.main.record;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "exercise")
@Schema(title = "칼로리 계산용 클래스")
public class Exercise {

    @Id
    @Schema(title = "운동이름")
    private String ename;
    @Schema(title = "운동칼로리")
    private float ecalories;
}
