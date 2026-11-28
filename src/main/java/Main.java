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
        Image mcuImage = new Image("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSBaOf7CuKx_qKRzyRFAID-p0lzBSUUx5waQIpgxzLDDA&s=10");
        Image marvelImage = new Image("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ1G8qEy2fGoqCC5znOnfF-MkOqvEBNAxvxkXx3l1t8kg&s=10");
        ImageView mcuImageView = new ImageView(mcuImage);
        ImageView marvelImageView= new ImageView(marvelImage);
        Button button1 = new Button("MCU Release Order");
        Button button2 = new Button("MCU Chronology Order");
        Button button3 = new Button("Non-MCU Release Order");
        Button button4 = new Button("Road to Avengers: Secret Wars");
        Button button5 = new Button("Exit");
        HBox images = new HBox();
        images.setAlignment(Pos.CENTER);
        images.setSpacing(30);
        mcuImageView.setFitWidth(350);
        mcuImageView.setFitHeight(250);
        mcuImageView.setPreserveRatio(true);
        marvelImageView.setFitWidth(350);
        marvelImageView.setFitHeight(200);
        marvelImageView.setPreserveRatio(true);
        images.getChildren().addAll(mcuImageView, marvelImageView);
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
            buttonAction(stage, scene, manager.getMCUByReleaseOrder(), manager, tmdbDetails);
        });

        button2.setOnAction(e -> {
            buttonAction(stage, scene, manager.getMCUByChronologyOrder(), manager, tmdbDetails);
        });

        button3.setOnAction(e -> {
            buttonAction(stage, scene, manager.getNonMCU(), manager, tmdbDetails);
        });

        button4.setOnAction(e -> {
            buttonAction(stage, scene, manager.getSecretWarsWatchOrder(), manager, tmdbDetails);
        });

        button5.setOnAction(e -> {
            stage.close();
        });
        stage.setScene(scene);
        stage.show();
    }

     public void buttonAction(Stage stage, Scene homeScene, ArrayList<Media> mediaList, MediaManager manager, ArrayList<TMDBMediaDetails> tmdbDetails) {
        int row = 0;
        int col = 0;
        int mediaNumber = 1;
        GridPane grid = new GridPane();
        grid.setHgap(40);
        grid.setVgap(30);
        grid.setPadding(new Insets(5, 20, 20, 20));
        for (Media media : mediaList) {
            Image image = null;
            ImageView imageView = null;
            for (TMDBMediaDetails tmdbMediaDetails : tmdbDetails) {
                if ((media.getType().equalsIgnoreCase("TV Show")
                    || media.getType().equalsIgnoreCase("TVShow")) && (media.getTitle().contains(" Season"))) {
                    if (media.getTitle().contains(": Season")) {
                        String[] name = media.getTitle().split(": Season");
                        if (name[0].equals(tmdbMediaDetails.getTitle())) {
                            image = new Image(tmdbMediaDetails.getPosterURL());
                            imageView = new ImageView(image);
                            imageView.setFitWidth(180);
                            imageView.setFitHeight(270);
                            imageView.setPreserveRatio(true);
                            break;
                        }
                    } else {
                        String[] name = media.getTitle().split(" Season");
                        if (name[0].equals(tmdbMediaDetails.getTitle())) {
                            image = new Image(tmdbMediaDetails.getPosterURL());
                            imageView = new ImageView(image);
                            imageView.setFitWidth(180);
                            imageView.setFitHeight(270);
                            imageView.setPreserveRatio(true);
                            break;
                        }
                    }
                } else {
                    if (media.getTitle().equals(tmdbMediaDetails.getTitle())) {
                        image = new Image(tmdbMediaDetails.getPosterURL());
                        imageView = new ImageView(image);
                        imageView.setFitWidth(180);
                        imageView.setFitHeight(270);
                        imageView.setPreserveRatio(true);
                        break;
                    }
                }
            }
            Label label;
            if (media.isSecretWarsWatch()) {
                if (mediaList.equals(manager.getSecretWarsWatchOrder())) {
                    label = new Label("" + mediaNumber + ". " + media.toString());
                } else {
                    Text title = new Text("" + mediaNumber + ". " + media.toString());
                    Text important = new Text(" [Important for Secret Wars]");
                    important.setStyle("-fx-fill: green;");
                    TextFlow flow = new TextFlow(title, important);
                    label = new Label();
                    label.setGraphic(flow);
                }
            } else {
                label = new Label("" + mediaNumber + ". " + media.toString());
            }
            label.setWrapText(true);
            label.setMaxWidth(350);
            label.setStyle(
                "-fx-font-family: 'Arial';" +
                "-fx-font-size: 17px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #292929;"
            );
            CheckBox check = new CheckBox();
            check.setStyle(
                "-fx-font-family: 'Arial';" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #333333;"
            );
            check.setScaleX(1.15);
            check.setScaleY(1.15);
            check.setSelected(media.isWatched());
            if (media.isWatched()) {
                check.setText("Watched");
            } else {
                check.setText("Not Watched");
            }
            check.setOnAction(c -> {
                media.setWatched(check.isSelected());
                if (media.isWatched()) {
                    check.setText("Watched");
                } else {
                    check.setText("Not Watched");
                }
                try (PrintWriter pw = new PrintWriter(new FileOutputStream("data/marvel_media.csv"))) {
                    for (Media media1 : manager.getMedia()) {
                        pw.println(media1.getTitle() + "," + media1.getType() + "," + media1.getReleaseDate() + "," + media1.getChronologyOrder() + "," + 
                                media1.isMCU() + "," + media1.isSecretWarsWatch() + "," + media1.isWatched());
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            });
            VBox mediaBox = new VBox(10);
            if (imageView != null) {
                mediaBox.getChildren().add(imageView);
            }
            mediaBox.getChildren().addAll(label, check);
            grid.add(mediaBox, col, row);
            col++;
            if (col == 2) {
                col = 0;
                row++;
            }
            mediaNumber++;
        }
        Button back = new Button("Back to HomePage");
        back.setOnAction(ee -> {
            stage.setScene(homeScene);
        });
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        scrollPane.setContent(grid);
        VBox layout = new VBox(back, scrollPane);
        Scene categoryScene = new Scene(layout, 1000, 700);
        stage.setScene(categoryScene);
        stage.show();
     }
}
