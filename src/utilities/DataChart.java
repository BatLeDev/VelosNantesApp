package utilities;

import java.util.ArrayList;

import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class DataChart {

    private LineChart<String, Number> lineChart;

    public DataChart (ArrayList<Double> data, ArrayList<String> listeNomAbscisses) {
        
        if ((data == null) || (listeNomAbscisses == null) || (data.size() != listeNomAbscisses.size()) ) {
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

    public LineChart<String, Number> getLineChart() {
        return this.lineChart;
    }

}