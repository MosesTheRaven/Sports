package Subject;

public class NewSeasonNotification implements MyNotification{
    public int startovne;

    public NewSeasonNotification(int startovne) {
        this.startovne = startovne;
    }

    
    @Override
    public String popis() {
        return "Notifikacia, ktora upozornuje o starte novej sezony";
    }
}
