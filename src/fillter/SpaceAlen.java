package fillter;

import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class SpaceAlen extends Fillter_Face{
    @Override
    public void filtering(Mat src, Mat des) {
        Mat blur;
        src.copyTo(des);
        //faceDetect.detectMultiScale(des, faceDetections);

        for(Rect roi : faceDetections.toArray()){
            blur = new Mat();
            Imgproc.GaussianBlur(new Mat(src,roi), blur, new Size(15,15), 30);
            blur.copyTo(des.submat(roi));
        }
    }
    
}
