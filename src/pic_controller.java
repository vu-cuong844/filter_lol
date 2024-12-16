import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class pic_controller extends main {
    @FXML
    private ImageView imageView;


    private List<File> imageFiles;
    private final String DIRPATH = "lib\\library\\pic";
    private int index;

    public void initialize(){
        getImageFiles();
        index = 0;
        Image image = new Image(imageFiles.get(index).toURI().toString());
        imageView.setImage(image);
    }

    public void clickOnNext(MouseEvent event) throws IOException {
        // Thực hiện hành động khi nút "Next" được nhấn
        getImageFiles();
        if (index  < imageFiles.size() - 1 ) {
            index = index + 1;
            Image image = new Image(imageFiles.get(index).toURI().toString());
            imageView.setImage(image);
        }
        

    }

    public void clickOnBack(MouseEvent event) throws IOException {
        // Thực hiện hành động khi nút "Back" được nhấn
        getImageFiles();
        if (index > 0) {
            index = index - 1;
            Image image = new Image(imageFiles.get(index).toURI().toString());
            imageView.setImage(image);
        }
    }

    public void clickOnCamera(ActionEvent event) throws IOException{
        // index = 0;
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        if (mainScene == null) mainScene = new Scene(FXMLLoader.load(getClass().getResource("fxml/camera.fxml")));
        stage.setScene(mainScene);
        stage.show();
    }

    public void deleteImg(ActionEvent event) throws Exception{
        if (imageFiles == null || imageFiles.isEmpty()) return;

        File file = new File(imageFiles.get(index).getAbsolutePath());
        

        if(file.delete()){
            imageFiles.remove(index);
            if(imageFiles.isEmpty()) imageView.setImage(null);
            else if (index == imageFiles.size()){
                index = imageFiles.size() - 1;
                Image image = new Image(imageFiles.get(index).toURI().toString());
                imageView.setImage(image);
            }
            else {
                Image image = new Image(imageFiles.get(index).toURI().toString());
                imageView.setImage(image);
            }
        }
        
        
    }


    private void getImageFiles() {
        File dir = new File(DIRPATH);
        File[] files = dir.listFiles((dir1, name) -> name.toLowerCase().endsWith(".jpg") ||
                name.toLowerCase().endsWith(".png") || name.toLowerCase().endsWith(".jpeg"));

        imageFiles = new ArrayList<>();
        if (files != null) {
            for (File file : files) {
                imageFiles.add(file);
            }
        }
        Collections.reverse(imageFiles);
    }

    public void clickOnChoose(ActionEvent event) throws IOException {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose Picture");
            File selectedFile = fileChooser.showOpenDialog(null);

            if (selectedFile != null) {
                Image image = new Image(selectedFile.toURI().toString());
                imageView.setImage(image);
                imageView.setPreserveRatio(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
