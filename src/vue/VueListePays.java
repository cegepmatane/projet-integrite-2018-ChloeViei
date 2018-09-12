package vue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
    private Button actionNaviguerAjouterPays;

    public VueListePays() 
    {
        super(new GridPane(), 400,400);
        grillePays = (GridPane) this.getRoot();
        this.actionNaviguerAjouterPays = new Button("Ajouter un pays");
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
        
        this.actionNaviguerAjouterPays.setOnAction(new EventHandler<ActionEvent>()
        {
        	@Override
			public void handle(ActionEvent arg0) {
				controleur.notifierNaviguerAjouterPays();
			}	
        });
        this.grillePays.add(this.actionNaviguerAjouterPays, 1, ++numero);
    }
    
    public void setControleur(ControleurPays controleur) {
    	this.controleur = controleur;
    }
}
