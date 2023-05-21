Infos conventions internes:

@Overide
On écrit cette annotation au dessus d'une méthode qui est censée surcharger une méthode d'une classe parente.
C'est pas obligatoire mais c'est une bonne pratique pour la compréhension du code.

Une "Stage" est une fenêtre de l'application.  
Une "Scene" est un conteneur de l'application.

Commandes pour compiler et start (a partir du dossier ws):
javac -d ../class --module-path ../lib/javafx-sdk-11/lib --add-modules javafx.controls,javafx.fxml ../src/*.java  
java --module-path ../lib/javafx-sdk-11/lib --add-modules javafx.controls,javafx.fxml -cp ../class/ MainApp