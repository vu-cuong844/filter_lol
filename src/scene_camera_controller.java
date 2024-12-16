
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class scene_camera_controller extends main {
    private VideoCapture Camera;

    private boolean isCheckPause = true;

    @FXML
    private Button stop, camera, effect, takePhotor, pause;

    @FXML
    private ImageView imageView;

    public void initialize() {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Camera = cameraManager.getCamera();
        // Camera.set(Videoio.CAP_PROP_FRAME_WIDTH, 600);
        // Camera.set(Videoio.CAP_PROP_FRAME_HEIGHT, 300);
        frameGrabber = new CameraFrameGrabber(Camera, imageView);
        Thread thread = new Thread(frameGrabber);
        thread.setDaemon(true);
        thread.start();
    }

    public void clickOnStop(ActionEvent event) throws IOException{
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        if(mainScene == null) mainScene = new Scene(FXMLLoader.load(getClass().getResource("fxml/camera.fxml")));
        stage.setScene(mainScene);
        stage.show();
    }

    public void clickOnEffect(ActionEvent event)throws IOException{
        currentFillter = 0;
        if(effectScene == null)  effectScene = new Scene(FXMLLoader.load(getClass().getResource("fxml/scene_effect.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(effectScene);
        stage.show();

    }

    public void clickOnTakePhotor(ActionEvent event){
        try {
            Mat frame = new Mat();
            Camera.read(frame);
            fillters[currentFillter].filtering(frame, frame);
            BufferedImage  bufferedImage = new BufferedImage(frame.width(), frame.height(), imageType[currentFillter]);
            frame.get(0, 0, ((DataBufferByte) bufferedImage.getRaster().getDataBuffer() ).getData());
            ImageIO.write(bufferedImage, "jpg", new File("captured.jpg"));


        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Không lưu được ảnh!!!");
        }
    }

    public void clickOnPause(ActionEvent event){
        if (isCheckPause) {
            //dừng camera tạm thời
            CameraManager.pauseCamera(isCheckPause);

            pause.setText("Continue");
            isCheckPause = false;
        }else{
            //Chạy cam
            CameraManager.pauseCamera(isCheckPause);
            pause.setText("Pause");
            isCheckPause = true;
        }
    }
    
}
