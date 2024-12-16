import fillter.*;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class controller_loading extends main implements Initializable {
    @FXML
    private ProgressBar progressBar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Timeline timeline = new Timeline(
            new KeyFrame(Duration.millis(50), event -> {
                double currentProgress = progressBar.getProgress() + 0.005;
                progressBar.setProgress(currentProgress);
            })
        );

        timeline.setCycleCount(200); // Chạy trong 10 giây (100 lần, mỗi lần 50ms)
        timeline.setOnFinished(event -> loadMainScene()); // Chuyển sang màn hình chính khi hoàn thành
        timeline.play();

        // Khởi tạo Dizzy trong một thread khác
        new Thread(this::initializeFilter).start();
        
    }

    private void initializeFilter() {
        // Khởi tọa các biến cần thiết cho toàn bộ app

        try {
            main.fillters[0] = new Default();
            main.fillters[1] = new SpaceAlen();
            main.fillters[2] = new NoseTwirl();
            main.fillters[3] = new Chipmunk();
            main.fillters[4] = new Dizzy();
            main.fillters[5] = new BlockHead();
            main.fillters[6] = new BugOut();
            main.fillters[7] = new Frog();
            main.fillters[8] = new Sepia();
            main.fillters[9] = new BlackAndWhite();
            main.fillters[10] = new PlasticCamera();
            main.fillters[11] = new ComicBook();
            main.fillters[12] = new ColorPencil();
            main.fillters[13] = new Glow();
            main.fillters[14] = new ThermalCamera();
            main.fillters[15] = new XRay();
            main.fillters[16] = new Bulge();
            main.fillters[17] = new Dent();
            main.fillters[18] = new Twirl();
            main.fillters[19] = new Squeeze();
            main.fillters[20] = new Mirror();
            main.fillters[21] = new LightTunnel();
            main.fillters[22] = new FishEye();
            main.fillters[23] = new Stretch();
 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadMainScene() {
        Platform.runLater(() -> {
            try {
                stage = (Stage) progressBar.getScene().getWindow();
                root = FXMLLoader.load(getClass().getResource("fxml/camera.fxml")); 
                if(mainScene == null) mainScene = new Scene(root);
                stage.setScene(mainScene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
