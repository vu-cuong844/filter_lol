package fillter;

import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

public class Default extends Fillter_Face {
    @Override
    public void filtering(Mat src, Mat des) {
        // thêm hàm ở đây
        src.copyTo(des);
        faceDetect.detectMultiScale(des, faceDetections);
        for (Rect rect : faceDetections.toArray()) {
            Imgproc.rectangle(des, rect, new Scalar(109, 255,79));
        }
        
    }
}


