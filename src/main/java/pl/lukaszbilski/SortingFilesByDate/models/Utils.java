package pl.lukaszbilski.SortingFilesByDate.models;

import java.io.File;

public class Utils {
    private File sourceFile = new File("Do przesortowania");
    private File destinationFile = new File("Przesortowane");

    public void creatingFolders(){
        sourceFile.mkdir();
        destinationFile.mkdir();
    }


}
