import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.imageio.ImageIO;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;


import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class cam_controller extends main {

    // private static final String IMAGE_DIR = "C:\\Users\\ADMIN\\Pictures\\filter_App";
    private static final String IMAGE_DIR = "lib\\library\\pic" ;
    private List<File> imageFiles;


    private VideoCapture Camera;

    @FXML
    private ScrollPane scrollpane;

    @FXML
    private Button takePhotor, camera, effect;

    @FXML
    private ImageView imageView;

    public void initialize() {
        showImage();
        Camera = cameraManager.getCamera();
        Camera.set(Videoio.CAP_PROP_FRAME_WIDTH, 600);
        Camera.set(Videoio.CAP_PROP_FRAME_HEIGHT, 300);
        frameGrabber = new CameraFrameGrabber(Camera, imageView);
        Thread thread = new Thread(frameGrabber);
        thread.setDaemon(true);
        thread.start();
        

    }

    private void showImage(){
        ImageView imageView1;
        imageFiles = getImageFiles(IMAGE_DIR);
        int i = 0;
        GridPane imageContainer = new GridPane();
        scrollpane.setContent(imageContainer);
        for (File file : imageFiles) {
            try (FileInputStream input = new FileInputStream(file)) {
                Image image = new Image(input);
                imageView1 = new ImageView(image);
                imageView1.setFitWidth(100);
                imageView1.setPreserveRatio(true);
                imageContainer.add(imageView1, i, 0);
                i++;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
       
    private List<File> getImageFiles(String dirPath) {
        File dir = new File(dirPath);
        File[] files = dir.listFiles((dir1, name) -> name.toLowerCase().endsWith(".jpg") ||
                name.toLowerCase().endsWith(".png") || name.toLowerCase().endsWith(".jpeg"));

        imageFiles = new ArrayList<>();
        if (files != null) {
            for (File file : files) {
                imageFiles.add(file);
            }
        }
        Collections.reverse(imageFiles);
        return imageFiles;
    }
    

    public void clickOnCamera(ActionEvent event) throws IOException{
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        if(cameraScene == null) cameraScene = new Scene(FXMLLoader.load(getClass().getResource("fxml/scene_camera.fxml")));
        stage.setScene(cameraScene);
        stage.show();
        
    }

    public void clickOnEffect(ActionEvent event) throws IOException{
        currentFillter = 0;//chuyển sang scene efect sẽ set lại curent
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
         if(effectScene == null) effectScene = new Scene(FXMLLoader.load(getClass().getResource("fxml/scene_effect.fxml")));
        stage.setScene(effectScene);
        stage.show();
    }

    public void library(MouseEvent event) throws IOException{
        if(scrollpane != null) scrollpane.setContent(null);
        sem--;
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
         if(picScene == null) picScene = new Scene(FXMLLoader.load(getClass().getResource("fxml/picture.fxml")));
        stage.setScene(picScene);
        stage.show();
    }

    private int sem = 1;
    public void setsroll(MouseEvent event) throws IOException{
        if(sem == 0){
            showImage();
            sem++;
        }
    }

    public void takeEffect(MouseEvent event) throws IOException{
        fillters[currentFillter].setFilter();

    }

    public void clickOnTakePhotor(ActionEvent event){
        try {
            Mat frame = new Mat();
            Camera.read(frame);
            //  Convert Mat to BufferedImage for JavaFX display
            Core.flip(frame, frame, 1);
            fillters[currentFillter].filtering(frame, frame);
            BufferedImage bufferedImage = new BufferedImage(frame.width(), frame.height(), imageType[currentFillter]);
            frame.get(0, 0, ((DataBufferByte) bufferedImage.getRaster().getDataBuffer()).getData());
            // save  the image in jpg format
            LocalDateTime currentTime = LocalDateTime.now();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
            String str = currentTime.format(format);
            ImageIO.write(bufferedImage, "jpg", new File("lib\\library\\pic\\captured_" + str +".jpg"));
            // ImageIO.write(bufferedImage, "jpg", new File("C:\\Users\\ADMIN\\Pictures\\filter_App\\captured_" + str +".jpg"));
            showImage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
