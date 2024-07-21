package ir.maktab.home_services.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
public class ExpertSubService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.REMOVE)
    private Expert experts;
    @ManyToOne(cascade = CascadeType.REMOVE)
    private SubService subServices;
}
