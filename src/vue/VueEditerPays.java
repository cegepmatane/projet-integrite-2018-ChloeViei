package vue;

import java.util.ArrayList;
import java.util.List;

import controleur.ControleurPays;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import modele.Lieu;
import modele.Pays;

public class VueEditerPays extends Scene {

    protected TextField valeurNom;
    protected TextField valeurContinent;
    protected TextField valeurPopulation;
    protected TextField valeurLangue;
    protected TextField valeurCapital;
    private int idPays = 0;
    
    private ControleurPays controleur = null;
    protected Button actionEnregistrerPays = null;
    protected GridPane grilleListeLieu = new GridPane();


    public VueEditerPays()  
    {
        super(new VBox(), 400, 400);
        VBox panneau = (VBox) this.getRoot();
        GridPane grillePays = new GridPane();
        this.actionEnregistrerPays = new Button("Enregistrer");
        
        this.actionEnregistrerPays.setOnAction(new EventHandler<ActionEvent>()
        {
        	@Override
			public void handle(ActionEvent arg0) {
				controleur.notifierEnregistrementPays();
        	}
        });

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
        grillePays.add(new Label("Capital : "), 0, 4);
        grillePays.add(valeurCapital, 1, 4);
        
        
        //this.grilleListeLieu.add(new Label("Lieu 1"), 0, 0);
        //this.grilleListeLieu.add(new Label("Lieu 2"), 0, 1);
        //this.grilleListeLieu.add(new Label("Lieu 3"), 0, 2);
        //this.grilleListeLieu.add(new Label("Lieu 4"), 0, 3);  
        
        List<Lieu> listeLieux = new ArrayList<Lieu>();
        Lieu lieu;
        lieu = new Lieu("Plitvice", "Lacs dans le parc national");
		listeLieux.add(lieu);
		lieu = new Lieu("Nishinomaru Garden", "Jardin style japonnais");
		listeLieux.add(lieu);
		lieu = new Lieu("Bora Bora", "Ile paradisiaque");
		listeLieux.add(lieu);
		
 		int numero = 0;
		for(Lieu i : listeLieux)
		{
			this.grilleListeLieu.add(new Label(i.getNom() + ""), 0, numero);
			this.grilleListeLieu.add(new Label(i.getType()), 1, numero);
			this.grilleListeLieu.add(new Button("Éditer"), 2, numero);
			this.grilleListeLieu.add(new Button("Effacer"), 3, numero);
			numero++;
		}
        

        panneau.getChildren().add(new Label("Editer un Pays"));
        panneau.getChildren().add(grillePays);
        panneau.getChildren().add(this.actionEnregistrerPays);
        panneau.getChildren().add(grilleListeLieu);
    }

    public Pays demanderPays()
    {
        Pays pays = new Pays(this.valeurNom.getText(),
                this.valeurContinent.getText(),
                this.valeurPopulation.getText(),
                this.valeurLangue.getText(),
                this.valeurCapital.getText());
        pays.setId(idPays);
        return pays;
    }
    
    public void afficherPays(Pays pays) 
    {
        this.idPays = pays.getId();
    	this.valeurNom.setText(pays.getNom());
    	this.valeurContinent.setText(pays.getContinent());
    	this.valeurPopulation.setText(pays.getPopulation());
    	this.valeurLangue.setText(pays.getLangue());
    	this.valeurCapital.setText(pays.getCapital());
    }
    
    public void setControleur(ControleurPays controleur) 
    {
    	this.controleur = controleur;
    }
}
