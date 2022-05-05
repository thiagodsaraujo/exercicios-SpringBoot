package br.com.github.exerciciossb.model.repositories;

import br.com.github.exerciciossb.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
