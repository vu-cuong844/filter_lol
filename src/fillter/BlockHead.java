package fillter;

import org.opencv.core.Mat;
import org.opencv.core.Rect;


public class BlockHead extends Fillter_Face {
    @Override
    public void filtering(Mat src, Mat des) {
        // Filter cat mat va tron mat
        src.copyTo(des);

        for (Rect roi : faceDetections.toArray()) {
            int width = roi.width / 3;
            int height = roi.height / 3;
            int x = roi.x;
            int y = roi.y;

            // Kiểm tra biên giới hạn để đảm bảo rằng tọa độ không vượt quá kích thước ma trận
            if (x + 3 * width > des.cols() || y + 3 * height > des.rows()) {
                continue; // Bỏ qua vùng này nếu vượt quá kích thước ma trận
            }

            // Hoán đổi các vùng trong ma trận
            Mat tmp = new Mat();
            // M1 (top-left)
            des.submat(y, y + height, x, x + width).copyTo(tmp);
            // M9 (bottom-right) -> M1
            des.submat(y + 2 * height, y + 3 * height, x + 2 * width, x + 3 * width)
                .copyTo(des.submat(y, y + height, x, x + width));
            // M3 (top-right) -> M9
            des.submat(y, y + height, x + 2 * width, x + 3 * width)
                .copyTo(des.submat(y + 2 * height, y + 3 * height, x + 2 * width, x + 3 * width));
            // M7 (bottom-left) -> M3
            des.submat(y + 2 * height, y + 3 * height, x, x + width)
                .copyTo(des.submat(y, y + height, x + 2 * width, x + 3 * width));
            // M1 (tmp) -> M7
            tmp.copyTo(des.submat(y + 2 * height, y + 3 * height, x, x + width));

            // M2 (top-center)
            des.submat(y, y + height, x + width, x + 2 * width).copyTo(tmp);
            // M8 (bottom-center) -> M2
            des.submat(y + 2 * height, y + 3 * height, x + width, x + 2 * width)
                .copyTo(des.submat(y, y + height, x + width, x + 2 * width));
            // M4 (middle-left) -> M8
            des.submat(y + height, y + 2 * height, x, x + width)
                .copyTo(des.submat(y + 2 * height, y + 3 * height, x + width, x + 2 * width));
            // M6 (middle-right) -> M4
            des.submat(y + height, y + 2 * height, x + 2 * width, x + 3 * width)
                .copyTo(des.submat(y + height, y + 2 * height, x, x + width));
            // M2 (tmp) -> M6
            tmp.copyTo(des.submat(y + height, y + 2 * height, x + 2 * width, x + 3 * width));

            // M5 (center)
            des.submat(y + height, y + 2 * height, x + width, x + 2 * width).copyTo(tmp);
            // M5 (center) doesn't move but needs to be restored in final place
            tmp.copyTo(des.submat(y + height, y + 2 * height, x + width, x + 2 * width));
        }
        
    }
}
