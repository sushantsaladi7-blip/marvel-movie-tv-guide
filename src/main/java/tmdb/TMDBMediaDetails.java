package tmdb;
import media.Media;

public class TMDBMediaDetails {
    private final String posterURL;
    private final double rating;
    private final String title;
    private final String overview;
    private final Media media;

    public TMDBMediaDetails(String posterURL, double rating, String title, String overview, Media media) {
        this.posterURL = posterURL;
        this.rating = rating;
        this.title = title;
        this.overview = overview;
        this.media = media;
    }

    public Media getMedia() {
        return media;
    }

    public String getPosterURL() {
        return posterURL;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public double getRating() {
        return rating;
    }
}
