package fillter;

import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class Frog extends Fillter_Face {
    private Mat meme = Imgcodecs.imread("lib\\library\\filter_picture\\mask.png", Imgcodecs.IMREAD_UNCHANGED);
    private Mat meme1 = new Mat();
    @Override
    public void filtering(Mat src, Mat des) {
        src.copyTo(des);
            for (Rect rect : faceDetections.toArray()) {
                if(rect.y - rect.height/6 >=0 && rect.y+rect.height*7/6<=src.rows()) {rect.y = rect.y-rect.height/6; rect.height = rect.height*4/3;}
                
                Rect location = new Rect(rect.x,rect.y,rect.width,rect.height*11/20);
                Imgproc.resize(meme, meme1, new Size(location.width, location.height));
                for (int y = 0; y < meme1.rows(); y++) {
                    for (int x = 0; x < meme1.cols(); x++) {
                        double[] fgPixel = meme1.get(y, x);
                        if(fgPixel[3] != 255) continue;
                        else {
                            double[] rgb = {fgPixel[0], fgPixel[1], fgPixel[2]};
                            des.put((int) location.y + y, (int) location.x + x, rgb);
                        }
                    }
                }
            }
    }
  
}
