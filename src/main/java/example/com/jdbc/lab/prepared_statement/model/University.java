package example.com.jdbc.lab.prepared_statement.model;

import example.com.jdbc.lab.utils.InputManager;

public class University {
//    private Long id;
//    private String firstName;
//    private String lastName;
//    private String subject;

    private Long id;
    private String name;
    private String city;
    private String country;

    public University(Long id, String name, String city, String country){
        this.id = id;
        this.name = name;
        this.city = city;
        this.country = country;
    }

    public University(){};

    @Override
    public String toString() {
        return "University{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public static University getUniversityFromInput() {
        University university = new University();
        System.out.print("Введи название: ");
        university.setName(InputManager.getNextLine());
        System.out.print("Введи страну: ");
        university.setCountry(InputManager.getNextLine());
        System.out.print("Введи город: ");
        university.setCity(InputManager.getNextLine());
        return university;
    }
}
