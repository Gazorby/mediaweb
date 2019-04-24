package persistance;

import java.sql.*;
import java.util.List;

import mediatheque.*;

// classe mono-instance  dont l'unique instance n'est connue que de la bibliotheque
// via une auto-d�claration dans son bloc static

public class MediathequeData implements PersistentMediatheque {
// Jean-Fran�ois Brette 01/01/2018
	private static final String JDBC_DRIVER = "org.postgresql.Driver";
	private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
	private static final String USER = "postgres";
	private static final String PASS = "";

	private Connection connection = null;


	static {
		try {
			System.out.println("static block");
			Mediatheque.getInstance().setData(new MediathequeData());
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	private MediathequeData() throws ClassNotFoundException, SQLException {
		Class.forName(JDBC_DRIVER);
		connection = DriverManager.getConnection(DB_URL, USER, PASS);
	}

	// renvoie la liste de tous les documents de la biblioth�que
	@Override
	public List<Document> tousLesDocuments() {
		return null;
	}

	// va r�cup�rer le User dans la BD et le renvoie
	// si pas trouv�, renvoie null
	@Override
	public Utilisateur getUser(String login, String password) {
		String statement = "select login, isbibliothecaire from "user" where login=? and passwd=?";
		Utilisateur user = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(statement);
			preparedStatement.setString(1, login);
			preparedStatement.setString(2, password);
			ResultSet res = preparedStatement.executeQuery();
			while (res.next()) {
				user = new User(res.getBoolean("isbibliothecaire"));
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		return user;
	}

	// va r�cup�rer le document de num�ro numDocument dans la BD
	// et le renvoie
	// si pas trouv�, renvoie null
	@Override
	public Document getDocument(int numDocument) {
		return null;
	}

	@Override
	public void nouveauDocument(int type, Object... args) {
		// args[0] -> le titre
		// args [1] --> l'auteur
		// etc...
	}

}
