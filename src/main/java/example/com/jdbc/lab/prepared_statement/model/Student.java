package example.com.jdbc.lab.prepared_statement.model;

import example.com.jdbc.lab.utils.InputManager;


public class Student {
	private Long id;
	private String firstName;
	private String lastName;
	private Integer age;
	private String city;
	private String email;

	public Student() {
	}

	public Student(String firstName, String lastName, Integer age, String city, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.city = city;
		this.email = email;
	}

	public static Student getStudentFromInput() {
		Student student = new Student();
		System.out.print("Введи имя: ");
		student.setFirstName(InputManager.getNextLine());
		System.out.print("Введи фамилию (-, чтобы пропустить): ");
		student.setLastName(InputManager.getNextLineWithSkip());
		System.out.print("Введи возраст (17 - 71): ");
		student.setAge(InputManager.getNextIntInRange(17, 71));
		System.out.print("Введи город (-, чтобы пропустить): ");
		student.setCity(InputManager.getNextLineWithSkip());
		System.out.print("Введи email (-, чтобы пропустить): ");
		student.setEmail(InputManager.getNextLineWithSkip());
		return student;
	}

	@Override
	public String toString() {
		return "Student{" +
				"id=" + id +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", age=" + age +
				", city='" + city + '\'' +
				", email='" + email + '\'' +
				'}';
	}

	public void setCity(String nextLineWithSkip) {
		this.city = nextLineWithSkip;
	}

	public void setAge(int nextIntInRange) {
		this.age = nextIntInRange;
	}

	public void setLastName(String nextLineWithSkip) {
		this.lastName = nextLineWithSkip;
	}

	public void setFirstName(String nextLine) {
		this.firstName = nextLine;
	}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public Integer getAge() {
        return age;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
