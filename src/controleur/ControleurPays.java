package controleur;

import accesseur.PaysDAO;
import modele.Pays;
import vue.NavigateurDesVues;
import vue.VueAjouterPays;
import vue.VueEditerPays;
import vue.VueListePays;
import vue.VuePays;

import java.util.List;

public class ControleurPays {

    private NavigateurDesVues navigateur;
    private VueAjouterPays vueAjouterPays = null;
    private VueListePays vueListePays = null;
    private VuePays vuePays = null;
    private VueEditerPays vueEditerPays = null;
    private PaysDAO paysDAO = null;

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
        this.vueEditerPays = navigateur.getVueEditerPays();
        
        
        /// TEST ///
        Pays pays = new Pays("Vietnam","Asie","127 millions","Vietnamien","Hanoi");
        this.vuePays.afficherPays(pays);
        this.navigateur.naviguerVersVuePays();
        
        /// TEST ///
        List<Pays> listePaysTest = paysDAO.listerPays();
        this.vueListePays.afficherListePays(listePaysTest);
       
        this.navigateur.naviguerVersVueListePays();
		//this.navigateur.naviguerVersVueAjouterPays();
    }

    // SINGLETON DEBUT
    private static ControleurPays instance = null;

    public static ControleurPays getInstance() 
    {
        if(null == instance) instance = new ControleurPays();
        return instance;
    }
    // SINGLETON FINI
    
    
    public void notifierEnregistrementNouveauPays() 
    {
    	System.out.println("ControleurPays.notifierEnregistrementNouveauPays()");
    	Pays pays = this.navigateur.getVueAjouterPays().demanderPays();
    	this.paysDAO.ajouterPays(pays);
    	this.vueListePays.afficherListePays(this.paysDAO.listerPays());
    	this.navigateur.naviguerVersVueListePays();
    }
    
    public void notifierEnregistrementPays() 
    {
    	System.out.println("ControleurPays.notifierEnregistrementPays()");
    	Pays pays = this.navigateur.getVueEditerPays().demanderPays();
    	this.paysDAO.modifierPays(pays);
    	this.vueListePays.afficherListePays(this.paysDAO.listerPays());
    	this.navigateur.naviguerVersVueListePays();
    }
    
    public void notifierNaviguerAjouterPays() 
    {
    	// envoie un message � la console pour tester le bon fonctionnement de tout
    	System.out.println("ControleurPays.notifierNaviguerAjouterPays()");
    	this.navigateur.naviguerVersVueAjouterPays();
    }
    
    public void notifierNaviguerEditerPays(int idPays)
	{
		System.out.println("ControleurPays.notifierEditerPays("+ idPays +")");
		//Pays paysTest = new Pays("Allemagne","Europe","90 miilions","Allemand","Berlin");
		this.vueEditerPays.afficherPays(this.paysDAO.rapporterPays(idPays));
		this.navigateur.naviguerVersVueEditerPays();
		
	}

}
