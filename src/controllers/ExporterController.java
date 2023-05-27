package controllers;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import views.ExporterView;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;

public class ExporterController  {
    private Rooter rooter;
    private ExporterView exporterView;

    public ExporterController(Rooter rooter) {
        this.rooter = rooter;
        this.exporterView = (ExporterView) rooter.getView("Exporter");

        setupEnregistrer();
    }

    public void setupEnregistrer() {
        exporterView.getToggleGroup().selectedToggleProperty().addListener(this::changed);
        exporterView.getEnregistrer().setOnAction(this::test);
    }

    private void test (ActionEvent action) {
        if (exporterView.getSelectedCheckBoxes().size() == 0) {
            System.out.println("Aucune case n'est cochée");
        } else {
            System.out.println("Les cases cochées sont :");
            for (CheckBox cb : exporterView.getSelectedCheckBoxes()) {
                System.out.println(cb.getText());
            }
        }
        System.out.println();
    }

    public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
        RadioButton rb = (RadioButton) exporterView.getToggleGroup().getSelectedToggle();
        System.out.println(rb.getText());
    }

}
