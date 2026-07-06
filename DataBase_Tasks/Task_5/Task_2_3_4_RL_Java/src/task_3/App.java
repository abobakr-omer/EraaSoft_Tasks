package task_3;

import java.util.Arrays;
import java.util.List;

public class App {

    public static void main(String[] args) {

        Teacher teacher1=new Teacher(1,"ahmed",35000);
        Teacher teacher2=new Teacher(2,"bakr",25000);
        Teacher teacher3=new Teacher(3,"mohamed",45000);

        Language language=new Language(1,"Arabic");

        List<Teacher> teachers= Arrays.asList(teacher1,teacher2,teacher3);
        language.setTeachers(teachers);

        teacher1.setLanguage(language);
        teacher2.setLanguage(language);
        teacher3.setLanguage(language);


        language.getTeachers().stream().
                forEach(teacher -> System.out.println(teacher.getName()));





    }


}
