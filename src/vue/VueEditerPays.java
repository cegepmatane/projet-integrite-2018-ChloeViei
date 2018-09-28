package vue;

import java.util.ArrayList;
import java.util.List;

import controleur.ControleurLieu;
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
    
    private ControleurPays controleurPays = null;
    private ControleurLieu controleurLieu = null;
    protected Button actionEnregistrerPays = null;
    protected GridPane grilleListeLieu = new GridPane();
    protected Button actionAjouterLieu = null;
    protected Button actionSupprimerPays = null;


    public VueEditerPays()  
    {
        super(new VBox(), 700, 500);
        VBox panneau = (VBox) this.getRoot();
        GridPane grillePays = new GridPane();
        
        this.actionSupprimerPays = new Button("Supprimer");
        this.actionSupprimerPays.setOnAction(new EventHandler<ActionEvent>()
        {
        	@Override
			public void handle(ActionEvent arg0) {
        		controleurPays.notifierSupprimerPays();
        	}
        });
        
        
        this.actionAjouterLieu = new Button("Ajouter un lieu");
        this.actionAjouterLieu.setOnAction(new EventHandler<ActionEvent>()
        {
        	@Override
			public void handle(ActionEvent arg0) {
				controleurLieu.notifierNaviguerAjouterLieu();
        	}
        });
        
        
        this.actionEnregistrerPays = new Button("Enregistrer");
        this.actionEnregistrerPays.setOnAction(new EventHandler<ActionEvent>()
        {
        	@Override
			public void handle(ActionEvent arg0) {
        		controleurPays.notifierEnregistrementPays();
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
		

        panneau.getChildren().add(new Label("Editer un Pays"));
        panneau.getChildren().add(grillePays);
        panneau.getChildren().add(this.actionEnregistrerPays);
        panneau.getChildren().add(this.actionSupprimerPays);
        panneau.getChildren().add(grilleListeLieu);
        panneau.getChildren().add(this.actionAjouterLieu);
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
    
    public void afficherListeLieu(List<Lieu> listeLieux)
	{
		int numero = 1;
		this.grilleListeLieu.add(new Label("Liste des lieux :"), 0, 0);
		
		for(Lieu lieu : listeLieux)
		{
			Button actionEditerLieu = new Button("Editer");
			actionEditerLieu.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    controleurLieu.notifierNaviguerEditerLieu(lieu.getId());
                }
            });
			
			Button actionSupprimerLieu = new Button("Supprimer");
	        actionSupprimerLieu.setOnAction(new EventHandler<ActionEvent>()
	        {
	        	@Override
				public void handle(ActionEvent arg0) {
	        		controleurLieu.notifierSupprimerLieu();
	        	}
	        });
            
			this.grilleListeLieu.add(new Label(lieu.getNom() + " : "), 0, numero);
			this.grilleListeLieu.add(new Label(lieu.getType() + "  "), 1, numero);
			this.grilleListeLieu.add(new Label("("+ lieu.getDetail() + ")  "), 2, numero);
			this.grilleListeLieu.add(actionEditerLieu, 3, numero);
			this.grilleListeLieu.add(actionSupprimerLieu, 4, numero);
			
			numero ++;
		}		
	}
    
    public void setControleurPays(ControleurPays controleur) 
    {
    	this.controleurPays = controleur;
    }
    
    public void setControleurLieu(ControleurLieu controleur) 
    {
    	this.controleurLieu = controleur;
    }
}
