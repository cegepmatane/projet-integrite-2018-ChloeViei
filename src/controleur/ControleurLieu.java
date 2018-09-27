package controleur;

import accesseur.LieuDAO;
import accesseur.PaysDAO;
import modele.Lieu;
import modele.Pays;
import vue.NavigateurDesVues;
import vue.VueAjouterLieu;
import vue.VueAjouterPays;
import vue.VueEditerLieu;
import vue.VueEditerPays;
import vue.VueListePays;
import vue.VuePays;

import java.util.ArrayList;
import java.util.List;

public class ControleurLieu {

    private NavigateurDesVues navigateur;
    private VueAjouterLieu vueAjouterLieu = null;
    private VueEditerLieu vueEditerLieu = null;
    private PaysDAO paysDAO = null;
    private LieuDAO lieuDAO = null;
    private VueListePays vueListePays = null;
    private VuePays vuePays = null;
    private VueEditerPays vueEditerPays = null;

    private ControleurLieu()
    {
        System.out.println("Initialisation de controleurLieu");
        this.lieuDAO = new LieuDAO();
        paysDAO = new PaysDAO();
    }

    public void activerVues(NavigateurDesVues navigateur)
    {
        this.navigateur = navigateur;
        this.vueAjouterLieu = navigateur.getVueAjouterLieu();
        this.vuePays = navigateur.getVuePays();
        this.vueListePays = navigateur.getVueListePays();
        this.vueEditerPays = navigateur.getVueEditerPays();
        this.vueEditerLieu = navigateur.getVueEditerLieu();
    }

    // SINGLETON DEBUT
    private static ControleurLieu instance = null;

    public static ControleurLieu getInstance() 
    {
        if(null == instance) instance = new ControleurLieu();
        return instance;
    }
    // SINGLETON FINI
    
    
    public void notifierEnregistrementNouveauLieu() 
    {
    	System.out.println("ControleurLieu.notifierEnregistrementNouveauLieu()");
    	Lieu lieu = this.navigateur.getVueAjouterLieu().demanderLieu();
    	this.lieuDAO.ajouterLieu(lieu);
    	this.vueListePays.afficherListePays(this.paysDAO.listerPays());
    	this.navigateur.naviguerVersVueListePays();
    }
    
    public void notifierEnregistrementLieu() 
    {
    	System.out.println("ControleurLieu.notifierEnregistrementLieu()");
    	Lieu lieu = this.navigateur.getVueEditerLieu().demanderLieu();
    	this.lieuDAO.modifierLieu(lieu);
    	this.vueListePays.afficherListePays(this.paysDAO.listerPays());
    	this.navigateur.naviguerVersVueListePays();
    }
    
    public void notifierSupprimerLieu() 
    {
    	System.out.println("ControleurLieu.notifierSupprimerLieu()");
    	Lieu lieu = this.navigateur.getVueEditerLieu().demanderLieu();
    	this.lieuDAO.supprimerLieu(lieu);
    	this.vueListePays.afficherListePays(this.paysDAO.listerPays());
    	this.navigateur.naviguerVersVueListePays();
    }
    
    public void notifierNaviguerAjouterLieu() 
    {
    	System.out.println("ControleurLieu.notifierNaviguerAjouterLieu()");
    	this.navigateur.naviguerVersVueAjouterLieu();
    }
    
    public void notifierNaviguerEditerLieu(int idLieu)
	{
		System.out.println("ControleurLieu.notifierNaviguerEditerLieu("+ idLieu +")");
		this.vueEditerLieu.afficherLieu(this.lieuDAO.rapporterLieu(idLieu));
		this.vueEditerLieu.afficherListeLieu(this.lieuDAO.listerLieuParPays(idLieu));     
		this.navigateur.naviguerVersVueEditerLieu();
	}

}
