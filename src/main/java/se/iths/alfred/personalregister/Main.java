package se.iths.alfred.personalregister;

import se.iths.alfred.personalregister.model.Department;
import se.iths.alfred.personalregister.model.Employee;
import se.iths.alfred.personalregister.model.Keycard;
import se.iths.alfred.personalregister.model.Skill;

import java.time.LocalDate;
import java.util.List;

public class Main {

    static void main() {

        Department department1 = new Department(1, "Human Resources", "Lund");
        Department department2 = new Department(2, "Level Design", "Stockholm");
        Department department3 = new Department(3, "Economy", "Skellefteå");
        Department department4 = new Department(4, "Ballistics", "Uddevalla");
        Department department5 = new Department(5, "Accounts", "London");

        Keycard keycard1 = new Keycard(1, 123, LocalDate.of(2023, 4, 6),
                LocalDate.of(2028, 4, 6));
        Keycard keycard2 = new Keycard(2, 456, LocalDate.of(2024, 6, 8),
                LocalDate.of(2029, 6, 8));
        Keycard keycard3 = new Keycard(3, 789, LocalDate.of(2024, 9, 2),
                LocalDate.of(2029, 9, 2));

        Skill skill1 = new Skill(1, "SQL", "Relationsdatabaser");
        Skill skill2 = new Skill(2, "Java", "Programmering");
        Skill skill3 = new Skill(3, "Elgitarr", "Instrument");
        Skill skill4 = new Skill(4, "Matlagning", "Kock");
        Skill skill5 = new Skill(5, "Nivådesign", "Innehållsproduktion");
        Skill skill6 = new Skill(6, "Budgetering", "Pengar");

        Employee employee1 = new Employee(1, "Angelica", "Borg", "anbo@email.se",
                LocalDate.of(2011, 4, 3), department1, keycard1);
        Employee employee2 = new Employee(2, "Anders", "Cameron", "anca@email.se",
                LocalDate.of(2011, 7, 2), department1, keycard2);
        Employee employee3 = new Employee(3, "Ahmed", "Beir", "ahbe@email.se",
                LocalDate.of(2013, 7, 2), department1);
        Employee employee4 = new Employee(4, "Amina", "Hassan", "amha@email.se",
                LocalDate.of(2022, 3, 14), department2);
        Employee employee5 = new Employee(5, "Mateo", "Silva", "masi@email.se",
                LocalDate.of(2023, 8, 1), department3, keycard3);
        Employee employee6 = new Employee(6, "Mei-Ling", "Chen", "mech@email.se",
                LocalDate.of(2018, 2, 3), department4);

        employee1.addSkill(skill1);
        employee1.addSkill(skill2);
        employee1.addSkill(skill3);
        employee2.addSkill(skill1);
        employee2.addSkill(skill2);
        employee3.addSkill(skill4);
        employee3.addSkill(skill3);
        employee4.addSkill(skill5);
        employee5.addSkill(skill5);

        List<Employee> employees = List.of(employee1, employee2, employee3, employee4, employee5, employee6);
        for (Employee employee : employees) {
            IO.println(employee);
        }

        IO.println("Oanvänd kompetens: " + skill6.getSkillName() + " - " + skill6.getDescription());

    }
}
