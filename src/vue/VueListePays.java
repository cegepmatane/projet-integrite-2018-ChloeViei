package vue;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import modele.Pays;

import java.util.List;

public class VueListePays extends Scene {

    protected GridPane grillePays;

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
        this.grillePays.add(new Label("Langue"), 1, numero);
        for(Pays pays : grillePays)
        {
            numero++;
            this.grillePays.add(new Label(pays.getNom()), 0, numero);
            this.grillePays.add(new Label(pays.getLangue()), 1, numero);
        }
    }
}
