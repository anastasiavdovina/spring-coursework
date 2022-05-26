package main.repository;

import main.entity.People;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PeopleRepository extends CrudRepository<People, Long> {
    boolean existsByGroupId_Id(long id);

    @Query("select p from People p where p.groupId.id= :groupId and p.type= :type")
    List<People> findAllByGroupIdAndType(Long groupId, String type);

    @Query("select m.teacherId from Marks m where m.subjectId.name= :subjectName and m.teacherId.type= :type")
    List<People> findAllBySubjectIdAndType(String subjectName, String type);

    @Query("select p from People p where p.type= :type")
    List<People> findAllByType(String type);
}
