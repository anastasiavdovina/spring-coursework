package main.service;

import main.entity.Marks;

import java.util.List;

public interface MarksService {
    List<Marks> listMarks();
    Marks findMark(long id);
    Marks addMark(Marks mark);
    void deleteById(Long id);
    List<Marks> findAllByStudentId(Long studentId);
    List<Marks> findAllByStudentIdAndSubject(Long studentId, String name);
    List<Marks> findAllByGroupIdAndSubject(Long groupId, String name);
    List<Marks> findAllByTeacherIdAndSubject(Long teacherId, String name);
    void setValueForId(Long markId, int newValue);
    void deleteAll();
}
