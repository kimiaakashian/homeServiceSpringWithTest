package ir.maktab.home_services.model;

import ir.maktab.home_services.model.enums.ExpertSuggestionStatus;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
public class ExpertSuggestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Orders orders;
    private String date;
    private String time;
    private Double suggestedPrice;
    private String durationOfWork;
    @ManyToOne
    private Expert experts;

    @Enumerated(EnumType.STRING)
    private ExpertSuggestionStatus expertSuggestionStatus;
}
