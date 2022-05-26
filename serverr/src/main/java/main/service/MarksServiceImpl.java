package main.service;

import main.entity.Marks;

import main.exception.GroupNotFoundException;
import main.exception.MarkNotFoundException;
import main.repository.MarksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarksServiceImpl implements MarksService {
    @Autowired
    private MarksRepository marksRepository;

    @Override
    public List<Marks> listMarks() {
        return (List<Marks>) marksRepository.findAll();
    }

    @Override
    public Marks findMark(long id) {
        Optional<Marks> optionalMark = marksRepository.findById(id);
        if (optionalMark.isPresent()) {
            return optionalMark.get();
        } else {
            throw new GroupNotFoundException("Mark not found");
        }
    }

    @Override
    public Marks addMark(Marks mark) {
        return marksRepository.save(mark);
    }

    @Override
    public void deleteById(Long id) {
        marksRepository.deleteById(id);
    }

    @Override
    public List<Marks> findAllByStudentId(Long studentId) {
        return marksRepository.findAllByStudentId(studentId);
    }

    @Override
    public List<Marks> findAllByStudentIdAndSubject(Long studentId, String name) {
        return marksRepository.findAllByStudentIdAndSubject(studentId, name);
    }

    @Override
    public List<Marks> findAllByGroupIdAndSubject(Long groupId, String name) {
        return marksRepository.findAllByGroupIdAndSubject(groupId, name);
    }

    @Override
    public List<Marks> findAllByTeacherIdAndSubject(Long teacherId, String name) {
        return marksRepository.findAllByTeacherIdAndSubject(teacherId, name);
    }

    @Override
    public void setValueForId(Long markId, int newMark) {

        Optional<Marks> optionalMark = marksRepository.findById(markId);
        if (optionalMark.isPresent()) {
            Marks mark = optionalMark.get();
            mark.setValue(newMark);
            marksRepository.save(mark);
        } else {
            throw new MarkNotFoundException("Mark not found");
        }
    }

    @Override
    public void deleteAll() {
        marksRepository.deleteAll();
    }
}
