package main.service;

import main.entity.Subjects;

import java.util.List;

public interface SubjectsService {
    List<Subjects> listSubjects();
    Subjects findSubject(long id);
    Subjects addSubject(Subjects subject);
    void deleteById(Long id);
    void deleteAll();
}
