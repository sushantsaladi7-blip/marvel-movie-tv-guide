import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.control.CheckBox;
public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Marvel Media Guide");
        stage.setWidth(1000);
        stage.setHeight(700);
        VBox buttons = new VBox();
        Label label = new Label("Your journey to Avengers: Secret Wars");
        label.setStyle("-fx-font-size: 26px; -fx-font-weight: bold;");
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
        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(12);
        for (Button button : new Button[]{
            button1, button2, button3, button4, button5
                }) {
                    button.setPrefWidth(300);
                    button.setStyle("-fx-font-size: 15px;");
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
        buttons.getChildren().addAll(button1, button2, button3, button4, button5);
        VBox box = new VBox();
        box.getChildren().addAll(label, images, buttons);
        box.setAlignment(Pos.CENTER);
        box.setSpacing(25);
        box.setPadding(new Insets(30));
        box.setStyle("-fx-background-color: #101820;");
        Scene scene = new Scene(box);
        button1.setOnAction(e -> {
            int row = 1;
            int col = 0;
            int i = 1;
            GridPane grid1 = new GridPane();
            for (Media m : manager.getMCUByReleaseOrder()) {
                Label l = new Label("" + i + ". " + m.toString());
                CheckBox check = new CheckBox();
                grid1.add(l, col, row);
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
            grid1.add(back, 0, 0);
            Scene scene1 = new Scene(grid1);
            stage.setScene(scene1);
            stage.show();
        });
        button2.setOnAction(e -> {
            int row = 1;
            int col = 0;
            int i = 1;
            GridPane grid2 = new GridPane();
            for (Media m : manager.getMCUByChronologyOrder()) {
                Label l = new Label("" + i + ". " + m.toString());
                CheckBox check = new CheckBox();
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
            grid2.add(back, 0, 0);
            Scene scene2 = new Scene(grid2);
            stage.setScene(scene2);
            stage.show();
        });
        button3.setOnAction(e -> {
            int row = 1;
            int col = 0;
            int i = 1;
            GridPane grid3 = new GridPane();
            for (Media m : manager.getNonMCU()) {
                Label l = new Label("" + i + ". " + m.toString());
                CheckBox check = new CheckBox();
                grid3.add(l, col, row);
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
            grid3.add(back, 0, 0);
            Scene scene3 = new Scene(grid3);
            stage.setScene(scene3);
            stage.show();
        });
        button4.setOnAction(e -> {
            int row = 1;
            int col = 0;
            int i = 1;
            GridPane grid4 = new GridPane();
            for (Media m : manager.getSecretWarsWatchOrder()) {
                Label l = new Label("" + i + ". " + m.toString());
                CheckBox check = new CheckBox();
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
            grid4.add(back, 0, 0);
            Scene scene4 = new Scene(grid4);
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
