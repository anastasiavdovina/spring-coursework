package main.service;

import main.entity.People;
import main.exception.PersonNotFoundException;
import main.exception.SubjectNotFoundException;
import main.repository.MarksRepository;
import main.repository.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PeopleServiceImpl implements PeopleService{
    private MarksRepository marksRepository;
    @Autowired
    private PeopleRepository peopleRepository;


    @Override
    public List<People> listPeople() {
        return (List<People>) peopleRepository.findAll();
    }

    @Override
    public People findPerson(long id) {
        Optional<People> optionalPerson = peopleRepository.findById(id);
        if (optionalPerson.isPresent()) {
            return optionalPerson.get();
        } else {
            throw new PersonNotFoundException("Person not found");
        }
    }

    @Override
    public People addPerson(People person) {
        return peopleRepository.save(person);
    }

    @Override
    public void deleteById(Long id) {
        if (marksRepository.existsByStudentId(id)) {
            throw new PersonNotFoundException("Some marks of this student exist, firstly delete them");
        }
        if (marksRepository.existsByTeacherId(id)) {
            throw new PersonNotFoundException("Some marks of this teacher exist, firstly delete them");
        }
        peopleRepository.deleteById(id);
    }

    @Override
    public List<People> findAllByGroupIdAndType(Long groupId, String type) {
        return peopleRepository.findAllByGroupIdAndType(groupId, type);
    }

    @Override
    public List<People> findAllBySubjectIdAndType(String subject, String type) {
        return peopleRepository.findAllBySubjectIdAndType(subject, type);
    }

    @Override
    public List<People> findAllByType(String type) {
        return peopleRepository.findAllByType(type);
    }

    @Override
    public void deleteAll() {
        List<People> people = (List<People>) peopleRepository.findAll();
        for (People p: people) {
            peopleRepository.deleteById(p.getId());
        }
    }
}
