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
public class SubService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private HomeService homeServices;
    private String subServiceName;
    private double basePrice;
    private String description;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.REMOVE,mappedBy = "subServices")
    private Set<Orders> orders =  new HashSet<>();

    @OneToMany(orphanRemoval = true, cascade = CascadeType.REMOVE,mappedBy = "subServices")
    private Set<ExpertSubService> expertSubServices =  new HashSet<>();

}
