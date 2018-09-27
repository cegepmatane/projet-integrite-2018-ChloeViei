package accesseur;

public interface LieuSQL {

	public static final String SQL_LISTER_LIEU_PAR_PAYS = "SELECT * FROM  lieu WHERE pays = ?";
	public static final String SQL_AJOUTER_LIEU = "INSERT INTO lieu(nom, continent, population, langue, capital) VALUES(?,?,?,?,?)";
	public static final String SQL_MODIFIER_LIEU = "UPDATE lieu SET nom = ?, type = ?, detail = ?";
	public static final String SQL_SUPPRIMER_LIEU = "DELETE FROM lieu WHERE id = ?";
	public static final String SQL_RAPPORTER_LIEU = "SELECT * FROM lieu WHERE id = ?";
}
