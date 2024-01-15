package com.mmd;

public class SearchTree implements NodeList {
    private ListItem root;
    public SearchTree(ListItem listItem){
        root = listItem;
    }
    public ListItem getRoot(){
        return root;
    }
    public boolean addItem(ListItem listItem){
        if(listItem == null){
            return false;
        }
        if(root == null || root.compareTo(listItem)==0){
            root = listItem;
            return true;
        }
        if(root.compareTo(listItem) >0){
            ListItem temp = root;
            root = listItem;
            root.setNext(temp);
            temp.setPrevious(root);
            return true;
        }

        ListItem nodes = root;
        while(nodes.next() != null){
            if(nodes.compareTo(listItem) ==0){
                return false;
            }
            else if(nodes.compareTo(listItem) <0 ){
                nodes = nodes.next();
                continue;
            }
            else if(nodes.compareTo(listItem ) >0){
                ListItem temp = nodes.previous();
                temp.setNext(listItem);
                listItem.setPrevious(temp);
                nodes.setPrevious(listItem);
                listItem.setNext(nodes);
                return true;
            }
            else
                return false;
        }
        if(nodes.compareTo(listItem) ==0 )
            return false;
        nodes.setNext(listItem);
        listItem.setPrevious(nodes);
        return true;
    }

    public boolean removeItem(ListItem listItem){
        if(root==null || listItem == null || listItem.getValue()==null){
            return false;
        }
        ListItem nodes = root;
        if( root.compareTo(listItem) ==0 ){
            if(root.next()==null){
                root = null;
                return false;
            }
            root = root.next();
            root.setPrevious(null);
            return true;
        }
        while(nodes.next() != null ){
            if( nodes.compareTo(listItem) !=0 ){
                nodes= nodes.next();
                continue;
            }
            else if( nodes.compareTo(listItem) ==0 ){
                ListItem temp = nodes.previous();
                temp.setNext( nodes.next() );
                nodes.setPrevious(temp);
            }
        }
        if(nodes.compareTo(listItem) !=0 )
            return false;
        ListItem temp = nodes.previous();
        temp.setNext(null);
        return true;
    }

    public void traverse(ListItem root){

        if(root == null ){
            System.out.println("The list is empty");
            return;
        }
        ListItem nodes = root;
        while(nodes!=null){
            System.out.println(nodes.getValue() );
            nodes = nodes.next();
        }
    }


}