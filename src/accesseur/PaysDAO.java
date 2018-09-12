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
    
    private static String BASEDEDONNEES_DRIVER = "org.postgresql.Driver";
    private static String BASEDEDONNEES_URL = "jdbc:postgresql://localhost:5432/lieuDecouvrir";
    private static String BASEDEDONNEES_USAGER = "postgres";
    private static String BASEDEDONNEES_MOTDEPASSE = "root";
    private Connection connection = null;

    
	public PaysDAO(){

        try {
            Class.forName(BASEDEDONNEES_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
   

        try {
        	
			connection = DriverManager.getConnection(BASEDEDONNEES_URL, BASEDEDONNEES_USAGER, BASEDEDONNEES_MOTDEPASSE);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
	
	public List<Pays> listerPays(){
		
		List<Pays> listePays = new ArrayList<Pays>();
		Statement requeteListePays;
		
		try {
        	requeteListePays = connection.createStatement();
            ResultSet curseurListePays = requeteListePays.executeQuery("SELECT * FROM pays");

            while (curseurListePays.next()){

                String nom = curseurListePays.getString("nom");
                String continent = curseurListePays.getString("continent");
                String population = curseurListePays.getString("population");
                String langue = curseurListePays.getString("langue");
                String capital = curseurListePays.getString("capital");
                
                System.out.println("Pays : " + nom + " en  " + continent + "\n" + "Peuplé de : " + population + " parle le : " + langue + " et la capital est : " + capital);

                Pays pays = new Pays(nom,continent,population,langue,capital);
                listePays.add(pays);

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
		
		return listePays;
		
	}
	
	
	public void ajouterPays(Pays pays)
	{
		System.out.println("PaysDAO.ajouterPays()");
		try {
			Statement requeteAjouterPays = connection.createStatement();
			
			String sqlAjouterPays = "INSERT into pays(nom, continent, population, langue, capital) VALUES('"+ pays.getNom()+"','"+ pays.getContinent()+"','"+ pays.getPopulation()+"','"+ pays.getLangue()+"','"+ pays.getCapital()+"')";
			System.out.println("SQL : " + sqlAjouterPays);
			requeteAjouterPays.execute(sqlAjouterPays);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
