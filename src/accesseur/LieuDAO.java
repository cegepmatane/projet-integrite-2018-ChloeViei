package accesseur;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modele.Lieu;


public class LieuDAO {
	
	private Connection connexion = null;
	
 	public LieuDAO()
	{
		this.connexion = BaseDeDonnees.getInstance().getConnection();
	}
 	
 	public List<Lieu> listerLieuParPays(int idPays)
 	{
 		System.out.println("LieuDAO.listerLieu()");
		List<Lieu> listeLieu =  new ArrayList<Lieu>();			
		Statement requeteListeLieu;
		
		try {
			requeteListeLieu = connexion.createStatement();
			ResultSet curseurListeLieu = requeteListeLieu.executeQuery("SELECT * FROM lieu WHERE pays = " + idPays);
			
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
	
	

}
