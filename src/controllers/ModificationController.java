package controllers;

// Projet import
import database.DatabaseAccess;
import views.ModificationView;
import models.*;

// Java import
import java.sql.SQLException;
import java.util.ArrayList;

// JavaFX import
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Toggle;
import javafx.scene.control.cell.PropertyValueFactory;

public class ModificationController  {
    private ModificationView modificationView;
    private ObservableList<IModels> data;

    public ModificationController(ModificationView modificationView) {
        this.modificationView = modificationView;
    }

    public void changerTable(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue){
        String getSelection = this.modificationView.getSelection();
        if (getSelection.equals("Compte")){
            showCompte();
        } else {
            TableView<IModels> table = new TableView<IModels>();
            table.setEditable(true);

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

                this.modificationView.setTextFields(tab);
                for (String column : tab){
                    TableColumn col = new TableColumn<>(column);
                    col.setCellValueFactory(new PropertyValueFactory<>(column));


                    col.setMinWidth(100);
                    table.getColumns().add(col);
                    
                }
                table.setItems(data); 

                this.modificationView.setTable(table);

            } catch (Exception e){
            this.modificationView.setMessage(e.getMessage());
            }
        }

    }

    public void showCompte(){
        ArrayList<String[]> comptes = DatabaseAccess.getAccounts();
        
        this.modificationView.setAccountTable(comptes);
    }

    public void ajouter(ActionEvent event){
        IModels model = null;
        String getSelection = this.modificationView.getSelection();

        try {
            ArrayList<String> tab = this.modificationView.getTextFields();
            if (getSelection.equals("Compteur")){
                String observation = tab.get(3);
                if (observation.equals("")) {
                    observation = null;
                }
                model = new Compteur(Integer.parseInt(tab.get(0)), tab.get(1), tab.get(2), observation, Double.parseDouble(tab.get(4)), Double.parseDouble(tab.get(5)), Integer.parseInt(tab.get(6)));
                DatabaseAccess.addCompteur((Compteur) model);

            } else if (getSelection.equals("Quartier")){
                model = new Quartier(Integer.parseInt(tab.get(0)), tab.get(1), Double.parseDouble(tab.get(2)));
                DatabaseAccess.addQuartier((Quartier) model);

            } else if (getSelection.equals("Jour")){
                model = new Jour(tab.get(0), Integer.parseInt(tab.get(1)), Double.parseDouble(tab.get(2)));
                DatabaseAccess.addJour((Jour) model);

            } else if (getSelection.equals("Releve Journalier")){
                int[] tmp = new int[24];
                for (int i = 0; i < 24; i++){
                    tmp[i] = Integer.parseInt(tab.get(i+2));
                }
                String anomalie = tab.get(27);
                if (anomalie.equals("")){
                    anomalie = null;
                }
                model = new ReleveJournalier(Integer.parseInt(tab.get(0)), tab.get(1), tmp, anomalie );
                DatabaseAccess.addReleveJournalier((ReleveJournalier) model);
            }

            data.add(model);
            this.modificationView.clearTextFields();
            this.modificationView.setMessage("Ajout effectué avec succès");
                
        } catch (SQLException e){
            this.modificationView.setMessage(e.getMessage());
            if (getSelection.equals("Compteur")){
                Compteur.removeCompteur(Integer.parseInt(this.modificationView.getTextFields().get(0)));

            } else if (getSelection.equals("Quartier")){
                Quartier.deleteQuartier(Integer.parseInt(this.modificationView.getTextFields().get(0)));

            } else if (getSelection.equals("Jour")){
                Jour.deleteJour(this.modificationView.getTextFields().get(0));

            } else if (getSelection.equals("Releve Journalier")){
                ReleveJournalier.removeReleveJournalier(this.modificationView.getTextFields().get(0), Integer.parseInt(this.modificationView.getTextFields().get(1)));
            }
        }
        catch (Exception e){
            this.modificationView.setMessage(e.getMessage());
        } 
    }

    public void supprimer(ActionEvent event){
        // Récupérer la ligne sélectionnée dans le TableView<IModels>
        try {
            IModels selected = null;
            String selection = this.modificationView.getSelection();
            if (selection.equals("Compteur")){
                selected = (Compteur) this.modificationView.getTable().getSelectionModel().getSelectedItem();
                if (selected != null && this.modificationView.newAlertSuppression()){
                    Compteur compteur = (Compteur) selected;
                    Compteur.removeCompteur(compteur.getNumero());
                    for (ReleveJournalier releve : ReleveJournalier.getRelevesByCompteur(compteur.getNumero())){
                        data.remove(releve);
                    }
                    
                    data.remove(compteur);
                    DatabaseAccess.deleteCompteur(compteur.getNumero());
                }
            
            } else if (selection.equals("Quartier")){
                selected = (Quartier) this.modificationView.getTable().getSelectionModel().getSelectedItem();
                if (selected != null && this.modificationView.newAlertSuppression()){
                    Quartier quartier = (Quartier) selected;
                    Quartier.deleteQuartier(quartier.getId());
                    for (int c : quartier.getCompteursList()){
                        Compteur compteur = Compteur.getCompteur(c);
                        data.remove(compteur);
                    }
                    data.remove(quartier);
                    DatabaseAccess.deleteQuartier(quartier.getId());
                }

            } else if (selection.equals("Jour")){
                selected = (Jour) this.modificationView.getTable().getSelectionModel().getSelectedItem();
                if (selected != null && this.modificationView.newAlertSuppression()){
                    Jour jour = (Jour) selected;
                    for (ReleveJournalier releve : ReleveJournalier.getRelevesByJour(jour.getDate())){
                        data.remove(releve);
                    }
                    Jour.deleteJour(jour.getDate());
                    data.remove(jour);
                    DatabaseAccess.deleteJour(jour.getDate());
                }

            } else if (selection.equals("Releve Journalier")){
                selected = (ReleveJournalier) this.modificationView.getTable().getSelectionModel().getSelectedItem();
                if (selected != null && this.modificationView.newAlertSuppression()){
                    ReleveJournalier releve = (ReleveJournalier) selected;
                    ReleveJournalier.removeReleveJournalier(releve.getJour(), releve.getCompteur());
                    data.remove(releve);
                    DatabaseAccess.deleteReleveJournalier(releve.getCompteur(), releve.getJour());
                }
            }

            this.modificationView.setMessage("Suppression effectuée avec succès");

        } catch (SQLException e){
            this.modificationView.setMessage(e.getMessage());
        } catch (Exception e){
            this.modificationView.setMessage(e.getMessage());
        }

    }


}
