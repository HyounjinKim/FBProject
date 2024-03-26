package com.main.demo.main.table;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "goal")
public class Goal {
    @Id
    private int gidx;
    private String ename;
    private String id;
    private String ggol;

}
