package main.repository;

import main.entity.Groups;
import main.entity.Marks;
import main.entity.People;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MarksRepository extends CrudRepository<Marks, Long>{
    boolean existsBySubjectId(long id);
    boolean existsByTeacherId(long id);
    boolean existsByStudentId(long id);

    @Query("select m from Marks m where m.studentId.id= :studentId")
    List<Marks> findAllByStudentId(Long studentId);

    @Query("select m from Marks m where m.subjectId.name= :name and m.studentId.id= :studentId")
    List<Marks> findAllByStudentIdAndSubject(Long studentId, String name);

    @Query("select m from Marks m where m.studentId.groupId.id= :groupId and m.subjectId.name= :name")
    List<Marks> findAllByGroupIdAndSubject(Long groupId, String name);

    @Query("select m from Marks m where m.subjectId.name= :name and m.teacherId.id= :teacherId")
    List<Marks> findAllByTeacherIdAndSubject(Long teacherId, String name);
}
