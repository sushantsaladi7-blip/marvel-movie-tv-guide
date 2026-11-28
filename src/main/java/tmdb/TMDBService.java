package tmdb;
import java.io.IOException;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import media.Media;
import java.util.ArrayList;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

public class TMDBService {
    public ArrayList<TMDBMediaDetails> searchMedia(ArrayList<Media> mediaList) {
        String token = System.getenv("TMDB_TOKEN");
        if (token == null || token.isBlank()) {
            System.out.println("TMDB_TOKEN environment variable is missing.");
            return new ArrayList<>();
        }
        HttpClient client = HttpClient.newHttpClient();
        ArrayList<TMDBMediaDetails> detailsList = new ArrayList<>();
        for (Media m : mediaList) {
            //Use different TMDB search endpoints for movies and TV shows
            String endpoint;
            if (m.getType().equalsIgnoreCase("Movie") || m.getType().equalsIgnoreCase("Animated Movie")
                || m.getType().equalsIgnoreCase("Special")) {
                endpoint = "movie";
            } else if (m.getType().equalsIgnoreCase("TV Show")
                    || m.getType().equalsIgnoreCase("TVShow")) {
                endpoint = "tv";
            } else {
                continue;
            }
            String query;
            //Remove the season suffix because TMDB searches for the TV show's main title
            if (endpoint.equals("tv") && m.getTitle().contains(" Season")) {
                String[] name = m.getTitle().split(" Season");
                query = URLEncoder.encode(name[0], StandardCharsets.UTF_8);
            } else {
                query = URLEncoder.encode(m.getTitle(), StandardCharsets.UTF_8);
            }
            String yearParameter = "";
            //Restrict movie searches by release year to help distinguish titles with the same name
            if (endpoint.equals("movie") && m.getReleaseDate() != null) {
                yearParameter = "&primary_release_year=" + m.getReleaseDate().getYear();
            }
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.themoviedb.org/3/search/"+ endpoint + "?query=" + query + yearParameter))
                    .header("Authorization",
                            "Bearer " + token)
                    .build();
            HttpResponse<String> response = null;
            try {
                response = client.send(request, HttpResponse.BodyHandlers.ofString());
                if (response.statusCode() != 200) {
                    System.out.println("TMDB request failed: " + response.statusCode());
                    continue;
                }
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            } catch (InterruptedException e) {
                //Restore the interrupt status so other parts of the application can detect the interruption.
                Thread.currentThread().interrupt();
                e.printStackTrace();
                return detailsList;
            }
            JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();
            JsonArray results = json.getAsJsonArray("results");
            if (results.isEmpty()) {
                System.out.println("No TMDB results for: " + m.getTitle());
                continue;
            }
            JsonObject first = results.get(0).getAsJsonObject();
            String title = null;
            if (endpoint.equals("movie")) {
                title = first.get("title").getAsString();
            } else {
                title = first.get("name").getAsString();
            }
            String overview = first.get("overview").getAsString();
            String posterURL = null;
            if (first.get("poster_path").isJsonNull()) {
                posterURL = null;
            } else {
                //TMDB returns a poster path for the the first one in the search results
                String posterPath = first.get("poster_path").getAsString();
                String baseURL = "https://image.tmdb.org/t/p/w500";
                posterURL = baseURL + posterPath;
            }
            double rating = first.get("vote_average").getAsDouble();
            TMDBMediaDetails details = new TMDBMediaDetails(posterURL, rating, title, overview, m);
            detailsList.add(details);
        }
        return detailsList;
    }
}