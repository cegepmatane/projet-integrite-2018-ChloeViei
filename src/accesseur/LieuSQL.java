package accesseur;

public interface LieuSQL {

	public static final String SQL_LISTER_LIEU_PAR_PAYS = "SELECT * FROM  lieu WHERE pays = ?";
	public static final String SQL_AJOUTER_LIEU = "INSERT INTO lieu(nom, continent, population, langue, capital) VALUES(?,?,?,?,?)";
}
