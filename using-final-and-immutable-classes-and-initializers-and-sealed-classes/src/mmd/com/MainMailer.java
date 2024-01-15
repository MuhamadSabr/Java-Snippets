package mmd.com;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MainMailer {
    public static void main(String[] args) {

        String[] names = {"Mohammed Othman", "Mohammed Othman PHD", "Karol Jadr", "Jones Ali", "Jones Ali PHD",
                "Shark Falah", "Sudan Jaafar", "Sudan Jaafar PHD"};

        List<StringBuilder> population = getNames(names);
        Map<StringBuilder, Integer> counts = new TreeMap<>();
        population.forEach( name-> counts.merge(name, 1, (oldValue, newValue) -> oldValue + newValue) );
        System.out.println(counts);

        StringBuilder mohammed = new StringBuilder("Mohammed Othman PHD");
        System.out.printf("There are %d records of %s%n", counts.get(mohammed), mohammed.toString());
        System.out.println("-".repeat(50));

        List<StringBuilder> standardizedNames = new ArrayList<>();
                standardizedNames.addAll(getStandardizedNames(population));
        System.out.println(counts);

    }

    private static List<StringBuilder> getNames(String[] names){
        List<StringBuilder> tempNames = new ArrayList<>();
        int index = 3;
        for(String name : names){
            for(int i=0; i<index; i++){
                tempNames.add(new StringBuilder(name));
            }
            index++;
        }
        return tempNames;
    }

    private static List<StringBuilder> getStandardizedNames(List<StringBuilder> list){
        List<StringBuilder> standardizedNames = new ArrayList<>();
        standardizedNames.addAll(list);
        for(StringBuilder name : standardizedNames){
            for(String suffix : new String[]{"PHD", "MS"}){
                if(name.indexOf(suffix)>-1){
                    name.replace(name.indexOf(suffix), suffix.length()+name.indexOf(suffix), "");
                }
            }
        }
        return standardizedNames;
    }

}
