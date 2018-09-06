package vue;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import modele.Pays;

import java.util.List;

import controleur.ControleurPays;

public class VueListePays extends Scene {

    protected GridPane grillePays;
    private ControleurPays controleur = null;

    public VueListePays() {
        super(new Pane(), 400,400);
        Pane panneau = (Pane) this.getRoot();
        grillePays = new GridPane();

        panneau.getChildren().add(grillePays);
    }

    public void afficherListePays(List<Pays> grillePays)
    {
        int numero = 0;
        this.grillePays.add(new Label("Nom"), 0, numero);
        this.grillePays.add(new Label("Capital"), 1, numero);
        this.grillePays.add(new Label("Langue"), 2, numero);

        for(Pays pays : grillePays)
        {
            numero++;
            this.grillePays.add(new Label(pays.getNom()), 0, numero);
            this.grillePays.add(new Label(pays.getCapital()), 1, numero);
            this.grillePays.add(new Label(pays.getLangue()), 2, numero);
            this.grillePays.add(new Button("Editer"), 3, numero);
        }
    }
    
    public void setControleur(ControleurPays controleur) {
    	this.controleur = controleur;
    }
}
