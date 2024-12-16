import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;


public class CameraManager {
    private static VideoCapture Camera;

    static{
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Camera = new VideoCapture(0);
    }

    public VideoCapture getCamera() {
        return Camera;
    }

    public static void releaseCamera(){
        if(Camera != null) Camera.release();
    }

    public static void pauseCamera(boolean isPause){
        if(isPause){
            Camera.grab();
        }else{
            Camera.retrieve(new Mat());
        }
    }
    
}
