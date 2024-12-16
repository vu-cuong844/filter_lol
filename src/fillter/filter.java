package fillter;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.objdetect.Objdetect;

public abstract class filter {
    private static double absFaceSize = 0;
    private static Size minSize;
    private static Size maxSize = new Size();
    private static MatOfRect faceRect = new MatOfRect();
    private static CascadeClassifier faceDetect = new CascadeClassifier("lib/haarcascade_frontalface_default.xml");

    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }
    public static final int 
        DEFAULT = 0,
        SPACE_ALIEN = 1,
        NOSE_TWIRL = 2,
        CHIPMUNK = 3,
        DIZZY = 4,
        BLOCK_HEAD = 5,
        BUG_OUT = 6,
        FROG = 7,
        SEPIA = 8,
        BLACK_AND_WHITE = 10,
        PLASTIC_CAMERA = 11,
        COMIC_BOOK = 12,
        COLOR_PENCIL = 13,
        GLOW = 14,
        THERMAL_CAMERA = 15,
        X_RAY = 16,
        BULGE = 17,
        DENT = 18,
        TWIRL = 19,
        SQUEEZE = 20,
        MIRROR = 21,
        LIGHT_TUNNEL = 22,
        FISH_EYE = 23,
        STRETCH = 24;
    public static void setFilter(Mat img, Mat des,int filterId) {
        if (filterId == 0) {
            img.copyTo(des);
        }
        detectFace(img);
        switch (filterId) {
            case 1:
                spaceAlien(img, des);
                break;
            case 2:
                noseTwirl(img, des);
                
        
            default:
                break;
        }
    }
    public static void detectFace(Mat img) {
        if (absFaceSize != img.rows() * 0.3f) {
            absFaceSize = img.rows() * 0.3f;
            minSize = new Size(absFaceSize, absFaceSize);
        }
        Mat grayImg = new Mat();
        Imgproc.cvtColor(img, grayImg, Imgproc.COLOR_BGR2GRAY);
        Imgproc.equalizeHist(grayImg, grayImg);
        faceDetect.detectMultiScale(grayImg, faceRect, 1.1, 2, 0 | Objdetect.CASCADE_SCALE_IMAGE, minSize, maxSize);
    }

    public static void spaceAlien(Mat src, Mat des) {
        Mat blur;
        src.copyTo(des);
        for (Rect roi : faceRect.toArray()) {
            blur = new Mat();
            Imgproc.GaussianBlur(new Mat(src, roi), blur, new Size(35, 35), 30);
            blur.copyTo(des.submat(roi));
        }
    }

    public static void noseTwirl(Mat src, Mat des) {
        Mat nosetwirl;
        src.copyTo(des);
        for (Rect roi : faceRect.toArray()) {
            nosetwirl = new Mat();
            Imgproc.warpAffine(new Mat(src, roi), nosetwirl, Imgproc.getRotationMatrix2D(new Point(roi.width / 2.0, roi.height / 2.0), 45.0, 1.0), roi.size());
            nosetwirl.copyTo(des.submat(roi));
        }
    }
}
