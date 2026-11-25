import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;

public class MediaDataLoader {
    public void addFromCSV(MediaManager manager) {
        try(BufferedReader br = new BufferedReader(new FileReader("data/marvel_media.csv"))) {
            String line = br.readLine();
            while (line != null) {
                try {
                    String[] data = line.split(",");
                    if (data.length != 6) {
                        continue;
                    }
                    Media med = new Media(data[0], data[1], LocalDate.parse(data[2]), Integer.parseInt(data[3]), Boolean.parseBoolean(data[4]), Boolean.parseBoolean(data[5]));
                    manager.addMediaObject(med);
                } catch (Exception e) {
                    IO.println("Failed");
                }
                line = br.readLine();
            }
        } catch (Exception e) {
            IO.println("Try again");
        }
    }
}
