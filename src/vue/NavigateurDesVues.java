package vue;

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

	
	public NavigateurDesVues()
	{
		this.vueAjouterPays = new VueAjouterPays();
		this.vueListePays = new VueListePays();
		this.vuePays = new VuePays();
		this.vueEditerPays = new VueEditerPays();
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
		this.vueEditerPays.setControleur(controleur);
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


}