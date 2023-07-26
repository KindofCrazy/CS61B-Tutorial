public class DogLauncher {
    public static void main(String[] args) {
        Dog d = new Dog(51);
        
        Dog d2 = new Dog(100);

        Dog bigger = Dog.maxDog(d, d2);
        bigger.makeNoise();
        // d.makeNoise();
        System.out.println(Dog.binomen);
    }
}
