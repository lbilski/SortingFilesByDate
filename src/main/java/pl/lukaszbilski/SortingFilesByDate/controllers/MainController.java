package pl.lukaszbilski.SortingFilesByDate.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import pl.lukaszbilski.SortingFilesByDate.models.Utils;

import java.io.File;

public class MainController {
    @FXML
    Button sortButton, chooseFolder;

    @FXML
    Label infoLabel;

    @FXML
    TextField pathToFolder;



    private Utils utils = new Utils();

    public void setDestinationPath(){
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(null);

        if(selectedDirectory != null){
            utils.setDestinationFile(selectedDirectory);
            pathToFolder.setText(selectedDirectory.getAbsolutePath());
            sortButton.setDisable(false);
        }
    }

    public void sorting(){
        File[] listOfFiles = utils.getSourceFile().listFiles();

        if ((listOfFiles != null ? listOfFiles.length : 0) != 0) {
            for(File file:listOfFiles){
                utils.moveFile(utils.returnMetada(file), file);
                infoLabel.setText(file.getName());
            }
            infoLabel.setText("Mission completed :))");
        }else{
            infoLabel.setText("Folder \"" + utils.getSourceFile() + "\" jest pusty");
        }


    }
}