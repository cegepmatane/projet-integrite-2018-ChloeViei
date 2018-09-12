package controleur;

import accesseur.PaysDAO;
import modele.Pays;
import vue.NavigateurDesVues;
import vue.VueAjouterPays;
import vue.VueListePays;
import vue.VuePays;

import java.util.List;

public class ControleurPays {

    private NavigateurDesVues navigateur;
    private VueAjouterPays vueAjouterPays = null;
    private VueListePays vueListePays = null;
    private VuePays vuePays = null;
    
    PaysDAO paysDAO = null;

    private ControleurPays()
    {
        System.out.println("Initialisation de controleur");
        this.paysDAO = new PaysDAO();
    }

    public void activerVues(NavigateurDesVues navigateur)
    {
        this.navigateur = navigateur;
        this.vueAjouterPays = navigateur.getVueAjouterPays();
        this.vuePays = navigateur.getVuePays();
        this.vueListePays = navigateur.getVueListePays();

        //// TEST ////
        Pays pays = new Pays("Allemagne","Europe", "90 millions", "allemand", "Berlin");
        this.vuePays.afficherPays(pays);


        /// TEST ///
        List<Pays> listePaysTest = paysDAO.listerPays();
        this.vueListePays.afficherListePays(listePaysTest);


        this.navigateur.naviguerVersVuePays();
        this.navigateur.naviguerVersVueListePays();
		this.navigateur.naviguerVersVueAjouterPays();
    }

    // SINGLETON DEBUT
    private static ControleurPays instance = null;

    public static ControleurPays getInstance() {
        if(null == instance) instance = new ControleurPays();
        return instance;
    }
    // SINGLETON FINI
    
    
    public void notifierEnregistrementPays() {
    	
    	System.out.println("ControleurPays.notifierEnregistrementPays()");
    	Pays pays = this.navigateur.getVueAjouterPays().demanderPays();
    	this.paysDAO.ajouterPays(pays);
    	this.navigateur.naviguerVersVueAjouterPays();
    }

}
