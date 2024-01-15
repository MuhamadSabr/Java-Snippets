public class SmartKitchen {
    private CofferMaker brewMaster;
    private DishWasher dishWasher;
    private Refrigerator iceBox;

    public SmartKitchen(CofferMaker brewMaster, DishWasher dishWasher, Refrigerator iceBox) {
        this.brewMaster = brewMaster;
        this.dishWasher = dishWasher;
        this.iceBox = iceBox;
    }

    public SmartKitchen(){
        brewMaster = new CofferMaker();
        dishWasher = new DishWasher();
        iceBox     = new Refrigerator();
    }

    public void addWater(){
        brewMaster.setHasWorkToDo(true);
    }

    public void pourMilk(){
        iceBox.setHasWorkToDo(true);
    }

    public void loadDishWasher(){
        dishWasher.setHasWorkToDo(true);
    }

    public void setKitchenState(boolean coffeeHasWorkToDo, boolean refrigeratorHasWorkToDo, boolean dishWasherHasWorkToDo) {
        dishWasher.setHasWorkToDo(dishWasherHasWorkToDo);
        iceBox.setHasWorkToDo(refrigeratorHasWorkToDo);
        brewMaster.setHasWorkToDo(coffeeHasWorkToDo);
    }

    public void doKitchenWork(){
        iceBox.orderFood();
        brewMaster.brewCoffee();
        dishWasher.doDishes();
    }


    /*
    public CofferMaker getBrewMaster(){
        return brewMaster;
    }

    public DishWasher getDishWasher(){
        return dishWasher;
    }

    public Refrigerator getIceBox(){
        return iceBox;
    }
     */


}

class Refrigerator{
    private boolean hasWorkToDo;

    public void setHasWorkToDo( boolean state){
        hasWorkToDo = state;
    }
    public void orderFood(){
        if(hasWorkToDo){
            System.out.println("Refrigerator running...");
            hasWorkToDo = false;
        }
    }
}



class DishWasher{
    private boolean hasWorkToDo;

    public void setHasWorkToDo(boolean state){
        hasWorkToDo = state;
    }
    public void doDishes(){
        if(hasWorkToDo){
            System.out.println("Loading the dishes...");
            hasWorkToDo = false;
        }
    }
}



class CofferMaker{
    private boolean hasWorkToDo;

    public void setHasWorkToDo(boolean state){
        hasWorkToDo = state;
    }
    public void brewCoffee(){
        if(hasWorkToDo){
            System.out.println("Making coffee...");
            hasWorkToDo = false;
        }
    }
}