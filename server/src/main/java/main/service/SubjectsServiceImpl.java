package main.service;

import main.entity.People;
import main.entity.Subjects;
import main.exception.GroupNotFoundException;
import main.exception.SubjectNotFoundException;
import main.repository.MarksRepository;
import main.repository.SubjectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectsServiceImpl implements SubjectsService{
    private MarksRepository marksRepository;
    @Autowired
    private SubjectsRepository subjectsRepository;


    @Override
    public List<Subjects> listSubjects() {
        return (List<Subjects>) subjectsRepository.findAll();
    }

    @Override
    public Subjects findSubject(long id) {
        Optional<Subjects> optionalSubject = subjectsRepository.findById(id);
        if (optionalSubject.isPresent()) {
            return optionalSubject.get();
        } else {
            throw new SubjectNotFoundException("Subject not found");
        }
    }

    @Override
    public Subjects addSubject(Subjects subject) {
         return subjectsRepository.save(subject);
    }

    @Override
    public void deleteById(Long id) {
        if (marksRepository.existsBySubjectId(id)) {
            throw new SubjectNotFoundException("Some marks for this subject exist, firstly delete them");
        }
        subjectsRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        List<Subjects> subjects = (List<Subjects>) subjectsRepository.findAll();
        for (Subjects s: subjects) {
                subjectsRepository.deleteById(s.getId());
        }
    }
}
