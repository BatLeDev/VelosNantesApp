package utilities;

// Java imports
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.BufferedReader;

// JavaFX imports
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * This class is used to read and write files in different formats.
 */
public class ReadWriteFile {
    
    /**
     * This method reads a csv file and returns its content as an ArrayList of String.
     * 
     * @param filename the name of the file to read
     * @param content the ArrayList of String to fill with the content of the file
     */
    public static void writeCsv(String filename, ArrayList<String> content){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filename);
            OutputStreamWriter file = new OutputStreamWriter(fileOutputStream, "UTF-8");
            PrintWriter out = new PrintWriter(file);

            for (String line : content) {
                out.println(line);
            }
            out.close();

        } catch (IOException e) {
            System.out.println("Erreur lors de la lecture : " + e.getMessage());
        }
    }

    /**
     * This method opens a file chooser and returns the path of the selected file.
     * 
     * @return the path of the selected file
     */
    public static String fileChooser() {
        // Create a window to select a file
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Sélectionner un fichier");
        ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Fichiers csv (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);

        fileChooser.setInitialFileName("data.csv");

        File currentDir = new File(System.getProperty("user.dir"));
        fileChooser.setInitialDirectory(currentDir);

        File file = fileChooser.showSaveDialog(null);
        if (file == null) {
            throw new NullPointerException("Aucun fichier sélectionné");
        }
        String ret = file.getAbsolutePath();

        // Add the extension if it is not present
        if (ret.length() < 4 || !ret.substring(ret.length() - 4).equals(".csv")) {
            throw new IllegalArgumentException("Le fichier doit être au format csv");
        }

        return ret;
    }

    /**
     * Convert a html file to a string
     * 
     * @param filePath Path of the html file
     * @return The html file converted to a string, executed by the web engine
     */
    public static String readHTMLFile(String filePath) {
        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content.toString();
    }

}
