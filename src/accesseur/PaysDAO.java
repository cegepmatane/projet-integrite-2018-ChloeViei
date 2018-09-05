package accesseur;

import modele.Pays;

import java.util.ArrayList;
import java.util.List;

public class PaysDAO {

    public List<Pays> simulerListerPays(){

        List listePaysTest = new ArrayList<Pays>();
        listePaysTest.add(new Pays("France", "Europe", "67 millions", "Français"));
        listePaysTest.add(new Pays("Japon", "Asie", "35 000 000", "Japonnais"));
        listePaysTest.add(new Pays("Canada", "Amérique", "20 kg", "Anglais/Français"));
        listePaysTest.add(new Pays("Grèce", "Europe", "10 millions", "Grec"));

        return listePaysTest;
    }

    public List<Pays> listerPays(){

        return this.simulerListerPays();
    }
}
