package kg.itrun.second.demo.repository;

import kg.itrun.second.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
