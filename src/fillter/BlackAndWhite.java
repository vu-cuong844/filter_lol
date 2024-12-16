package fillter;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;


public class BlackAndWhite extends Fillter_Face {
    private Mat meme = Imgcodecs.imread("lib\\library\\filter_picture\\dollar.jpg");
    @Override
    public void filtering(Mat src, Mat des) {
        src.copyTo(des);
            for (Rect rect : faceDetections.toArray()) {
                Rect fRect = rect;
                Mat resizedEllipse = new Mat();
                Imgproc.resize(meme, resizedEllipse, new Size(fRect.width, fRect.height));
                // Create mask of the ellipse
                Mat mask = new Mat(resizedEllipse.size(), CvType.CV_8UC1, Scalar.all(0));
                Imgproc.ellipse(mask, new Point(fRect.width/2, fRect.height/2), new Size(fRect.width / 2, fRect.height / 2), 0, 0, 360, new Scalar(255), -1);

            // Extract the region of interest (ROI) from the face image
                Mat faceROI = des.submat(fRect);

            // Black out the area of the ellipse in the face ROI
                Mat faceROIBackground = new Mat();
                faceROI.copyTo(faceROIBackground);

            // Extract the ellipse part from the resized ellipse image
                Mat ellipseForeground = new Mat();
                resizedEllipse.copyTo(ellipseForeground, mask);

            // Add the ellipse part to the face ROI
                Core.add(faceROIBackground, ellipseForeground, faceROI);
        }
        
    }
}
