package com.mmd;

public class Main {
    public static void main(String[] args) {
        Mappable krgBuilding = new Building("KRG Building", "Governmental things", "Label just for your sake", IconType.CROSS);
        Mappable.print(krgBuilding);
        Mappable krgUtilityLine = new UtilityLine("KRG Utility Line", "KRG internet line", "KRG utility internet line", IconType.CROSS);
        Mappable.print(krgUtilityLine);
    }
}