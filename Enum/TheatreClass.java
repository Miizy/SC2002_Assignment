
/**
 * Enumeration for different types of Theatres in the cineplex
 * @author Viral Mehta
 */
public enum TheatreClass {
    /**
     * Platinum Movie Suites is the normal base theatre type
     */
    plat("Platinum Movie Suites"),
    /**
     * Elite Club is an luxurious and more expensive theatre type
     */
    elit("Elite Club");
    
    /**
     * A string that holds what type of theatre it is
     */
    private String theatreClass;
    /**
     * Constructs the theatre type based on the input
     * @param cinClass Abbreviated string for the theatre enumeration
     */
    TheatreClass(String cinClass) {
        this.setTheatreClass(cinClass);
    }
    /**
     * Gets the Theatre Type
     * @return string that shows what type of Theatre it is
     */
    public String getTheatreClass() {
        return theatreClass;
    }

    /**
     * Modifies and sets this theatre type based on input
     * @param cinemaClass abbreviated string for the theatre enumeration
     */
    public void setTheatreClass(String cinemaClass) {
        this.theatreClass = cinemaClass;
    }
    
}

