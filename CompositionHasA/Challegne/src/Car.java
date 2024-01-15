import java.util.concurrent.SubmissionPublisher;

public class Car {
    private String description;

    public Car(String description){
        this.description = description;
    }

    public void startEngine(){
        System.out.println("Engine started...");
    }

    protected void runEngine(){
        System.out.println("Engine running...");
    }

    public void drive(){
        runEngine();
    }


}


class GasPowerCar extends Car{

    private double avgKmPerLiter;
    private int cylinders;
    public GasPowerCar(String description, double avgKmPerLiter, int cylinders){
        super(description);
        this.avgKmPerLiter = avgKmPerLiter;
        this.cylinders = cylinders;
    }

    public void drive(int perHour){
        super.drive();
        System.out.printf("Gas Power Car running at %d Km/h%n", perHour);
    }
}


class ElectricCar extends Car{

    private double avgKmPerCharge;
    private int batterySize;
    public ElectricCar(String description, double avgKmPerCharge, int batterySize){
        super(description);
        this.avgKmPerCharge = avgKmPerCharge;
        this.batterySize = batterySize;
    }

    public void drive(int perHour){
        super.drive();
        System.out.printf("Electric Car running at %d Km/h%n", perHour);
    }
}


class HybridCar extends Car{

    private double avgKmPerLiter;
    private int batterySize;
    private int cylinders;
    public HybridCar(String description, double avgKmPerLiter, int batterySize, int cylinders){
        super(description);
        this.avgKmPerLiter = avgKmPerLiter;
        this.batterySize = batterySize;
        this.cylinders = cylinders;
    }

    public void drive(int perHour){
        super.drive();
        System.out.printf("Hybrid Car running at %d Km/h%n", perHour);
    }
}