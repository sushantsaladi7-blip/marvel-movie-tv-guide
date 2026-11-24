import java.util.ArrayList;

public class MediaManager {
    private ArrayList<Media> media = new ArrayList<>();

    public void addMediaObject(Media m) {
        media.add(m);
    }

    public ArrayList<Media> getMedia() {
        return media;
    }
}
