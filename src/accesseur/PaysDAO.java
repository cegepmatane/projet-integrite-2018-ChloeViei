package accesseur;

import modele.Pays;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaysDAO {
	
	private Connection connexion = null;
    
	public PaysDAO()
	{
        this.connexion = BaseDeDonnees.getInstance().getConnection();
    }
	
	
	public List<Pays> listerPays(){
		
		List<Pays> listePays = new ArrayList<Pays>();
		Statement requeteListePays;
		
		try {
        	requeteListePays = connexion.createStatement();
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
			Statement requeteAjouterPays = connexion.createStatement();
			
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
		
		try 
		{
			Statement requeteModifierPays = connexion.createStatement();
			String SQL_MODIFIER_PAYS = "UPDATE pays SET nom = '"+ pays.getNom()+"', continent = '"+ pays.getContinent()+"', population = '"+ pays.getPopulation()+"', langue = '"+ pays.getLangue()+"', capital = '"+ pays.getCapital()+"' WHERE id = " + pays.getId();
			System.out.println("SQL : " + SQL_MODIFIER_PAYS);
			requeteModifierPays.execute(SQL_MODIFIER_PAYS);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public Pays rapporterPays(int idPays)
	{
		PreparedStatement requetePays;
 		try 
 		{ 	
 			String SQL_RAPPORTER_PAYS = "SELECT * FROM pays WHERE id = ?";
			requetePays = connexion.prepareStatement(SQL_RAPPORTER_PAYS);
			requetePays.setInt(1, idPays);
 			
 			System.out.println(SQL_RAPPORTER_PAYS);
 			ResultSet curseurPays = requetePays.executeQuery();
 			
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
