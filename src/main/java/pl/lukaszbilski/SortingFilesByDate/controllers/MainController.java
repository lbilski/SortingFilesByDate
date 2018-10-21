package pl.lukaszbilski.SortingFilesByDate.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import pl.lukaszbilski.SortingFilesByDate.models.Utils;

import java.io.File;

public class MainController {
    @FXML
    Button sortButton;

    @FXML
    Label infoLabel;

    private Utils utils = new Utils();

    public void sorting(){
        File[] listOfFiles = utils.getSourceFile().listFiles();
        infoLabel.setText("Działam .... :-)");
        if ((listOfFiles != null ? listOfFiles.length : 0) != 0) {
            for(File file:listOfFiles){
                utils.moveFile(utils.returnMetada(file), file);
            }
            infoLabel.setText("Mission completed :))");
        }else{
            infoLabel.setText("Folder \"" + utils.getSourceFile() + "\" jest pusty");
        }
    }
}