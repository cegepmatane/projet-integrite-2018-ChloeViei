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
        System.out.println("Initialisation du controleur");
    }

    public void activerVues(NavigateurDesVues navigateur)
    {
        this.navigateur = navigateur;
        this.vueAjouterPays = navigateur.getVueAjouterPays();
        this.vuePays = navigateur.getVuePays();
        this.vueListePays = navigateur.getVueListePays();

        //// TEST ////
        Pays pays = new Pays("Dolly", "Grise", "20 kg", "5 juin 2015");
        this.vuePays.afficherPays(pays);


        /// TEST ///
//		List listeMoutonsTest = new ArrayList<Mouton>();
//		listeMoutonsTest.add(new Mouton("Dolly", "Grise", "20 kg", "5 juin 2015"));
//		listeMoutonsTest.add(new Mouton("Molly", "Rousse", "20 kg", "5 mai 2016"));
//		listeMoutonsTest.add(new Mouton("Arthurus", "Noire", "20 kg", "5 mars 2017"));
//		listeMoutonsTest.add(new Mouton("Cheese", "Jaune", "20 kg", "5 septembre 2015"));
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
