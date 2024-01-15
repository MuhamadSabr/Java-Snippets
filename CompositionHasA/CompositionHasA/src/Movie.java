public class Movie {
    private String title;
    public Movie(String title){
        this.title = title;
    }
    public void watchMovie(){
        String instanceType = this.getClass().getSimpleName();
        System.out.printf("""
                %s is a "%s" film%n""", title, instanceType);
    }

    public static Movie getMovie(String type, String title){ // The type of method that returns a new instance of an object is called a Factory Method
        return switch (type.toUpperCase().charAt(0)){
            case 'A' -> new Adventure(title);
            case 'C' -> new Comedy(title);
            case 'S' -> new ScienceFiction(title);
            default -> new Movie(title);
        };
    }

}

class Comedy extends Movie{
    public Comedy(String title) {
        super(title);
    }
    public void watchMovie(){
        super.watchMovie();
        System.out.printf("""
                This is a comedy film
                Very very funny%n""");
    }
}



class Adventure extends Movie{
    public Adventure(String title) {
        super(title);
    }
    public void watchMovie(){
        super.watchMovie();
        System.out.printf("""
                Wow jump
                no come down, move move%n""");
    }
}



class ScienceFiction extends Movie{
    public ScienceFiction(String title) {
        super(title);
    }
    public void watchMovie(){
        super.watchMovie();
        System.out.printf("""
                Aliens are very where
                Watch out, watch out%n""");
    }
}