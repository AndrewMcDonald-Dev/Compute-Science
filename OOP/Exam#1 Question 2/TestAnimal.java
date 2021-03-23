public class TestAnimal {
	public static void main(String[] args) {
		Animal.message();

		Animal sparky = new Dog();

		Animal kit = new Cat();

		sparky.name();
		sparky.sound();
		kit.name();
		kit.sound();
	}
}
