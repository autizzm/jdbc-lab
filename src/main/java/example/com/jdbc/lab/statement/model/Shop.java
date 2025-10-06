package example.com.jdbc.lab.statement.model;

import lombok.Data;

@Data
public class Shop {
	private Long id;
	private String name;
	private String address;

	public Shop() {
	}

	public Shop(String name, String address) {
		this.name = name;
		this.address = address;
	}

	@Override
	public String toString() {
		return "Shop{" +
				"id=" + id +
				", name='" + name + '\'' +
				", address='" + address + '\'' +
				'}';
	}

	public Object getName() {
		return  name;
    }

	public Long getId() {
		return id;
	}

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}