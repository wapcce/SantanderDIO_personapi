package santander.dio.personapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import santander.dio.personapi.entity.Person;

public interface PersonRepository extends JpaRepository<Person,Long> {
}
