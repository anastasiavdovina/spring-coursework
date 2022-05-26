package main.entity;

import javax.persistence.*;

@Entity
public class Marks {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "value")
    private Integer value;

    @ManyToOne
    @JoinColumn(name = "student_id")
    People studentId;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    People teacherId;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    Subjects subjectId;

    public Marks() {

    }

    public Marks(People studentId, Subjects subjectId, People teacherId, Integer value) {
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.teacherId = teacherId;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public People getStudentId() {
        return studentId;
    }

    public void setStudentId(People studentId) {
        this.studentId = studentId;
    }

    public Subjects getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Subjects subjectId) {
        this.subjectId = subjectId;
    }

    public People getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(People teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Marks{" +
                "id=" + id +
                ", student_id=" + studentId +
                ", subject_id=" + subjectId +
                ", teacher_id=" + teacherId +
                ", value=" + value +
                '}';
    }
}
