import java.util.ArrayList;

public class MediaManager {
    private ArrayList<Media> media = new ArrayList<>();

    public void addMediaObject(Media m) {
        if (media.size() == 0) {
            media.add(m);
        } else {
            for (int i = 0; i < media.size(); i++) {
                if ((media.get(i).getReleaseDate()).compareTo(m.getReleaseDate()) > 0) {
                    media.add(i, m);
                    break;
                }

                if (i == media.size() - 1 && (media.get(i).getReleaseDate()).compareTo(m.getReleaseDate()) < 0) {
                    media.add(m);
                }
            }
        }
    }

    public ArrayList<Media> getMedia() {
        return media;
    }
}
