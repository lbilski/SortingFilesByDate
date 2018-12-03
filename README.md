## SortingFilesByDate
###General info

Application was created to help in my current job. Our team was wasting a lot of time sorting picuters. 
This program simplifies the sorting process by cataloging photos on the date they were taken.

###Technologies

* Java 8
* JavaFX

![main window](https://drive.google.com/uc?export=view&id=1qHYhZ2R5K26EyQH1d1FhoV9rrQ0o82Ow)

###How to use

1. Choose the path of the directory to sort
2. Select the destination folder

![main window](https://drive.google.com/uc?export=view&id=1VcoCCQuyUa5m1GnSiaA_ZTXXinlLytnT)

3. Application create directory structure like:

 * 2018
   * Styczeń / representing month
      * 01 / representing day of month
      * 02
   * Luty etc.
      * 05
      * 20
   * Brak daty / files without date of capture
   
4. New files will be reneme:
   [Date of capture]_[Hour:Minutes]_[Number of photo taken in this time].[extension]



