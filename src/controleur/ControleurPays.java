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

    private ControleurPays()
    {
        System.out.println("Initialisation de controleur");
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

        PaysDAO moutonDAO = new PaysDAO();
        List<Pays> listePaysTest = moutonDAO.listerPays();
        this.vueListePays.afficherListePays(listePaysTest);


        this.navigateur.naviguerVersVuePays();
        this.navigateur.naviguerVersVueListePays();
//		this.navigateur.naviguerVersVueAjouterPays();
    }

    // SINGLETON DEBUT
    private static ControleurPays instance = null;

    public static ControleurPays getInstance() {
        if(null == instance) instance = new ControleurPays();
        return instance;
    }
    // SINGLETON FINI

}
