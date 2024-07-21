package ir.maktab.home_services.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
public class HomeService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.REMOVE,mappedBy = "homeServices")
    private Set<SubService> subServices =  new HashSet<>();

}
