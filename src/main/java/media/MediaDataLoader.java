package media;
import java.io.*;
import java.time.*;
import java.time.format.*;

public class MediaDataLoader {
    public void addFromCSV(MediaManager manager) {
        try(BufferedReader br = new BufferedReader(new FileReader("data/marvel_media.csv"))) {
            String line = br.readLine();
            while (line != null) {
                try {
                    String[] data = line.split(",");
                    if (data.length != 7) {
                        line = br.readLine();
                        continue;
                    }
                    if ((!data[4].trim().equalsIgnoreCase("true") && !data[4].trim().equalsIgnoreCase("false")) || 
                        (!data[5].trim().equalsIgnoreCase("true") && !data[5].trim().equalsIgnoreCase("false")) || 
                        (!data[6].trim().equalsIgnoreCase("true") && !data[6].trim().equalsIgnoreCase("false"))) {
                        throw new Exception("Failed to load: " + line.split(",")[0] + "\n" + "Please enter either true or false for the last three parameters");
                    }
                    if (!data[1].trim().equalsIgnoreCase("Movie") && !data[1].trim().equalsIgnoreCase("TV Show")
                        && !data[1].trim().equalsIgnoreCase("TVShow") && !data[1].trim().equalsIgnoreCase("Special")
                        && !data[1].trim().equalsIgnoreCase("Animated Movie") && !data[1].trim().equalsIgnoreCase("AnimatedMovie")) {
                        throw new Exception("Failed to load: " + line.split(",")[0] + "\n" + "Invalid type");
                    }
                    Media med = new Media(data[0], data[1], LocalDate.parse(data[2]), Integer.parseInt(data[3]), Boolean.parseBoolean(data[4]), Boolean.parseBoolean(data[5]), Boolean.parseBoolean(data[6]));
                    manager.addMediaObject(med);
                } catch (DateTimeParseException de) {
                    IO.println("Failed to load: " + line.split(",")[0]);
                    IO.println("Invalid Release Date");
                } catch (NumberFormatException ne) {
                    IO.println("Failed to load: " + line.split(",")[0]);
                    IO.println("Invalid Chronology Ordering");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                line = br.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
