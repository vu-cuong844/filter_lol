package fillter;

import java.util.List;
import java.util.ArrayList;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;


import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class NoseTwirl extends Fillter_Face {
    private List<Mat> gifFrames = new ArrayList<>();
    private int frameIndex = 0;

   
    
    public NoseTwirl() {
        loadGif("lib\\library\\filter_picture\\traitim.gif", Imgcodecs.IMREAD_UNCHANGED);
    }

    private Mat bufferedImageToMat(BufferedImage bi) {
        Mat mat = new Mat(bi.getHeight(), bi.getWidth(), CvType.CV_8UC4);
        int[] data = new int[bi.getWidth() * bi.getHeight()];
        bi.getRGB(0, 0, bi.getWidth(), bi.getHeight(), data, 0, bi.getWidth());
        byte[] bytes = new byte[bi.getWidth() * bi.getHeight() * 4];
        for (int i = 0; i < data.length; i++) {
            bytes[i * 4] = (byte) (data[i] & 0xFF);        // Blue
            bytes[i * 4 + 1] = (byte) ((data[i] >> 8) & 0xFF);  // Green
            bytes[i * 4 + 2] = (byte) ((data[i] >> 16) & 0xFF); // Red
            bytes[i * 4 + 3] = (byte) ((data[i] >> 24) & 0xFF); // Alpha
        }
        mat.put(0, 0, bytes);
        return mat;
    }

    public void loadGif(String path, int a) {
        try {
            File gifFile = new File(path);
            ImageInputStream stream = ImageIO.createImageInputStream(gifFile);
            var readers = ImageIO.getImageReadersByFormatName("gif");
            var reader = readers.next();
            reader.setInput(stream);
            int numFrames = reader.getNumImages(true);

            for (int i = 0; i < numFrames; i++) {
                BufferedImage bufferedImage = reader.read(i);
                gifFrames.add(bufferedImageToMat(bufferedImage));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void filtering(Mat src, Mat des) { 
        src.copyTo(des);
       faceDetect.detectMultiScale(src, faceDetections);
       Mat flameFrame = gifFrames.get(frameIndex);
       frameIndex = (frameIndex + 1) % gifFrames.size();
       for (Rect rect : faceDetections.toArray()) {
           
           Rect location = new Rect(rect.x, 0,rect.width,rect.height+rect.y);
         
           Mat flameResized = new Mat();
           Imgproc.resize(flameFrame, flameResized, new Size(location.width, location.height));
     
           for (int y = 0; y < flameResized.rows(); y++) {
               for (int x = 0; x < flameResized.cols(); x++) {
                   double[] fgPixel = flameResized.get(y, x);
                   if (fgPixel[3] == 0) continue; // Skip transparent pixels
                   double[] rgb = { fgPixel[0], fgPixel[1], fgPixel[2] };
                   des.put((int) location.y + y, (int) location.x + x, rgb);
               }
           }
           
       }
    }
    
}
