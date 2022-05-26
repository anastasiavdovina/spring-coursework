package main.entity;

public class Marks {

    private Long id;
    private Integer value;
    private People studentId;
    private People teacherId;
    private Subjects subjectId;

    public Marks() {

    }

    public Marks(long id, People studentId, Subjects subjectId, People teacherId, Integer value) {
        this.id = id;
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