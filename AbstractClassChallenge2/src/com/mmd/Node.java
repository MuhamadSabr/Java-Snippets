package com.mmd;


public class Node extends ListItem {

    public Node(Object value){
        super(value);
    }

    ListItem next(){
        return rightLink;
    }
    ListItem setNext(ListItem listItem){
        rightLink = listItem;
        return rightLink;
    }
    ListItem previous(){
        return leftLink;
    }
    ListItem setPrevious(ListItem listItem){
        leftLink = listItem;
        return leftLink;
    }

    int compareTo(ListItem item) {
        if(item==null){
            return -1;
        }
        int val1 ;
        int val2;
        if(item.getValue() instanceof String) {
            val2 = Integer.parseInt( (String) item.getValue() );
        }else{
            val2 =(int) item.getValue();
        }

        if(this.getValue() instanceof String) {
            val1 = Integer.parseInt( (String) this.getValue() );
        }
        else {
            val1 = (int) this.getValue();
        }

        if(val1 > val2){
            return 1;
        }
        if(val1 < val2){
            return -1;
        }
        return 0;
    }

}