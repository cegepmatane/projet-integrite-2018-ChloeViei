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
		
		String BASEDEDONNEES_DRIVER = "org.postgresql.Driver";
		String BASEDEDONNEES_URL = "jdbc:postgresql://localhost:5432/lieuDecouvrir";
		String BASEDEDONNEES_USAGER = "postgres";
		String BASEDEDONNEES_MOTDEPASSE = "root";


        try {
            Class.forName(BASEDEDONNEES_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
			Connection connection = DriverManager.getConnection(BASEDEDONNEES_URL, BASEDEDONNEES_USAGER, BASEDEDONNEES_MOTDEPASSE);

            Statement requeteListePays = connection.createStatement();
            ResultSet curseurListePays = requeteListePays.executeQuery("SELECT * FROM pays");

            while (curseurListePays.next()){

                String nom = curseurListePays.getString("nom");
                String continent = curseurListePays.getString("continent");
                String population = curseurListePays.getString("population");
                String langue = curseurListePays.getString("langue");
                String capitale = curseurListePays.getString("capital");

                System.out.println("Pays : " + nom );

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return this.simulerListerPays();
    }
}
