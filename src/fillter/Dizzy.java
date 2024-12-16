package fillter;
import java.util.List;
import java.util.ArrayList;

import java.io.File;

import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;



public class Dizzy extends Fillter_Face {
    private String Path = "lib\\library\\filter_picture\\kinh";
    private Mat meme;
    private Mat meme1;
    private int index = 0;
    private List<Mat> iMats;

    public Dizzy(){
        iMats = getImageFiles(Path);
        meme = iMats.get(index+1);
        meme1 =new Mat();
    }

    @Override
    public void setFilter() {
        index = (index+1) % iMats.size();
        meme = iMats.get(index);
    }

    private List<Mat> getImageFiles(String dirPath) {
        File dir = new File(dirPath);
        File[] files = dir.listFiles((dir1, name) -> name.toLowerCase().endsWith(".jpg") ||
                name.toLowerCase().endsWith(".png") || name.toLowerCase().endsWith(".jpeg"));

        List<Mat> imageFiles = new ArrayList<>();
        if (files != null) {
            for (File file : files) {
                imageFiles.add(Imgcodecs.imread(file.getAbsolutePath(), Imgcodecs.IMREAD_UNCHANGED));
            }
        }
        return imageFiles;
    }
    @Override
    public void filtering(Mat src, Mat des) {

        src.copyTo(des);
            for (Rect rect : faceDetections.toArray()) {
                Rect location = new Rect(rect.x+rect.width/12,rect.y+rect.height/6,rect.width*5/6,rect.height* 2/5);
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
