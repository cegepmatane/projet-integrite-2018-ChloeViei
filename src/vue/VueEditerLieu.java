package vue;

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

public class VueEditerLieu extends Scene {

    protected TextField valeurNom;
    protected TextField valeurType;
    protected TextField valeurDetail;
    private int idPays = 0;

    private ControleurLieu controleurLieu = null;
    protected Button actionEnregistrerLieu = null;
    protected GridPane grilleListeLieu = new GridPane();
    protected Button actionAjouterLieu = null;

    public VueEditerLieu()  
    {
        super(new VBox(), 500, 500);
        VBox panneau = (VBox) this.getRoot();
        GridPane grilleLieu = new GridPane();
        
        this.actionEnregistrerLieu = new Button("Enregistrer");
        this.actionEnregistrerLieu.setOnAction(new EventHandler<ActionEvent>()
        {
        	@Override
			public void handle(ActionEvent arg0) {
        		controleurLieu.notifierEnregistrementLieu();
        	}
        });

        valeurNom = new TextField();
        grilleLieu.add(new Label("Nom : "), 0, 0);
        grilleLieu.add(valeurNom, 1, 0);

        valeurType = new TextField("");
        grilleLieu.add(new Label("Type : "), 0, 1);
        grilleLieu.add(valeurType, 1, 1);

        valeurDetail = new TextField("");
        grilleLieu.add(new Label("Detail : "), 0, 2);
        grilleLieu.add(valeurDetail, 1, 2);		

        panneau.getChildren().add(new Label("Editer un Lieu"));
        panneau.getChildren().add(grilleLieu);
        panneau.getChildren().add(this.actionEnregistrerLieu);
    }

    public Lieu demanderLieu()
    {
    	Lieu lieu = new Lieu(this.valeurNom.getText(),
                this.valeurType.getText(),
                this.valeurDetail.getText());
    	lieu.setId(idPays);
        return lieu;
    }
    
    public void afficherLieu(Lieu lieu) 
    {
        this.idPays = lieu.getId();
    	this.valeurNom.setText(lieu.getNom());
    	this.valeurType.setText(lieu.getType());
    	this.valeurDetail.setText(lieu.getDetail());
    }
    
    
    public void setControleurLieu(ControleurLieu controleur) 
    {
    	this.controleurLieu = controleur;
    }
}
