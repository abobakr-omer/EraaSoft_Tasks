package level_4;

import java.util.Objects;

public class StudentById {

    int id;
    String email;

    public StudentById(int id, String email){
        this.id=id;
        this.email=email;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        StudentById student = (StudentById) obj;
        return id == student.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "StudentById{id=" + id + ", email='" + email + "'}";
    }


}
