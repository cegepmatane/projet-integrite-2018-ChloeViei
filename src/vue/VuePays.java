package vue;

import controleur.ControleurPays;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import modele.Pays;

public class VuePays extends Scene {

    protected Label valeurNom;
    protected Label valeurContinent;
    protected Label valeurPopulation;
    protected Label valeurLangue;
    protected Label valeurCapital;
    
    private ControleurPays controleur = null;	

    public VuePays() {
        super(new Pane(),400,400);
        Pane panneau = (Pane) this.getRoot();
        GridPane grillePays = new GridPane();

        valeurNom = new Label("");
        grillePays.add(new Label("Nom : "), 0, 0);
        grillePays.add(valeurNom, 1, 0);

        valeurContinent = new Label("");
        grillePays.add(new Label("Continent : "), 0, 1);
        grillePays.add(valeurContinent, 1, 1);

        valeurPopulation = new Label("");
        grillePays.add(new Label("Population : "), 0, 2);
        grillePays.add(valeurPopulation, 1, 2);

        valeurLangue = new Label("");
        grillePays.add(new Label("Langue : "), 0, 3);
        grillePays.add(valeurLangue, 1, 3);

        valeurCapital = new Label("");
        grillePays.add(new Label("Capital : "), 0, 3);
        grillePays.add(valeurCapital, 1, 3);

        panneau.getChildren().add(grillePays);
    }

    public void afficherPays(Pays pays)
    {
        this.valeurNom.setText(pays.getNom());
        this.valeurContinent.setText(pays.getContinent());
        this.valeurPopulation.setText(pays.getPopulation());
        this.valeurLangue.setText(pays.getLangue());
        this.valeurCapital.setText(pays.getCapital());
    }
    
    public void setControleur(ControleurPays controleur) {
		this.controleur = controleur;
	}
}
