# Les vélos de Nantes - Application de valorisation de données publiques

## Introduction
Le vélo est un mode de transport peu onéreux, bon pour la santé et, surtout, écologique. Dans le contexte environnemental actuel, le développement de l’usage du vélo constitue donc une priorité nationale. Au palmarès du Baromètre des Villes Cyclables 2021, Nantes Métropole, pourtant très fière de ses aménagements cyclables, a été déçue de recevoir la note de C (Plutôt favorable). Nantes a donc décidé (hypothétiquement) de faire appel aux compétences des étudiants en Informatique du BUT de Vannes pour faire un état des lieux de son réseau cyclable et pour identifier les améliorations éventuelles à apporter à celui-ci.

## Données
Pour mener à bien cette étude, Nantes Métropole a mis à disposition du grand public, sur la plateforme data.gouv, les données recueillies heure par heure depuis 2020 de ses compteurs de cyclistes. Elle a également mit à disposition sur leur site internet des données coplémentaires sur les quartiers, et les coordonnées GPS des capteurs

## Cahier des charges de Nantes Métropole
Après une discussion (toujours hypothétique) avec les représentants de Nantes Métropole, nous avons pu lister trois exigences majeures :
- Concevoir une base de données pour stocker les données des capteurs de passage.
- Présenter de façon synthétique à l’aide d’un diaporama les mètres statistiques qui, pour nous, constituent des indicateurs pertinents pour décrire l’usage des aménagements cyclables pendant les deux dernières années.
- Valoriser au mieux les données en présentant ces mètres de façon la plus visuelle et adéquate possible.
- Nantes aimerait également connaître l’évolution de l’usage du vélo sur les deux dernières années.

## Objectifs
L'objectif de ce projet est de créer une application de valorisation de données publiques qui permettra à Nantes Métropole d'avoir une meilleure compréhension de l'usage de ses équipements cyclables et d'identifier les améliorations à apporter à ceux-ci.

# Ce dépot GitHub est l'application finale
## Installation
 - Prerequis : Java 11
 - Créer la base de donnée V2 trouvable ici : [Depot BDD](https://github.com/BatLeDev/S2.01-BDD)

## Lancement
- Compilation:
```bash
javac -d ./class ./src/*/*/*.java ./src/*/*.java ./src/*.java --module-path lib/javafx-sdk-11/lib --add-modules javafx.web
```

- Lancement:
```bash
java --module-path lib/javafx-sdk-11/lib --add-modules javafx.web -cp "./class:./lib/mysql-connector-j-8.0.33.jar" MainApp
```

- Génération de la JavaDoc:
```bash
javadoc -d ./doc --module-path lib/javafx-sdk-11/lib --add-modules javafx.web ./src/*/*/*.java ./src/*/*.java ./src/*.java
```

## Technologies utilisées
- [Java](https://www.java.com/fr/)
- [JavaFX](https://openjfx.io/)
- HTML, CSS, JavaScript

## Auteurs
Ce projet a été réalisé par GUERNY Baptiste, PINTO DA SILVA Gabriel, NOUVION Matéo, PITON Corentin et PIERRE Noé dans le cadre de notre formation en Informatique à l'IUT de Vannes.
