package fillter;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;



import org.opencv.core.CvType;
import org.opencv.core.Mat;

import org.opencv.core.Rect;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;




public class ComicBook extends Fillter_Face {
    private List<Mat> gifFrames = new ArrayList<>();
    private int frameIndex = 0;

    public ComicBook() {
        loadGif("lib\\library\\filter_picture\\fire-flame.gif", Imgcodecs.IMREAD_UNCHANGED);
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

    

    private Mat bufferedImageToMat(BufferedImage bi) {
        //int type = bi.getType() == 0? BufferedImage.TYPE_INT_ARGB : bi.getType();
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




    // private Mat meme = Imgcodecs.imread("lib\\library\\filter_picture\\fire-flame.png");
    // Mat meme1 = new Mat();
    public void filtering(Mat src, Mat des) {
        src.copyTo(des);
        Mat flameFrame = gifFrames.get(frameIndex);
        frameIndex = (frameIndex + 1) % gifFrames.size();
        for (Rect rect : faceDetections.toArray()) {
            // tìm vị trí 2 mắt
            Rect location = new Rect(rect.x+rect.width/12,rect.y+rect.height/7,rect.width*5/12,rect.height/3);
            Rect location2 = new Rect(rect.x+rect.width* 6/12,rect.y+rect.height/7,rect.width*5/12,rect.height/3);
            //chỉnh lại kích thước cho phù hợp 
            Mat flameResized = new Mat();
            Imgproc.resize(flameFrame, flameResized, new Size(location.width, location.height));
            Mat flameResized2 = new Mat();
            Imgproc.resize(flameFrame, flameResized2, new Size(location2.width, location2.height));

            //thêm từng pixel vào vị trí tườn ứng
            for (int y = 0; y < flameResized.rows(); y++) {
                for (int x = 0; x < flameResized.cols(); x++) {
                    //lấy giá trị pixel ở vị trí(y,x)
                    double[] fgPixel = flameResized.get(y, x);
                    if (fgPixel[3] == 0) continue; // Skip transparent pixels
                    double[] rgb = { fgPixel[0], fgPixel[1], fgPixel[2] };
                    des.put((int) location.y + y, (int) location.x + x, rgb);
                }
            }
            for (int y = 0; y < flameResized2.rows(); y++) {
                for (int x = 0; x < flameResized2.cols(); x++) {
                    double[] fgPixel = flameResized2.get(y, x);
                    if (fgPixel[3] == 0) continue; // Skip transparent pixels
                    double[] rgb = { fgPixel[0], fgPixel[1], fgPixel[2] };
                    des.put((int) location2.y + y, (int) location2.x + x, rgb);
                }
            }
        }
    }
}
