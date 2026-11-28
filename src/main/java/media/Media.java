package media;
import java.time.LocalDate;
public class Media {
    private final String title;
    private final String type;
    private final LocalDate releaseDate;
    private final int chronologyOrder;
    private final boolean isMCU;
    private final boolean secretWarsWatch;
    private boolean watched;

    public Media(String title, String type, LocalDate releaseDate, int chronologyOrder, boolean isMCU, boolean secretWarsWatch, boolean watched) {
        this.title = title;
        this.type = type;
        this.releaseDate = releaseDate;
        this.chronologyOrder = chronologyOrder;
        this.isMCU = isMCU;
        this.secretWarsWatch = secretWarsWatch;
        this.watched = watched;
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

    public boolean isWatched() {
        return watched;
    }

    public void setWatched(boolean watched) {
        this.watched = watched;
    }

    @Override
    public String toString() {
        return getTitle() + " (" + getReleaseDate().getYear() + ") - " + getType();
    }
}