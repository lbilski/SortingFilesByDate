package pl.lukaszbilski.SortingFilesByDate.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import pl.lukaszbilski.SortingFilesByDate.models.Utils;

import java.io.File;
import java.util.Calendar;


public class MainController {
    @FXML
    Button sortButton;

    @FXML
    Label infoLabel;

    Utils utils = new Utils();

    public void sorting(){
        File[] listOfFiles = utils.getSourceFile().listFiles();
        int counter = 0;
        
        if (listOfFiles.length != 0) {
            for(File file:listOfFiles){
                System.out.println(file.getName() + utils.returnMetada(file));
                counter++;
            }
        }else{
            infoLabel.setText("Folder \"" + utils.getSourceFile() + "\" jest pusty");
        }
        infoLabel.setText("Liczba plików: " + counter);
    }
}
