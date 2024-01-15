package com.mmd;

class OnMapItems{
    GeometryType geometryType;
    String label;
    IconType iconType;
    String name;
    public OnMapItems(String name, GeometryType geometryType, String label, IconType iconType) {
        this.geometryType = geometryType;
        this.label = label;
        this.iconType = iconType;
        this.name = name;
    }
    public GeometryType getGeometryType() {
        return geometryType;
    }
    public String getLabel() {
        return label;
    }
    public IconType getIconType() {
        return iconType;
    }
    public String getName() {
        return name;
    }
}

class Building extends OnMapItems implements Mappable { //Order of extends first n implements 2 matters be careful
    String usage;
    public Building(String name, String usage, String label, IconType iconType) {
        super(name, GeometryType.POINT, label, iconType);
        this.usage = usage;
    }
    @Override
    public String label() {
        return getLabel();
    }
    @Override
    public GeometryType geometryType() {
        return getGeometryType();
    }
    @Override
    public IconType mapMarker() {
        return getIconType();
    }
    public String getUsage() {
        return usage;
    }

    @Override
    public String toJSON() {
        return Mappable.super.toJSON() + ", " +
                TEMPLATE.formatted("name", getName() ) + ", " + TEMPLATE.formatted("usage", getUsage());
    }
}

class UtilityLine extends OnMapItems implements Mappable{
    String utility;
    public UtilityLine(String name, String utility, String label, IconType iconType) {
        super(name, GeometryType.LINE, label, iconType);
        this.utility = utility;
    }
    @Override
    public String label() {
        return getLabel();
    }
    @Override
    public GeometryType geometryType() {
        return getGeometryType();
    }
    @Override
    public IconType mapMarker() {
        return getIconType();
    }
    public String getUtility() {
        return utility;
    }
    @Override
    public String toJSON() {
        return Mappable.super.toJSON() + ", " + TEMPLATE.formatted("name", getName() )+
                ", " + TEMPLATE.formatted("utility", getUtility());
    }

}

public interface Mappable {
    String TEMPLATE = "\"%s\": \"%s\"";
    String JSON_PROPERTY = """
            "properties":{%S}\"""";  // U can use \s at after the whitespace to escape whitespace trails "properties":{%S}"   \s"
                                    //As  u can see \ was used to escape the last " because it comes directly before """
    String label();
    GeometryType geometryType();
    IconType mapMarker();

    default String toJSON(){ // U can invoke the abstract methdos from concrete methods. At runtime Java will invoke the implemented method.
        return """
                        "type": "%s", "label": "%s", "marker": "%s\"""".formatted(
                geometryType().name(), label(), mapMarker().name() );
    }
    static void print(Mappable mappable){
        System.out.printf(JSON_PROPERTY, mappable.toJSON());  //This is how the method above will resolve the abstract methods.
        System.out.println();
    }
}
enum GeometryType{POINT, LINE,}
enum IconType{CROSS, POINTER,}