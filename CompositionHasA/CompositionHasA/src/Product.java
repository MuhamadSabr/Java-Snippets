
public class Product{
    private String model;
    private String manufacturer;
    private int width;
    private int height;
    private int depth;

    public Product(String model, String manufacturer) {
        this.model = model;
        this.manufacturer = manufacturer;
    }
}


class Monitor extends Product {

    private int size;
    private String resolution;
    public Monitor(String model, String manufacturer) {
        super(model, manufacturer);
    }

    public Monitor(String model, String manufacturer, int size, String resolution) {
        super(model, manufacturer);
        this.size = size;
        this.resolution = resolution;
    }

    public void drawPixelAt(int x, int y, String color){
        System.out.printf("Pixel at %d,%d in color %s%n", x, y, color);
    }
}


class Motherboard extends Product {

    private int ramSlot;
    private int carSlot;
    private String bioas;
    public Motherboard(String model, String manufacturer) {
        super(model, manufacturer);
    }
    public Motherboard(String model, String manufacturer, int ramSlot, int carSlot, String bioas) {
        super(model, manufacturer);
        this.ramSlot = ramSlot;
        this.carSlot = carSlot;
        this.bioas = bioas;
    }

    public void loadProgram(){
        System.out.println("Loading program...");
    }

}



class ComputerCase extends Product {
    private String powerSupply;
    public ComputerCase(String model, String manufacturer) {
        super(model, manufacturer);
    }

    public ComputerCase(String model, String manufacturer, String powerSupply) {
        super(model, manufacturer);
        this.powerSupply = powerSupply;
    }

    public void pressPowerButton(){
        System.out.println("Power button pressed...");
    }

}
