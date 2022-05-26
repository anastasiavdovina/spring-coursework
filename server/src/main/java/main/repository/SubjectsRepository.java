package main.repository;

import main.entity.Subjects;
import org.springframework.data.repository.CrudRepository;

public interface SubjectsRepository extends CrudRepository<Subjects, Long> {
}
