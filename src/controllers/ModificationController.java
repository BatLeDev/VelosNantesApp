package controllers;

import views.ModificationView;
import models.*;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Toggle;
import javafx.scene.control.cell.PropertyValueFactory;
import utilities.Rooter;


public class ModificationController  {
    private Rooter rooter;
    private ModificationView modificationView;

    public ModificationController(Rooter rooter) {
        this.rooter = rooter;
    }

    public void setView(ModificationView modificationView) {
        this.modificationView = modificationView;
    }


    public void changerTable(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue){
        String getSelection = this.modificationView.getSelection();
        TableView table = new TableView();
        //if (!this.rooter.getPermission().equals("user")) {}
        table.setEditable(true);
        ObservableList<IModels> data = null;

        try {
            String[] tab = new String[0];
            if (getSelection.equals("Compteur")){
                tab = Compteur.getColumns();
                data = FXCollections.observableArrayList(Compteur.getAll());

            } else if (getSelection.equals("Quartier")){
                tab = Quartier.getColumns();
                data = FXCollections.observableArrayList(Quartier.getAll());

            } else if (getSelection.equals("Jour")){
                tab = Jour.getColumns();
                data = FXCollections.observableArrayList(Jour.getAll());

            } else if (getSelection.equals("Releve Journalier")){
                tab = ReleveJournalier.getColumns();  
                data = FXCollections.observableArrayList(ReleveJournalier.getAllReleves());
            }

            for (String column : tab){
                TableColumn col = new TableColumn<>(column);
                col.setCellValueFactory(new PropertyValueFactory<>(column));

                table.getColumns().add(col);
            }
            table.setItems(data); 

            this.modificationView.setTable(table);

        } catch (Exception e){
            this.modificationView.setMessage(e.getMessage());
        }
    }


}
