package task_3;

import java.util.List;

public class Language {

    private int id;
    private String name;

    private List<Teacher> teachers;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public Language() {
    }

    public Language(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
