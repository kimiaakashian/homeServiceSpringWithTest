package ir.maktab.home_services.model;

import ir.maktab.home_services.model.enums.ExpertStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class Expert extends Users{
    @Enumerated(EnumType.STRING)
    private ExpertStatus expertStatus;
    private Double expertCredit;
    private Double averageScore;
    private byte[] picture;
    private String fieldOfExpertise;
}
