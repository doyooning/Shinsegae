package BookMarket.com.market.member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Person {
	private String name;
	private String phone;
	private String address;

	public Person(String name, String phone) {
		this.name = name;
		this.phone = phone;
	}

	public Person(String name, String phone, String address) {
		this.name = name;
		this.phone = phone;
		this.address = address;
	}
}