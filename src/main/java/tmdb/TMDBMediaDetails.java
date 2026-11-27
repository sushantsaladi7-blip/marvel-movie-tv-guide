package tmdb;
import media.Media;

public class TMDBMediaDetails {
    private String posterURL;
    private double rating;
    private String title;
    private String overview;
    private Media media;

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
