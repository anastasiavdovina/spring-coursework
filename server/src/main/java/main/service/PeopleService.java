package main.service;

import main.entity.People;

import java.util.List;

public interface PeopleService {
    List<People> listPeople();
    People findPerson(long id);
    People addPerson(People person);
    void deleteById(Long id);
    List<People> findAllByGroupIdAndType(Long groupId, String type);
    List<People> findAllBySubjectIdAndType(String subject, String type);
    List<People> findAllByType(String type);
    void deleteAll();
}
