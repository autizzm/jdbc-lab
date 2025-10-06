package example.com.jdbc.lab.statement.model;


public class Book {
	private Long id;
	private String title;
	private Long authorId;
	private Integer yearOfPublication;

	public Book() {
	}

	public Book(String title, Long authorId, Integer yearOfPublication) {
		this.title = title;
		this.authorId = authorId;
		this.yearOfPublication = yearOfPublication;
	}

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", authorId=" + authorId +
                ", yearOfPublication=" + yearOfPublication +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Integer getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(Integer yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}