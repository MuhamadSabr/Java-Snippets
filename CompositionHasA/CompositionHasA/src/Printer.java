public class Printer {

    private int tonerLevel;
    private int pagesPrinted;
    private boolean duplex;

    public Printer(int tonerLevel, boolean duplex){
        pagesPrinted=0;
        this.tonerLevel = tonerLevel>100 ? 100 : Math.max(tonerLevel, 0);
        this.duplex = duplex;
    }

    public int addToner(int tonerAmount){
        if( (tonerAmount+tonerLevel)>100  || tonerAmount<0 ){
            return -1;
        }
        tonerLevel += tonerAmount;
        return  tonerLevel;
    }

    public int printPages(int printPages){ //This determines how many sheets of paper will be printed based on printPages n duplex

        if(!duplex){
            pagesPrinted += printPages;
            return printPages;
        }
        System.out.println("It is a duplex printer");
        pagesPrinted += ( (printPages/2) + (printPages%2) );
        return ( (printPages/2) + (printPages%2) );
    }

    public int getTonerLevel(){
        return tonerLevel;
    }

    public int getPagesPrinted(){
        return pagesPrinted;
    }
}
