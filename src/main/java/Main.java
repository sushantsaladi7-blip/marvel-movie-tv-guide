import tmdb.TMDBMediaDetails;
import javafx.application.Application;
import javafx.stage.Stage;
import media.Media;
import media.MediaDataLoader;
import media.MediaManager;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.control.CheckBox;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import tmdb.TMDBService;
import java.util.ArrayList;
public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Marvel Media Guide");
        stage.setWidth(1000);
        stage.setHeight(700);
        Label label = new Label("Your journey to Avengers: Secret Wars");
        label.setStyle(
            "-fx-font-size: 28px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: white;"
        );
        Image mcu = new Image("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSBaOf7CuKx_qKRzyRFAID-p0lzBSUUx5waQIpgxzLDDA&s=10");
        Image marvel = new Image("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ1G8qEy2fGoqCC5znOnfF-MkOqvEBNAxvxkXx3l1t8kg&s=10");
        ImageView mcuview = new ImageView(mcu);
        ImageView marvelview= new ImageView(marvel);
        Button button1 = new Button("MCU Release Order");
        Button button2 = new Button("MCU Chronology Order");
        Button button3 = new Button("Non-MCU Release Order");
        Button button4 = new Button("Road to Avengers: Secret Wars");
        Button button5 = new Button("Exit");
        HBox images = new HBox();
        images.setAlignment(Pos.CENTER);
        images.setSpacing(30);
        mcuview.setFitWidth(350);
        mcuview.setFitHeight(250);
        mcuview.setPreserveRatio(true);
        marvelview.setFitWidth(350);
        marvelview.setFitHeight(200);
        marvelview.setPreserveRatio(true);
        images.getChildren().addAll(mcuview, marvelview);
        GridPane buttons = new GridPane();
        for (Button button : new Button[]{
            button1, button2, button3, button4, button5
                }) {
                    button.setPrefWidth(300);
                    button.setStyle(
                        "-fx-background-color: #263746;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 15px;" +
                        "-fx-background-radius: 8px;" +
                        "-fx-cursor: hand;"
                    );
        }
        MediaDataLoader loader = new MediaDataLoader();
        MediaManager manager = new MediaManager();
        loader.addFromCSV(manager);
        TMDBService tmdbService = new TMDBService();
        ArrayList<TMDBMediaDetails> tmdbDetails = tmdbService.searchMedia(manager.getMedia());
        buttons.add(button1, 0, 0);
        buttons.add(button2, 1, 0);
        buttons.add(button3, 0, 1);
        buttons.add(button4, 1, 1);
        buttons.setHgap(20);
        buttons.setVgap(20);
        buttons.setAlignment(Pos.CENTER);
        VBox box = new VBox();
        box.getChildren().addAll(label, images, buttons, button5);
        box.setAlignment(Pos.CENTER);
        box.setSpacing(25);
        box.setPadding(new Insets(30));
        box.setStyle("-fx-background-color: #101820;");
        Scene scene = new Scene(box);
        button1.setOnAction(e -> {
            int row = 0;
            int col = 0;
            int i = 1;
            GridPane grid1 = new GridPane();
            grid1.setHgap(15);
            grid1.setVgap(15);
            grid1.setPadding(new Insets(5, 20, 20, 20));
            for (Media m : manager.getMCUByReleaseOrder()) {
                Image image = null;
                ImageView imageView = null;
                for (TMDBMediaDetails tmdb : tmdbDetails) {
                    if ((m.getType().equalsIgnoreCase("TV Show")
                        || m.getType().equalsIgnoreCase("TVShow")) && (m.getTitle().contains(" Season"))) {
                        if (m.getTitle().contains(": Season")) {
                            String[] name = m.getTitle().split(": Season");
                            if (name[0].equals(tmdb.getTitle())) {
                                image = new Image(tmdb.getPosterURL());
                                imageView = new ImageView(image);
                                imageView.setFitWidth(350);
                                imageView.setFitHeight(250);
                                imageView.setPreserveRatio(true);
                                break;
                            }
                        } else {
                            String[] name = m.getTitle().split(" Season");
                            if (name[0].equals(tmdb.getTitle())) {
                                image = new Image(tmdb.getPosterURL());
                                imageView = new ImageView(image);
                                imageView.setFitWidth(350);
                                imageView.setFitHeight(250);
                                imageView.setPreserveRatio(true);
                                break;
                            }
                        }
                    } else {
                        if (m.getTitle().equals(tmdb.getTitle())) {
                            image = new Image(tmdb.getPosterURL());
                            imageView = new ImageView(image);
                            imageView.setFitWidth(350);
                            imageView.setFitHeight(250);
                            imageView.setPreserveRatio(true);
                            break;
                        }
                    }
                }
                Label l;
                if (m.isSecretWarsWatch()) {
                    Text title = new Text("" + i + ". " + m.toString());
                    Text important = new Text(" [Important for Secret Wars]");
                    important.setStyle("-fx-fill: green;");
                    TextFlow flow = new TextFlow(title, important);
                    l = new Label();
                    l.setGraphic(flow);
                } else {
                    l = new Label("" + i + ". " + m.toString());
                }
                CheckBox check = new CheckBox();
                check.setSelected(m.isWatched());
                check.setOnAction(c -> {
                    m.setWatched(check.isSelected());
                    try (PrintWriter pw = new PrintWriter(new FileOutputStream("data/marvel_media.csv"))) {
                        for (Media m1 : manager.getMedia()) {
                            pw.println(m1.getTitle() + "," + m1.getType() + "," + m1.getReleaseDate() + "," + m1.getChronologyOrder() + "," + 
                                    m1.isMCU() + "," + m1.isSecretWarsWatch() + "," + m1.isWatched());
                        }
                    } catch (FileNotFoundException fe) {
                        fe.printStackTrace();
                    }
                });
                VBox movie = new VBox();
                if (imageView != null) {
                    movie.getChildren().add(imageView);
                }
                movie.getChildren().add(l);
                grid1.add(movie, col, row);
                grid1.add(check, col + 1, row);
                col += 2;
                if (col == 4) {
                    col = 0;
                    row++;
                }
                i++;
            }
            Button back = new Button("Back to HomePage");
            back.setOnAction(ee -> {
                stage.setScene(scene);
            });
            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setFitToWidth(true);
            scrollPane.setContent(grid1);
            VBox layout = new VBox(back, scrollPane);
            Scene scene1 = new Scene(layout, 1000, 700);
            stage.setScene(scene1);
            stage.show();
        });
        button2.setOnAction(e -> {
            int row = 0;
            int col = 0;
            int i = 1;
            GridPane grid2 = new GridPane();
            grid2.setHgap(15);
            grid2.setVgap(15);
            grid2.setPadding(new Insets(5, 20, 20, 20));
            for (Media m : manager.getMCUByChronologyOrder()) {
                Label l;
                if (m.isSecretWarsWatch()) {
                    Text title = new Text("" + i + ". " + m.toString());
                    Text important = new Text(" [Important for Secret Wars]");
                    important.setStyle("-fx-fill: green;");
                    TextFlow flow = new TextFlow(title, important);
                    l = new Label();
                    l.setGraphic(flow);
                } else {
                    l = new Label("" + i + ". " + m.toString());
                }
                CheckBox check = new CheckBox();
                check.setSelected(m.isWatched());
                check.setOnAction(c -> {
                    m.setWatched(check.isSelected());
                    try (PrintWriter pw = new PrintWriter(new FileOutputStream("data/marvel_media.csv"))) {
                        for (Media m1 : manager.getMedia()) {
                            pw.println(m1.getTitle() + "," + m1.getType() + "," + m1.getReleaseDate() + "," + m1.getChronologyOrder() + "," + 
                                    m1.isMCU() + "," + m1.isSecretWarsWatch() + "," + m1.isWatched());
                        }
                    } catch (FileNotFoundException fe) {
                        fe.printStackTrace();
                    }
                });
                grid2.add(l, col, row);
                grid2.add(check, col + 1, row);
                col += 2;
                if (col == 4) {
                    col = 0;
                    row++;
                }
                i++;
            }
            Button back = new Button("Back to HomePage");
            back.setOnAction(ee -> {
                stage.setScene(scene);
            });
            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setFitToWidth(true);
            scrollPane.setContent(grid2);
            VBox layout = new VBox(back, scrollPane);
            Scene scene2 = new Scene(layout, 1000, 700);
            stage.setScene(scene2);
            stage.show();
        });
        button3.setOnAction(e -> {
            int row = 0;
            int col = 0;
            int i = 1;
            GridPane grid3 = new GridPane();
            grid3.setHgap(15);
            grid3.setVgap(15);
            grid3.setPadding(new Insets(5, 20, 20, 20));
            for (Media m : manager.getNonMCU()) {
                Image image = null;
                ImageView imageView = null;
                for (TMDBMediaDetails tmdb : tmdbDetails) {
                    if (m.getTitle().equals(tmdb.getTitle())) {
                        image = new Image(tmdb.getPosterURL());
                        imageView = new ImageView(image);
                        imageView.setFitWidth(350);
                        imageView.setFitHeight(250);
                        imageView.setPreserveRatio(true);
                        break;
                    }
                }
                Label l;
                if (m.isSecretWarsWatch()) {
                    Text title = new Text("" + i + ". " + m.toString());
                    Text important = new Text(" [Important for Secret Wars]");
                    important.setStyle("-fx-fill: green;");
                    TextFlow flow = new TextFlow(title, important);
                    l = new Label();
                    l.setGraphic(flow);
                } else {
                    l = new Label("" + i + ". " + m.toString());
                }
                CheckBox check = new CheckBox();
                check.setSelected(m.isWatched());
                check.setOnAction(c -> {
                    m.setWatched(check.isSelected());
                    try (PrintWriter pw = new PrintWriter(new FileOutputStream("data/marvel_media.csv"))) {
                        for (Media m1 : manager.getMedia()) {
                            pw.println(m1.getTitle() + "," + m1.getType() + "," + m1.getReleaseDate() + "," + m1.getChronologyOrder() + "," + 
                                    m1.isMCU() + "," + m1.isSecretWarsWatch() + "," + m1.isWatched());
                        }
                    } catch (FileNotFoundException fe) {
                        fe.printStackTrace();
                    }
                });
                VBox movie = new VBox();
                if (imageView != null) {
                    movie.getChildren().add(imageView);
                }
                movie.getChildren().add(l);
                grid3.add(movie, col, row);
                grid3.add(check, col + 1, row);
                col += 2;
                if (col == 4) {
                    col = 0;
                    row++;
                }
                i++;
            }
            Button back = new Button("Back to HomePage");
            back.setOnAction(ee -> {
                stage.setScene(scene);
            });
            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setFitToWidth(true);
            scrollPane.setContent(grid3);
            VBox layout = new VBox(back, scrollPane);
            Scene scene3 = new Scene(layout, 1000, 700);
            stage.setScene(scene3);
            stage.show();
        });
        button4.setOnAction(e -> {
            int row = 0;
            int col = 0;
            int i = 1;
            GridPane grid4 = new GridPane();
            grid4.setHgap(15);
            grid4.setVgap(15);
            grid4.setPadding(new Insets(5, 20, 20, 20));
            for (Media m : manager.getSecretWarsWatchOrder()) {
                Label l = new Label("" + i + ". " + m.toString());
                CheckBox check = new CheckBox();
                check.setSelected(m.isWatched());
                check.setOnAction(c -> {
                    m.setWatched(check.isSelected());
                    try (PrintWriter pw = new PrintWriter(new FileOutputStream("data/marvel_media.csv"))) {
                        for (Media m1 : manager.getMedia()) {
                            pw.println(m1.getTitle() + "," + m1.getType() + "," + m1.getReleaseDate() + "," + m1.getChronologyOrder() + "," + 
                                    m1.isMCU() + "," + m1.isSecretWarsWatch() + "," + m1.isWatched());
                        }
                    } catch (FileNotFoundException fe) {
                        fe.printStackTrace();
                    }
                });
                grid4.add(l, col, row);
                grid4.add(check, col + 1, row);
                col += 2;
                if (col == 4) {
                    col = 0;
                    row++;
                }
                i++;
            }
            Button back = new Button("Back to HomePage");
            back.setOnAction(ee -> {
                stage.setScene(scene);
            });
            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setFitToWidth(true);
            scrollPane.setContent(grid4);
            VBox layout = new VBox(back, scrollPane);
            Scene scene4 = new Scene(layout, 1000, 700);
            stage.setScene(scene4);
            stage.show();
        });
        button5.setOnAction(e -> {
            stage.close();
        });
        stage.setScene(scene);
        stage.show();
    }
}
