package fillter;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;


public class Chipmunk extends Fillter {
    @Override
    public void filtering(Mat src, Mat des) {
        //thêm code
        src.copyTo(des);
        Imgproc.cvtColor(src, des, Imgproc.COLOR_BGR2GRAY);
    }
}
