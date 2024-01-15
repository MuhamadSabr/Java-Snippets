import java.lang.management.MonitorInfo;

public class PersonalComputer extends Product{

    private Motherboard motherboard;
    private ComputerCase computerCase;
    private Monitor monitor;
    public PersonalComputer(String model, String manufacturer) {
        super(model, manufacturer);
        motherboard = new Motherboard(model, manufacturer);
        computerCase = new ComputerCase(model, manufacturer);
        monitor = new Monitor(model, manufacturer);
    }

    public PersonalComputer(String model, String manufacturer, Motherboard motherboard, ComputerCase computerCase, Monitor monitor) {
        super(model, manufacturer);
        this.motherboard = motherboard;
        this.computerCase = computerCase;
        this.monitor = monitor;
    }

    public void computerCaseShare() {
        computerCase.pressPowerButton();
    }

    public void monitorShare() {
        monitor.drawPixelAt(3,3,"Blue");
    }
}
