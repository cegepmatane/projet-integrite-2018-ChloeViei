package accesseur;

import modele.Pays;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaysDAO {

    public List<Pays> simulerListerPays()
    {
        List listePaysTest = new ArrayList<Pays>();
        listePaysTest.add(new Pays("France", "Europe", "67 millions", "Francais", "Paris"));
        listePaysTest.add(new Pays("Japon", "Asie", "35 millions", "Japonnais", "Tokyo"));
        listePaysTest.add(new Pays("Canada", "Amerique", "20 millions", "Anglais/Francais", "Ottawa"));
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

            while (curseurListePays.next())
            {
            	int id = curseurListePays.getInt("id");
                String nom = curseurListePays.getString("nom");
                String continent = curseurListePays.getString("continent");
                String population = curseurListePays.getString("population");
                String langue = curseurListePays.getString("langue");
                String capital = curseurListePays.getString("capital");
                
                System.out.println("Pays : " + nom + " en  " + continent + "\n" + "Peuplé de : " + population + " parle le : " + langue + " et la capital est : " + capital);

                Pays pays = new Pays(nom,continent,population,langue,capital);
                pays.setId(id);
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
			
			String sqlAjouterPays = "INSERT INTO pays(nom, continent, population, langue, capital) VALUES('"+ pays.getNom()+"','"+ pays.getContinent()+"','"+ pays.getPopulation()+"','"+ pays.getLangue()+"','"+ pays.getCapital()+"')";
			System.out.println("SQL : " + sqlAjouterPays);
			requeteAjouterPays.execute(sqlAjouterPays);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void modifierPays(Pays pays) 
	{
		System.out.println("PaysDAO.modifierPays()");
		
		try {
			Statement requeteModifierPays = connection.createStatement();
			String SQL_MODIFIER_PAYS = "UPDATE pays SET nom = '"+ pays.getNom()+"', continent = '"+ pays.getContinent()+"', population = '"+ pays.getPopulation()+"', langue = '"+ pays.getLangue()+"', capital = '"+ pays.getCapital()+"' WHERE id = " + pays.getId();
			System.out.println("SQL : " + SQL_MODIFIER_PAYS);
			requeteModifierPays.execute(SQL_MODIFIER_PAYS);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public Pays rapporterPays(int idPays)
	{
		Statement requetePays;
 		try {
 			requetePays = connection.createStatement();
 			
 			String SQL_RAPPORTER_PAYS = "SELECT * FROM pays WHERE id = " + idPays;
 			System.out.println(SQL_RAPPORTER_PAYS);
 			ResultSet curseurPays = requetePays.executeQuery(SQL_RAPPORTER_PAYS);
 			
 			curseurPays.next();
 			int id = curseurPays.getInt("id");
 			String nom = curseurPays.getString("nom");
 			String continent = curseurPays.getString("continent");
 			String population = curseurPays.getString("population");
 			String langue = curseurPays.getString("langue");
			String capital = curseurPays.getString("capital");
			
 			System.out.println("Pays " + nom + " de " + continent + " : " + population + " parle " + langue + " " + capital);
 			
 			Pays pays = new Pays(nom, continent, population, langue, capital);
			pays.setId(id);
 			return pays;
 			
 		} catch (SQLException e) {
 			e.printStackTrace();
 		}
 		return null;
 	}
	
	
}
