package main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDatabaseRepository extends JpaRepository<UserDatabase, Long> {
}
