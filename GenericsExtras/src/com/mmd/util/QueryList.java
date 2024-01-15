package com.mmd.util;

import com.mmd.model.LPAStudent;
import com.mmd.model.Student;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class QueryList <T extends Student & QueryItem> extends ArrayList<T>{ // The type argument that u use must implement the QueryItem interface.
    //private List<T> items; //In our example we pass it a list of either Student or a child of it.


//    public QueryList(List<T> items) { // We pass a list through the constructor at time of initialization.
//        this.items = items;
//        super(items);
//    }

    public static <T extends QueryItem> List<T> getMatches(List<T> items, String field, String value){ // We sent back a list from the list that was passed of all the items that match the values of this field and value
        List<T> matches = new ArrayList<>();
        for(var item : items){
            if(item.matchFieldValue(field, value) ){
                matches.add(item);
            }
        }
        return matches;
    }

    public QueryList<T> getMatches(String field, String value){ // We sent back a list from the list that was passed of all the items that match the values of this field and value
        QueryList<T> matches = new QueryList<>();
        for(var item : this){
            if(item.matchFieldValue(field, value) ){
                matches.add(item);
            }
        }
        return matches;
    }
}
