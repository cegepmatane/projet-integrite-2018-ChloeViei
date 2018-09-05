package accesseur;

import modele.Pays;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaysDAO {

    public List<Pays> simulerListerPays(){

        List listePaysTest = new ArrayList<Pays>();
        listePaysTest.add(new Pays("France", "Europe", "67 millions", "Francais", "Paris"));
        listePaysTest.add(new Pays("Japon", "Asie", "35 000 000", "Japonnais", "Tokyo"));
        listePaysTest.add(new Pays("Canada", "Amerique", "20 kg", "Anglais/Francais", "Ottawa"));
        listePaysTest.add(new Pays("Grece", "Europe", "10 millions", "Grec", "Athene"));

        return listePaysTest;
    }

    
	public List<Pays> listerPays(){

        return this.simulerListerPays();
    }
}
