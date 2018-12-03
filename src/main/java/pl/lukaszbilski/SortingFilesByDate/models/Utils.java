package pl.lukaszbilski.SortingFilesByDate.models;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifSubIFDDirectory;

import java.io.File;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import java.util.TimeZone;

public class Utils {
    private File sourceFile = new File("Do przesortowania");
    private File destinationFile = new File("");
    String [] months = {"Styczen", "Luty", "Marzec", "Kwiecien", "Maj", "Czerwiec", "Lipiec", "Sierpien", "Wrzesien", "Pazdziernik", "Listopad", "Grudzien"};

    public File getSourceFile() {
        return sourceFile;
    }
    public void setSourceFile(File sourceFile){
        this.sourceFile = sourceFile;
    }

    public void setDestinationFile(File destinationFile) {
        this.destinationFile = destinationFile;
    }

    public String returnMetada(File input){
        SimpleDateFormat formattedDate = new SimpleDateFormat("yyyy-MM-dd HH.mm");
        Metadata metadata;
        try {
            metadata = ImageMetadataReader.readMetadata(input);
            Date date = metadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class).getDateOriginal(TimeZone.getTimeZone(ZoneId.systemDefault()));

            return formattedDate.format(date);

        } catch (ImageProcessingException | IOException | NullPointerException e) {
            return "0000-00-00 00.00";
        }
    }

    public void moveFile(String dateOfFile, File file){
        String nameWithoutExtension = file.getName().substring(0, file.getName().lastIndexOf("."));
        String extensionOfFile = file.getName().substring(file.getName().lastIndexOf("."));
        String [] input = dateOfFile.split("[- :]");
        File result;

        if(input[0].equals("0000")){
            result = new File(destinationFile.toString() + "\\Brak daty");

            if(!result.isDirectory()){
                result.mkdir();
            }

            renameAndMove(sourceFile.toString() + "\\" + file.getName(), result.toString() + "\\" + nameWithoutExtension + " (0)" + extensionOfFile);

        }else{
            result = new File(destinationFile.toString() + "\\" + months[Integer.parseInt(input[1])-1] + "\\" + input[2]);

            if(!result.isDirectory()){
                result.mkdirs();
            }

            renameAndMove(sourceFile.toString() + "\\" + file.getName(), result.toString() + "\\" + dateOfFile + " (0)" + extensionOfFile);
        }
    }

    //this method move file and reneme exist files because the camera take a photo with the same name after format card
    private void renameAndMove(String pathFrom, String pathTo){

        StringBuilder outputFileName = new StringBuilder();
        outputFileName.append(pathTo);
        int start = pathTo.lastIndexOf("(");
        int stop = pathTo.lastIndexOf(")");
        int counter = Integer.parseInt(pathTo.substring(start+1, stop));

        File file = new File(pathTo);

        while(file.exists()){
            outputFileName.setLength(0);
            outputFileName.append(pathTo.substring(0,start+1));
            outputFileName.append(++counter);
            outputFileName.append(pathTo.substring(stop));
            file = new File(String.valueOf(outputFileName));
        }
        try {
            Files.move(Paths.get(pathFrom), Paths.get(String.valueOf(outputFileName)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
