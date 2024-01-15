import java.util.Objects;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        //The return type of getMovie is Movie class. The compiler needs to verify at compile time that movie is compatible with Adventure which is not
        //Since we know at runtime we would get Adventure we can cast it to Adventure
        Adventure adventure = (Adventure) Movie.getMovie("A", "Very adventorous");
        adventure.watchMovie();  //U can have a reference of the base class to reference a subclass object. But only members of the base class will be accessible

        Object adven = Movie.getMovie("Com", "Wow very thrilling");
        if(adven.getClass().getSimpleName().equals("Adventure")){
            Adventure adv = (Adventure) adven;
            adv.watchMovie();
        }
        else if(adven instanceof Comedy){
            ((Comedy)adven).watchMovie();
        }
        else if(adven instanceof ScienceFiction scienceFiction){ // Pattern matching support version
            scienceFiction.watchMovie();
        }


        /*
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("Type movie type or Q for quit");
            String type = scanner.nextLine();
            if("Qq".contains(type)){
                break;
            }
            System.out.println("Enter movie title");
            String title = scanner.nextLine();
            Movie movie = Movie.getMovie(type, title);
            movie.watchMovie();
        }
         */


        /*
        Printer printer1 = new Printer(-150, true);
        printer1.printPages(80);
        System.out.println(printer1.getTonerLevel());
        System.out.println(printer1.getPagesPrinted());

        Printer printer2 = new Printer(50, false);
        printer2.printPages(77);
        System.out.println(printer2.getPagesPrinted());
        if(printer2.addToner(50) ==-1){
            System.out.println("Unvalid added toner level");
        }else {
            System.out.println(printer2.getTonerLevel());
        }

        /*
                                                    // U create an instance of each class n assign their reference to the class member variables in the smartkitchen
        //SmartKitchen smartKitchen = new SmartKitchen(new CofferMaker(), new DishWasher(), new Refrigerator());
        SmartKitchen smartKitchen = new SmartKitchen(); //But this one is like saying the class comes with its own set of appliances.
        smartKitchen.addWater();
        smartKitchen.loadDishWasher();
        smartKitchen.pourMilk();

        smartKitchen.setKitchenState(false, true, false);
        smartKitchen.doKitchenWork();

        Motherboard motherboard360 = new Motherboard("Microsoft", "Microsoft", 3, 2, "Linux version 2.3");
        Monitor gigabyte = new Monitor("Gigabyte", "Gigabyte", 32, "3840x1440");
        ComputerCase thermaltake = new ComputerCase("Thermaltake", "Thermo company", "ups850");
        PersonalComputer Mohammed_Othman_PC = new PersonalComputer("Mixed", "built type", motherboard360, thermaltake, gigabyte);
        Mohammed_Othman_PC.computerCaseShare();
        Mohammed_Othman_PC.monitorShare();
         */
    }
}


