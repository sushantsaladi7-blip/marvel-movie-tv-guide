import java.util.ArrayList;
import java.util.Comparator;

public class MediaManager {
    private ArrayList<Media> media = new ArrayList<>();

    public void addMediaObject(Media m) {
        media.add(m);
    }

    public ArrayList<Media> getMCUByReleaseOrder() {
        ArrayList<Media> mcu = new ArrayList<>();
        for (int i = 0; i < media.size(); i++) {
            if (media.get(i).isMCU()) {
                mcu.add(media.get(i));
            }
        }
        Comparator<Media> releaseOrder = Comparator.comparing(Media::getReleaseDate);
        mcu.sort(releaseOrder);
        return mcu;
    }

    public ArrayList<Media> getMCUByChronologyOrder() {
        ArrayList<Media> mcu = new ArrayList<>();
        for (int i = 0; i < media.size(); i++) {
            if (media.get(i).isMCU()) {
                mcu.add(media.get(i));
            }
        }
        Comparator<Media> chronologyOrder = Comparator.comparing(Media::getChronologyOrder);
        mcu.sort(chronologyOrder);
        return mcu;
    }

    public ArrayList<Media> getNonMCU() {
        ArrayList<Media> nonmcu = new ArrayList<>();
        for (int i = 0; i < media.size(); i++) {
            if (!media.get(i).isMCU()) {
                nonmcu.add(media.get(i));
            }
        }
        Comparator<Media> releaseOrder = Comparator.comparing(Media::getReleaseDate);
        nonmcu.sort(releaseOrder);
        return nonmcu;
    }

    public ArrayList<Media> getSecretWarsWatchOrder() {
         ArrayList<Media> secretWars = new ArrayList<>();
        for (int i = 0; i < media.size(); i++) {
            if (media.get(i).isSecretWarsWatch()) {
                secretWars.add(media.get(i));
            }
        }
        Comparator<Media> chronologyOrder = Comparator.comparing(Media::getChronologyOrder);
        secretWars.sort(chronologyOrder);
        return secretWars;
    }
}
