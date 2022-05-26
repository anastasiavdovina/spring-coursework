package main.entity;

public class People {

    private Long id;
    private String firstName;
    private String lastName;
    private String fatherName;
    private String type;
    private Groups groupId;

    public People() {

    }

    public People(long id, String firstName, String lastName, String fatherName, Groups groupId, String type) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.firstName = fatherName;
        this.groupId = groupId;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public Groups getGroupId() {
        return groupId;
    }

    public void setGroupId(Groups groupId) {
        this.groupId = groupId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    @Override
    public String toString() {
        return "People{" +
                "id=" + id +
                ", first_name='" + firstName + '\'' +
                ", last_name='" + lastName + '\'' +
                ", father_name='" + fatherName + '\'' +
                ", group_id=" + groupId +
                ", type='" + type + '\'' +
                '}';
    }
}