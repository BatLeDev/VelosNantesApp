package utilities;

// Java imports
import java.util.ArrayList;

// JavaFX imports
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

/**
 * This class is used to create a line chart.
 */
public class DataChart {

    private LineChart<String, Number> lineChart;

    /**
     * Constructor
     * 
     * @param data the data to display
     * @param listeNomAbscisses the labels of the data
     * @throws IllegalArgumentException if the two lists don't have the same size
     */
    public DataChart(ArrayList<Double> data, ArrayList<String> listeNomAbscisses) {
        if ((data == null) || (listeNomAbscisses == null) || (data.size() != listeNomAbscisses.size())) {
            throw new IllegalArgumentException("Les deux listes doivent avoir la même taille");
        }
        
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Releves");

        final NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Passages");

        lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Données avec étiquettes personnalisées");
        
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        for (int i = 0; i < data.size(); i++) {
            String label = listeNomAbscisses.get(i);
            series.getData().add(new XYChart.Data<>(label, data.get(i)));
        }
        
        lineChart.getData().add(series);
        lineChart.setCreateSymbols(false);
    }

    /**
     * This method returns the line chart.
     * 
     * @return the line chart
     */
    public LineChart<String, Number> getLineChart() {
        return this.lineChart;
    }

}