package fillter;

import org.opencv.core.Mat;
import org.opencv.objdetect.CascadeClassifier;

public class Fillter_Face extends Fillter {
        protected static CascadeClassifier faceDetect = new CascadeClassifier("lib\\library\\haarcascade_frontalface_default.xml");
        // ****** SUA *******
        public static void detect(Mat src) {
                faceDetect.detectMultiScale(src, faceDetections);
        }
        public void setFilter(){};

}
