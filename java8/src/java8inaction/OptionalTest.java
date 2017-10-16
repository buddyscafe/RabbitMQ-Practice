package java8inaction;

import java.util.Optional;

/* ways to create Optional values:
 * 1) Optional.empty() : returns empty Optional
 * 2) Optional.of() : throws NullPointerException if value is null.
 * 3) Optional.ofNullable : Returns empty Optional if value is null.
 * */

class Person {
	private Optional<Car> car;
	public Optional<Car> getCar() {
		return car;
	}
}

class Car {
	private Optional<Insurance> insurance;
	public Optional<Insurance> getInsuranceName() {
		return insurance;
	}
}

class Insurance {
	private String name;
	public String getName() {
		return name;
	}
}

public class OptionalTest {

	public static void main(String[] args) {
		
		System.out.println(new Person().getCar().get().getInsuranceName().get().getName());
		
	}

}
