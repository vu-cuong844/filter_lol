package fillter;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;

public class PlasticCamera extends Fillter_Face {
    @Override
    public void filtering(Mat src, Mat des) {
        src.copyTo(des);
        //faceDetect.detectMultiScale(src, faceDetections);
            for (Rect rect : faceDetections.toArray()) {
                Mat faceROI = new Mat(des, rect);
            Mat gray = new Mat();
            Mat edges = new Mat();
            Mat color = new Mat();
        
        // Convert to grayscale
            Imgproc.cvtColor(faceROI, gray, Imgproc.COLOR_BGR2GRAY);
        
        // Apply a median blur to reduce image noise and detail
            Imgproc.medianBlur(gray, gray, 7);
        
        // Detect edges in the image
            Imgproc.adaptiveThreshold(gray, edges, 255, Imgproc.ADAPTIVE_THRESH_MEAN_C, Imgproc.THRESH_BINARY, 9, 9);
        
        // Convert back to color so that it has three channels
            Imgproc.cvtColor(edges, edges, Imgproc.COLOR_GRAY2BGR);
        
        // Reduce the number of colors in the image
            Imgproc.bilateralFilter(faceROI, color, 9, 75, 75);
        
        // Combine edges and color image
            Core.bitwise_and(color, edges, faceROI);
            }
        
    }
}
