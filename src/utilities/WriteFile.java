package utilities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;


public class WriteFile {
    
    public static void writeCsv(String filename, ArrayList<String> contenu){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filename);
            OutputStreamWriter file = new OutputStreamWriter(fileOutputStream, "UTF-8");
            PrintWriter out = new PrintWriter(file);

            for (String line : contenu) {
                out.println(line);
            }
            out.close();

        } catch (IOException e) {
            System.out.println("Erreur lors de la lecture : " + e.getMessage());
        }
    }

    public static String fileChooser(){
        String ret;

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Sélectionner un fichier");
        ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Fichiers csv (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);


        fileChooser.setInitialFileName("data.csv");

        File currentDir = new File(System.getProperty("user.dir"));
        fileChooser.setInitialDirectory(currentDir);

        ret = fileChooser.showSaveDialog(null).getAbsolutePath();
        System.out.println(ret);
        if (ret.length() < 4 || !ret.substring(ret.length() - 4).equals(".csv")) {
            ret += ".csv";
        }
        return ret;
    }

}
