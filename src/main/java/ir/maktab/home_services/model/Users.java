package ir.maktab.home_services.model;
import lombok.*;
import jakarta.persistence.*;
import lombok.experimental.SuperBuilder;
import org.antlr.v4.runtime.misc.NotNull;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;

    @Column(nullable = false)
    private String email;
    private String password;
    private String DateOfRegistration;

}
