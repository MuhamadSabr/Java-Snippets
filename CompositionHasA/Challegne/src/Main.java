// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        Car tesslaElectricCar = new ElectricCar("Tessla electric car", 200, 500);
        Car toyotoGasCar = new GasPowerCar("Toyota gas car", 100, 4);
        Car bmwHybirdCar = new HybridCar("BMW super amazing hybrid car", 200, 200, 8);

        runTimeObject(tesslaElectricCar);
        runTimeObject(toyotoGasCar);
        runTimeObject(bmwHybirdCar);

    }

    public static void runTimeObject(Car car){
        System.out.printf("Runtime type of the object is : %s%n", car.getClass().getSimpleName());
    }
}