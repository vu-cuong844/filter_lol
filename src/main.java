import fillter.*;

import org.opencv.core.Core;
import org.opencv.videoio.VideoCapture;

import java.awt.image.BufferedImage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.Optional;

public class main  extends Application{
    static{
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }
    protected Parent root;
    protected Stage stage;
    protected static Scene mainScene = null ;
    protected static Scene cameraScene = null ;
    protected static Scene effectScene = null ;
    protected static Scene picScene = null ;
    protected static Scene loadScene = null;

    protected VideoCapture Camera;
    protected CameraManager  cameraManager = new CameraManager();
    protected CameraFrameGrabber frameGrabber;

    protected static int currentFillter = 0; // trang thái của của fillter
    protected static BlackAndWhite BLACK_AND_WHITE;
    protected static BlockHead BLOCK_HEAD;
    protected static BugOut BUG_OUT;
    protected static Bulge BULGE;
    protected static Chipmunk CHIPMUNK;
    protected static ColorPencil COLOR_PENCIL;
    protected static ComicBook COMIC_BOOK;
    protected static Default DEFAULT;
    protected static Dent DENT;
    protected static Dizzy DIZZY;
    protected static FishEye FISH_EYE;
    protected static Frog FROG;
    protected static Glow GLOW;
    protected static LightTunnel LIGHT_TUNNEL;
    protected static Mirror MIRROR;
    protected static NoseTwirl NOSE_TWIRL;
    protected static PlasticCamera PLASTIC_CAMERA;
    protected static Sepia SEPIA;
    protected static SpaceAlen SPACE_ALIEN;
    protected static Squeeze SQUEEZE;
    protected static Stretch STRETCH;
    protected static ThermalCamera THERMAL_CAMERA;
    protected static Twirl TWIRL;
    protected static XRay X_RAY;

    protected static Fillter[] fillters = {DEFAULT, SPACE_ALIEN, NOSE_TWIRL, CHIPMUNK, DIZZY, BLOCK_HEAD, BUG_OUT, FROG, SEPIA, BLACK_AND_WHITE , PLASTIC_CAMERA, COMIC_BOOK, COLOR_PENCIL, GLOW, THERMAL_CAMERA, X_RAY, BULGE, DENT, TWIRL, SQUEEZE, MIRROR, LIGHT_TUNNEL, FISH_EYE, STRETCH};

    protected static int[] imageType ={
            BufferedImage.TYPE_3BYTE_BGR,
            BufferedImage.TYPE_3BYTE_BGR, 
            BufferedImage.TYPE_3BYTE_BGR, 
            BufferedImage.TYPE_BYTE_GRAY,  
            BufferedImage.TYPE_3BYTE_BGR, 
            BufferedImage.TYPE_3BYTE_BGR, 
            BufferedImage.TYPE_3BYTE_BGR, 
            BufferedImage.TYPE_3BYTE_BGR, 
            BufferedImage.TYPE_3BYTE_BGR, 
            BufferedImage.TYPE_3BYTE_BGR, 
            BufferedImage.TYPE_3BYTE_BGR, 
            BufferedImage.TYPE_3BYTE_BGR, 
            BufferedImage.TYPE_3BYTE_BGR, 
            BufferedImage.TYPE_3BYTE_BGR, 
            BufferedImage.TYPE_3BYTE_BGR, 
            BufferedImage.TYPE_3BYTE_BGR, 
            BufferedImage.TYPE_3BYTE_BGR, 
            BufferedImage.TYPE_3BYTE_BGR, 
            BufferedImage.TYPE_3BYTE_BGR, 
            BufferedImage.TYPE_3BYTE_BGR, 
            BufferedImage.TYPE_3BYTE_BGR, 
            BufferedImage.TYPE_3BYTE_BGR,
            BufferedImage.TYPE_3BYTE_BGR,
            BufferedImage.TYPE_3BYTE_BGR,
            BufferedImage.TYPE_3BYTE_BGR,
        };

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            
            if(loadScene == null) loadScene = new Scene(FXMLLoader.load(getClass().getResource("fxml/loading.fxml")));
            primaryStage.setScene(loadScene);
            primaryStage.setTitle("FilterApp");
            primaryStage.show();
            primaryStage.setOnCloseRequest(event -> {
                event.consume();
                exit(primaryStage);
            });
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void main(String[] args) {
        launch(args);
    }

    public void exit(Stage stage){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setHeaderText("Bạn chán rồi à???");
        alert.setContentText("Bạn muốn thoát chứ???");

        Optional<ButtonType> result = alert.showAndWait();
        result.ifPresent(buttonType -> {
        if (buttonType == ButtonType.OK) {
            stage.close();
        }
    });
    }
}

