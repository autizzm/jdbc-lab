package example.com.jdbc.lab.statement.model;


public class Author {
	private Long id;
	private String firstName;
	private String lastName;

	public Author() {
	}

	public Author(final String firstName, final String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

    public enum SortingType{
        BY_FIRST_NAME,
        BY_LAST_NAME
    }

    public String getLastName() {
		return  this.lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}