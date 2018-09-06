package vue;

import controleur.ControleurPays;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import modele.Pays;

public class VueAjouterPays extends Scene {

    protected TextField valeurNom;
    protected TextField valeurContinent;
    protected TextField valeurPopulation;
    protected TextField valeurLangue;
    protected TextField valeurCapital;
    
    private ControleurPays controleur = null;


    public VueAjouterPays()  {
        super(new VBox(), 400, 400);
        VBox panneau = (VBox) this.getRoot();
        GridPane grillePays = new GridPane();

        valeurNom = new TextField();
        grillePays.add(new Label("Nom : "), 0, 0);
        grillePays.add(valeurNom, 1, 0);

        valeurContinent = new TextField("");
        grillePays.add(new Label("Continent : "), 0, 1);
        grillePays.add(valeurContinent, 1, 1);

        valeurPopulation = new TextField("");
        grillePays.add(new Label("Population : "), 0, 2);
        grillePays.add(valeurPopulation, 1, 2);

        valeurLangue = new TextField("");
        grillePays.add(new Label("Langue : "), 0, 3);
        grillePays.add(valeurLangue, 1, 3);

        valeurCapital = new TextField("");
        grillePays.add(new Label("Capital : "), 0, 3);
        grillePays.add(valeurCapital, 1, 3);

        panneau.getChildren().add(new Label("Ajouter un Pays"));
        panneau.getChildren().add(grillePays);
        panneau.getChildren().add(new Button("Enregistrer"));
    }

    public Pays demanderPays()
    {
        Pays pays = new Pays(this.valeurNom.getText(),
                this.valeurContinent.getText(),
                this.valeurPopulation.getText(),
                this.valeurLangue.getText(),
                this.valeurCapital.getText());
        return pays;
    }
    
    public void setControleur(ControleurPays controleur) {
    	
    	this.controleur = controleur;
    }
}
