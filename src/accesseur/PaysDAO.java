package accesseur;

import modele.Pays;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaysDAO implements PaysSQL {
	
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
            ResultSet curseurListePays = requeteListePays.executeQuery(SQL_LISTER_PAYS);

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
			PreparedStatement requeteAjouterPays = connexion.prepareStatement(SQL_AJOUTER_PAYS);
			requeteAjouterPays.setString(1, pays.getNom());
			requeteAjouterPays.setString(2, pays.getContinent());
			requeteAjouterPays.setString(3, pays.getPopulation());
			requeteAjouterPays.setString(4, pays.getLangue());
			requeteAjouterPays.setString(5, pays.getCapital());
			
			System.out.println("SQL : " + SQL_AJOUTER_PAYS);
			requeteAjouterPays.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void modifierPays(Pays pays) 
	{
		System.out.println("PaysDAO.modifierPays()");
		
		try 
		{			
			PreparedStatement requeteModifierPays = connexion.prepareStatement(SQL_MODIFIER_PAYS);
			requeteModifierPays.setString(1, pays.getNom());
			requeteModifierPays.setString(2, pays.getContinent());
			requeteModifierPays.setString(3, pays.getPopulation());
			requeteModifierPays.setString(4, pays.getLangue());
			requeteModifierPays.setString(5, pays.getCapital());
			requeteModifierPays.setInt(6, pays.getId());
			
			System.out.println("SQL : " + SQL_MODIFIER_PAYS);
			requeteModifierPays.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void supprimerPays(Pays pays) 
	{
		System.out.println("PaysDAO.supprimerPays()");
		
		try 
		{			
			PreparedStatement requeteSupprimerPays = connexion.prepareStatement(SQL_SUPPRIMER_PAYS);
			requeteSupprimerPays.setInt(1, pays.getId());
			
			PreparedStatement requeteSupprimerLieuParPays = connexion.prepareStatement(SQL_SUPPRIMER_LIEU);
			requeteSupprimerLieuParPays.setInt(1, pays.getId());
			
			System.out.println("SQL : " + SQL_SUPPRIMER_PAYS + " ainsi que : " + SQL_SUPPRIMER_LIEU);
			requeteSupprimerLieuParPays.execute();
			requeteSupprimerPays.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public Pays rapporterPays(int idPays)
	{
		PreparedStatement requetePays;
 		try 
 		{ 	
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
