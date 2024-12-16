

import java.util.Arrays;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

import fillter.Fillter_Face;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.ImageView;

public class CameraFrameGrabber extends main implements Runnable {

    private BufferedImage bufferedImage;
    private final VideoCapture camera;
    private List<ImageView> imageViews;
    private BufferedImage[] bufferedImages = new BufferedImage[24] ;
    // truyền vào camera, biến trạng thái fillter để xác định dùng filter nào, các imageView
    public CameraFrameGrabber(VideoCapture camera, ImageView... imageView) {
        this.camera = camera;
        this.imageViews = Arrays.asList(imageView);
    }

    

    @Override
    public void run() {
        Mat frame = new Mat();
        Mat res = new Mat();
        int totalImages = imageViews.size();
        
        if (camera.isOpened()) {
        while (true) {
            camera.read(frame);
            int frameWidth = frame.width();
            int frameHeight = frame.height();
            Core.flip(frame, frame, 1);
            Fillter_Face.detect(frame);
            for(int i = 0 ; i < totalImages ; i++){
                try {
                    fillters[currentFillter + i].filtering(frame,res);
                } catch (Exception e) { }
                bufferedImage = new BufferedImage(frameWidth, frameHeight, imageType[i + currentFillter]);
                res.get(0, 0, ((DataBufferByte) bufferedImage.getRaster().getDataBuffer()).getData());
                bufferedImages[i] = bufferedImage;
            }
            
            Platform.runLater(() -> {
                for (int i = 0 ; i < totalImages ; i++) {
                    imageViews.get(i).setImage(SwingFXUtils.toFXImage(bufferedImages[i], null));
                    
                }
                
            });
            
        }
            
        }
    }
}