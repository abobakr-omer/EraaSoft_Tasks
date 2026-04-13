package level_2;

import java.util.Objects;

public class Person {

    int id;
    String name;

    public Person(int id, String name){
        this.id=id;
        this.name=name;
    }



    @Override
    public boolean equals(Object obj) {
        if (this==obj) return true;
        if (obj==null || getClass()!=obj.getClass()) return false;

        Person person=(Person) obj;

        return (id==person.id);
    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(id);
//    }
}
