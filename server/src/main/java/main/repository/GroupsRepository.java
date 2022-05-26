package main.repository;

import main.entity.Groups;
import org.springframework.data.repository.CrudRepository;

public interface GroupsRepository extends CrudRepository<Groups, Long> {
}
