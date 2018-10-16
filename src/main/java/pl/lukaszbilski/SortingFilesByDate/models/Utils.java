package pl.lukaszbilski.SortingFilesByDate.models;


import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifIFD0Directory;
import com.drew.metadata.exif.ExifSubIFDDirectory;

import java.io.File;
import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utils {
    private File sourceFile = new File("Do przesortowania");
    private File destinationFile = new File("Przesortowane");

    public File getSourceFile() {
        return sourceFile;
    }

    public File getDestinationFile() {
        return destinationFile;
    }

    //Creating new folders with unsorted and sorted files
    public void createFolders(){
        sourceFile.mkdir();
        destinationFile.mkdir();
    }

    public String returnMetada(File input){
        SimpleDateFormat formattedDate = new SimpleDateFormat("yyyy-MM-dd");
        Metadata metadata;
        try {
            metadata = ImageMetadataReader.readMetadata(input);
            Date date = metadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class).getDateOriginal();
            return formattedDate.format(date);

        } catch (ImageProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e){
            e.printStackTrace();
        }
        return "unknown";
    }
}
