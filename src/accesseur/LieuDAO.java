package accesseur;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modele.Lieu;
import modele.Pays;


public class LieuDAO implements LieuSQL {
	
	private Connection connexion = null;
	
 	public LieuDAO()
	{
		this.connexion = BaseDeDonnees.getInstance().getConnection();
	}
 	
 	public List<Lieu> listerLieuParPays(int idPays)
 	{
 		System.out.println("LieuDAO.listerLieu()");
		List<Lieu> listeLieu =  new ArrayList<Lieu>();			
		PreparedStatement requeteListeLieu;
		
		try {
			requeteListeLieu = connexion.prepareStatement(SQL_LISTER_LIEU_PAR_PAYS);
			requeteListeLieu.setInt(1, idPays);
			ResultSet curseurListeLieu = requeteListeLieu.executeQuery();
			
			while(curseurListeLieu.next())
			{
				int id = curseurListeLieu.getInt("id");
				String nom = curseurListeLieu.getString("nom");
				String type = curseurListeLieu.getString("type");
				String detail = curseurListeLieu.getString("detail");		
				
				System.out.println("Lieu " + nom + " qui est un " + type);
				
				Lieu lieu = new Lieu(nom, type);
				lieu.setDetail(detail);
				lieu.setId(id);
				listeLieu.add(lieu);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		return listeLieu;
 	}
	
	public List<Lieu> simulerListeLieu()
	{
		//// TEST  ////
        List<Lieu> listeLieux = new ArrayList<Lieu>();
        Lieu lieu;
        lieu = new Lieu("Plitvice", "Lacs dans le parc national");
		listeLieux.add(lieu);
		lieu = new Lieu("Nishinomaru Garden", "Jardin style japonnais");
		listeLieux.add(lieu);
		lieu = new Lieu("Bora Bora", "Ile paradisiaque");
		listeLieux.add(lieu); 
		return listeLieux;
	}
	
	public void ajouterLieu(Lieu lieu)
	{
		System.out.println("LieuDAO.ajouterLieu()");
		try {			
			PreparedStatement requeteAjouterLieu = connexion.prepareStatement(SQL_AJOUTER_LIEU);
			requeteAjouterLieu.setString(1, lieu.getNom());
			requeteAjouterLieu.setString(2, lieu.getType());
			requeteAjouterLieu.setString(3, lieu.getDetail());
			requeteAjouterLieu.setInt(4, lieu.getPays());
			
			System.out.println("SQL : " + SQL_AJOUTER_LIEU);
			requeteAjouterLieu.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public void modifierLieu(Lieu lieu) 
	{
		System.out.println("LieuDAO.modifierLieu()");
		
		try 
		{			
			PreparedStatement requeteModifierLieu = connexion.prepareStatement(SQL_MODIFIER_LIEU);
			requeteModifierLieu.setString(1, lieu.getNom());
			requeteModifierLieu.setString(2, lieu.getType());
			requeteModifierLieu.setString(3, lieu.getDetail());
			requeteModifierLieu.setInt(4, lieu.getPays());
			
			System.out.println("SQL : " + SQL_MODIFIER_LIEU);
			requeteModifierLieu.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void supprimerLieu(Lieu lieu) 
	{
		System.out.println("LieuDAO.supprimerLieu()");
		
		try 
		{			
			PreparedStatement requeteSupprimerLieu = connexion.prepareStatement(SQL_SUPPRIMER_LIEU);
			requeteSupprimerLieu.setInt(1, lieu.getId());
			
			System.out.println("SQL : " + SQL_SUPPRIMER_LIEU);
			requeteSupprimerLieu.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public Lieu rapporterLieu(int idLieu)
	{
		PreparedStatement requeteLieu;
 		try 
 		{ 	
			requeteLieu = connexion.prepareStatement(SQL_RAPPORTER_LIEU);
			requeteLieu.setInt(1, idLieu);
 			
 			System.out.println(SQL_RAPPORTER_LIEU);
 			ResultSet curseurLieu = requeteLieu.executeQuery();
 			
 			curseurLieu.next();
 			int id = curseurLieu.getInt("id");
 			String nom = curseurLieu.getString("nom");
 			String type = curseurLieu.getString("type");
 			String detail = curseurLieu.getString("detail");
			
 			System.out.println("Lieu " + nom + " qui est un " + type + " : " + detail );
 			
 			Lieu lieu = new Lieu(nom, type, detail);
			lieu.setId(id);
 			return lieu;
 			
 		} catch (SQLException e) {
 			e.printStackTrace();
 		}
 		return null;
 	}
	

}
