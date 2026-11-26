package se.iths.alfred.personalregister.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Employee {

    private Integer employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate startDate;
    private Department department;
    private Keycard keycard;
    private List<Skill> skillList = new ArrayList<>();

    //Konstruktor MED keycard
    public Employee(Integer employeeId, String firstName, String lastName, String email,
                    LocalDate startDate, Department department, Keycard keycard) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.startDate = startDate;
        this.department = department;
        this.keycard = keycard;
    }

    //Konstruktor UTAN keycard
    public Employee(Integer employeeId, String firstName, String lastName, String email,
                    LocalDate startDate, Department department) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.startDate = startDate;
        this.department = department;
    }

    public void addSkill(Skill skill) {
        if (skillList.contains(skill)) {
            IO.println(skill.getSkillName() + " är redan registrerad på " + getFirstName() + " " + getLastName() + ".");
        } else {
            skillList.add(skill);
        }
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(", ");
        for (Skill skill : skillList) {
            joiner.add(skill.getSkillName() + " (" + skill.getDescription() + ")");
        }
        return firstName + " " + lastName
                + "\nPersonal-ID: " + employeeId
                + "\nAvdelning: " + department.getDepartmentName()
                + "\nEmail: " + email
                + "\nAnställd: " + startDate
                + "\nPasserkortnummer: " + (keycard == null ? "[Inget passerkort]" : keycard.getCardNumber())
                + "\nKompetenser: " + joiner
                + "\n";
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public Department getDepartment() {
        return department;
    }

    public Keycard getKeycard() {
        return keycard;
    }
}
