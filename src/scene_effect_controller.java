
import java.io.IOException;
import org.opencv.videoio.VideoCapture;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
public class scene_effect_controller extends main{
    private VideoCapture Camera;

    @FXML
    private Button takePhotor, camera, effect;

    @FXML
    private ImageView imageView_0_0;
    @FXML
    private ImageView imageView_1_0;
    @FXML
    private ImageView imageView_2_0;
    @FXML
    private ImageView imageView_3_0;
    @FXML
    private ImageView imageView_4_0;
    @FXML
    private ImageView imageView_5_0;
    @FXML
    private ImageView imageView_6_0;
    @FXML
    private ImageView imageView_0_1;
    @FXML
    private ImageView imageView_1_1;
    @FXML
    private ImageView imageView_2_1;
    @FXML
    private ImageView imageView_3_1;
    @FXML
    private ImageView imageView_4_1;
    @FXML
    private ImageView imageView_5_1;
    @FXML
    private ImageView imageView_6_1;
    @FXML
    private ImageView imageView_0_2;
    @FXML
    private ImageView imageView_1_2;
    @FXML
    private ImageView imageView_2_2;
    @FXML
    private ImageView imageView_3_2;
    @FXML
    private ImageView imageView_4_2;
    @FXML
    private ImageView imageView_5_2;
    @FXML
    private ImageView imageView_6_2;



    public void initialize() {
        Camera = cameraManager.getCamera();
        frameGrabber = new CameraFrameGrabber(Camera, imageView_0_0, imageView_0_1, imageView_0_2, imageView_1_0, imageView_1_1, imageView_1_2, imageView_2_0, imageView_2_1, imageView_2_2, imageView_3_0, imageView_3_1, imageView_3_2);// imageView_4_0, imageView_4_1, imageView_4_2, imageView_5_0, imageView_5_1, imageView_5_2, imageView_6_0, imageView_6_1, imageView_6_2);
        Thread thread = new Thread(frameGrabber);
        thread.setDaemon(true);
        thread.start();
    }

    // //phần này để chọn filter
    public void clickOnImageView_0_0(MouseEvent event) throws IOException{
        currentFillter = 0;
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        if(mainScene == null) mainScene = new Scene(FXMLLoader.load(getClass().getResource("fxml/camera.fxml")));
        stage.setScene(mainScene);
        stage.show();
    }
    public void clickOnImageView_0_1(MouseEvent event) throws IOException{
        currentFillter = 1;
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        if(mainScene == null) mainScene = new Scene(FXMLLoader.load(getClass().getResource("fxml/camera.fxml")));
        stage.setScene(mainScene);
        stage.show();
    }
    public void clickOnImageView_0_2(MouseEvent event) throws IOException{
        currentFillter = 2;
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        if(mainScene == null) mainScene = new Scene(FXMLLoader.load(getClass().getResource("fxml/camera.fxml")));
        stage.setScene(mainScene);
        stage.show();
    }
    public void clickOnImageView_1_0(MouseEvent event) throws IOException{
        currentFillter = 3;
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        if(mainScene == null) mainScene = new Scene(FXMLLoader.load(getClass().getResource("fxml/camera.fxml")));
        stage.setScene(mainScene);
        stage.show();
    }
    public void clickOnImageView_1_1(MouseEvent event) throws IOException{
        currentFillter = 4;
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        if(mainScene == null) mainScene = new Scene(FXMLLoader.load(getClass().getResource("fxml/camera.fxml")));
        stage.setScene(mainScene);
        stage.show();
    }
    public void clickOnImageView_1_2(MouseEvent event) throws IOException{
        currentFillter = 5;
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        if(mainScene == null) mainScene = new Scene(FXMLLoader.load(getClass().getResource("fxml/camera.fxml")));
        stage.setScene(mainScene);
        stage.show();
    }
    public void clickOnImageView_2_0(MouseEvent event) throws IOException{
        currentFillter = 6;
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        if(mainScene == null) mainScene = new Scene(FXMLLoader.load(getClass().getResource("fxml/camera.fxml")));
        stage.setScene(mainScene);
        stage.show();
    }
    public void clickOnImageView_2_1(MouseEvent event) throws IOException{
        currentFillter = 7;
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        if(mainScene == null) mainScene = new Scene(FXMLLoader.load(getClass().getResource("fxml/camera.fxml")));
        stage.setScene(mainScene);
        stage.show();
    }
    public void clickOnImageView_2_2(MouseEvent event) throws IOException{
        currentFillter = 8;
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        if(mainScene == null) mainScene = new Scene(FXMLLoader.load(getClass().getResource("fxml/camera.fxml")));
        stage.setScene(mainScene);
        stage.show();
    }
    public void clickOnImageView_3_0(MouseEvent event) throws IOException{
        currentFillter = 9;
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        if(mainScene == null) mainScene = new Scene(FXMLLoader.load(getClass().getResource("fxml/camera.fxml")));
        stage.setScene(mainScene);
        stage.show();
    }
    public void clickOnImageView_3_1(MouseEvent event) throws IOException{
        currentFillter = 10;
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        if(mainScene == null) mainScene = new Scene(FXMLLoader.load(getClass().getResource("fxml/camera.fxml")));
        stage.setScene(mainScene);
        stage.show();
    }
    public void clickOnImageView_3_2(MouseEvent event) throws IOException{
        currentFillter = 11;
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        if(mainScene == null) mainScene = new Scene(FXMLLoader.load(getClass().getResource("fxml/camera.fxml")));
        stage.setScene(mainScene);
        stage.show();
    }
    
}
