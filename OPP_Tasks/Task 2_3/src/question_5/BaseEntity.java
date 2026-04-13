package question_5;

public class BaseEntity {

    private Integer id;
    private String name;

    BaseEntity(String name, Integer id) {
        setName(name);
        setId(id);
    }

    public void setName(String name) {
        if (name != null && name.matches("[a-zA-Z ]+") && name.length() >= 3) {
            this.name = name;
        } else {
            System.out.println("Name must be at least 3 characters and contain only letters and spaces");
        }
    }

    public void setId(Integer id) {
        if (id != null && id > 0) {
            this.id = id;
        } else {
            System.out.println("ID must be greater than Zero");
        }
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
