package sports.base;

public class SportClubWithRandomNumber extends SportClub {
    int number;
    
    public SportClubWithRandomNumber(String name, double points, int number) {
        super(name, points);
        this.number=number;
    }
    
    public SportClubWithRandomNumber(String name, double points, int number, String pokrik) {
        super(name, points, pokrik);
        this.number=number;
    }

    @Override
    public String toString() {
        return ("" + number + ". " + this.outputStrategy.formatOutput(this.name, this.rating));
    }
}
