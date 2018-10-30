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
    Button sortButton, chooseSourcePath, chooseDestinyPath;

    @FXML
    Label infoLabel;

    @FXML
    TextField sourcePath, destinationPath;



    private Utils utils = new Utils();

    public void setSourcePath(){
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(null);

        if(selectedDirectory != null){
            utils.setSourceFile(selectedDirectory);
            sourcePath.setText(selectedDirectory.getAbsolutePath());
            chooseDestinyPath.setDisable(false);
            infoLabel.setText("");
        }
    }

    public void setDestinationPath(){
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(null);

        if(selectedDirectory != null){
            utils.setDestinationFile(selectedDirectory);
            destinationPath.setText(selectedDirectory.getAbsolutePath());
            sortButton.setDisable(false);
        }
    }

    public void sorting(){
        sort(utils.getSourceFile());

        destinationPath.setText("");
        sourcePath.setText("");
        sortButton.setDisable(true);
        chooseDestinyPath.setDisable(true);
    }

    private void sort(File file){
        File[] listOfFiles = file.listFiles();

        if ((listOfFiles != null ? listOfFiles.length : 0) != 0) {
            for(File result:listOfFiles){
                if (result.isFile()) {
                    utils.moveFile(utils.returnMetada(result), result);
                }else if(result.isDirectory()){
                    utils.setSourceFile(result);
                    sort(new File(result.getAbsolutePath()));
                }
            }
            infoLabel.setText("Mission completed :))");
        }else{
            infoLabel.setText("Folder \"" + utils.getSourceFile() + "\" jest pusty");
        }
    }
}