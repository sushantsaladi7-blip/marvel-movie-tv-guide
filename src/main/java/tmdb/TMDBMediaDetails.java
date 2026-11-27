package tmdb;
import java.io.IOException;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import media.Media;
import java.util.ArrayList;

public class TMDBMediaDetails {
    private String posterURL;
    private double rating;
    private String title;
    private String overview;
    private int runtime;

    public void searchMovie(ArrayList<Media> mediaList) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("https://api.themoviedb.org/3/search/movie?query=Iron%20Man"))
        .header("Authorization", "Bearer " + System.getenv("TMDB_TOKEN"))
        .build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request,HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
            return;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
            return;
        }
        JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();
        JsonArray results = json.getAsJsonArray("results");
        JsonObject first = (results.get(0)).getAsJsonObject();
        title = first.get("title").getAsString();
        overview = first.get("overview").getAsString();
        String posterPath = first.get("poster_path").getAsString();
        String baseURL = "https://image.tmdb.org/t/p/w500";
        posterURL = baseURL + posterPath;
        rating = first.get("vote_average").getAsDouble();
        runtime = first.get("runtime").getAsInt();
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

    public int getRuntime() {
        return runtime;
    }
}
