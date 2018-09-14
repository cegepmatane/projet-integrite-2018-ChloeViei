package accesseur;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDeDonnees {

	private static String BASEDEDONNEES_DRIVER = "org.postgresql.Driver";
    private static String BASEDEDONNEES_URL = "jdbc:postgresql://localhost:5432/lieuDecouvrir";
    private static String BASEDEDONNEES_USAGER = "postgres";
    private static String BASEDEDONNEES_MOTDEPASSE = "root";
    private Connection connection = null;
	
	private BaseDeDonnees()
	{
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
	
	// SINGLETON - DEBUT
	private static BaseDeDonnees instance = null;
	public static BaseDeDonnees getInstance()
	{
		if(null == instance) instance = new BaseDeDonnees();
		return instance;
	}
	// SINGLETON - FIN
	
 	public Connection getConnection()
	{
		return this.connection;
	}
	
}
