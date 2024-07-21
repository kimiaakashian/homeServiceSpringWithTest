package ir.maktab.home_services.repository;

import ir.maktab.home_services.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users,Integer> {
    Optional<Users> findUserByEmail(String email);
    Optional<Users> findByPassword(String password);

    Users findById(int id);

}
