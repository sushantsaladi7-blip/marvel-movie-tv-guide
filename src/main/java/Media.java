import java.time.LocalDate;
public class Media {
    private String title;
    private String type;
    private LocalDate releaseDate;
    private int chronologyOrder;
    private boolean isMCU;
    private boolean secretWarsWatch;

    public Media(String title, String type, LocalDate releaseDate, int chronologyOrder, boolean isMCU, boolean secretWarsWatch) {
        this.title = title;
        this.type = type;
        this.releaseDate = releaseDate;
        this.chronologyOrder = chronologyOrder;
        this.isMCU = isMCU;
        this.secretWarsWatch = secretWarsWatch;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public boolean isMCU() {
        return isMCU;
    }

    public int getChronologyOrder() {
        return chronologyOrder;
    }

    public boolean isSecretWarsWatch() {
        return secretWarsWatch;
    }

    @Override
    public String toString() {
        return getTitle() + " (" + getReleaseDate().getYear() + ")";
    }
}