package se.iths.alfred.personalregister.model;

public class Department {

    private Integer departmentId;
    private String departmentName;
    private String city;

    public Department(Integer departmentId, String departmentName, String city) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.city = city;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "Department{" +
                "departmentId=" + departmentId +
                ", departmentName='" + departmentName + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
