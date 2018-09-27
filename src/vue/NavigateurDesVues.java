package vue;

import controleur.ControleurLieu;
import controleur.ControleurPays;
import javafx.application.Application;
import javafx.stage.Stage;

public class NavigateurDesVues extends Application{

	private Stage stade;

	private VueAjouterPays vueAjouterPays = null;
	private VueEditerPays vueEditerPays = null;
	private VueListePays vueListePays = null;
	private VuePays vuePays = null;
	private ControleurPays controleur = null;
	private VueAjouterLieu vueAjouterLieu = null;
	private VueEditerLieu vueEditerLieu = null;
	private ControleurLieu controleurLieu = null;

	
	public NavigateurDesVues()
	{
		this.vueAjouterPays = new VueAjouterPays();
		this.vueListePays = new VueListePays();
		this.vuePays = new VuePays();
		this.vueEditerPays = new VueEditerPays();
		this.vueAjouterLieu = new VueAjouterLieu();
		this.vueEditerLieu = new VueEditerLieu();
	}


	@Override
	public void start(Stage stade) throws Exception {
		
		this.stade = stade;
		this.stade.setScene(null);
		this.stade.show();

		this.controleur = ControleurPays.getInstance();
		this.controleur.activerVues(this);
		this.vueAjouterPays.setControleur(controleur);
		this.vueListePays.setControleur(controleur);
		this.vuePays.setControleur(controleur);
		this.vueEditerPays.setControleurPays(controleur);
		
		this.controleurLieu = ControleurLieu.getInstance();
		this.controleurLieu.activerVues(this);
		this.vueAjouterLieu.setControleurLieu(controleurLieu);
		this.vueEditerLieu.setControleurLieu(controleurLieu);
	}

	public VueAjouterPays getVueAjouterPays() {
		return vueAjouterPays;
	}

	public VueEditerPays getVueEditerPays(){
		return this.vueEditerPays;
	}

	public VueListePays getVueListePays() {
		return vueListePays;
	}

	public VuePays getVuePays() {
		return vuePays;
	}

	public void naviguerVersVuePays() {
		stade.setScene(this.vuePays);
		stade.show();
	}

	public void naviguerVersVueListePays()
	{
		stade.setScene(this.vueListePays);
		stade.show();
	}

	public void naviguerVersVueAjouterPays()
	{
		stade.setScene(this.vueAjouterPays);
		stade.show();
	}

	public void naviguerVersVueEditerPays()
	{
		stade.setScene(this.vueEditerPays);
		stade.show();
	}
	
	public VueAjouterLieu getVueAjouterLieu() 
	{
		return vueAjouterLieu;
	}
	
	public void naviguerVersVueAjouterLieu()
	{
		stade.setScene(this.vueAjouterLieu);
		stade.show();
	}
	
	public VueEditerLieu getVueEditerLieu()
	{
		return this.vueEditerLieu;
	}
	
	public void naviguerVersVueEditerLieu()
	{
		stade.setScene(this.vueEditerLieu);
		stade.show();
	}


}