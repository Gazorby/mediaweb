package persistance;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import mediatheque.*;
import persistance.Document.DocumentFactory;
import persistance.Document.Type;

// classe mono-instance  dont l'unique instance n'est connue que de la bibliotheque
// via une auto-d�claration dans son bloc static

public class MediathequeData implements PersistentMediatheque {
// Jean-Fran�ois Brette 01/01/2018
	private static final String JDBC_DRIVER = "org.postgresql.Driver";
	private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
	private static final String USER = "DEVERDUX";
	private static final String PASS = "DEVERDUX";

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
		Utilisateur user = null;
		List<Integer> borrowedDocs = new ArrayList<>();
		String userQuery = "select login, passwd, isbibliothecaire from public.subscriber where login=? and passwd=?;";

		try {
			PreparedStatement preparedStatementUser = connection.prepareStatement(userQuery);
			preparedStatementUser.setString(1, login);
			preparedStatementUser.setString(2, password);
			ResultSet resUser = preparedStatementUser.executeQuery();

			if (resUser.next()) {
				if (! resUser.getBoolean("isbibliothecaire")) {
					String docsQuery = "select doc.id from public.document as doc where doc.subscriber=?;";
					PreparedStatement preparedStatementDoc = connection.prepareStatement(docsQuery);
					preparedStatementDoc.setString(1, login);
					ResultSet resDocs = preparedStatementDoc.executeQuery();

					while (resDocs.next()) {
						borrowedDocs.add(resDocs.getInt("id"));
					}
				}
				user = new User(resUser.getString("login"),
								resUser.getBoolean("isbibliothecaire"),
								borrowedDocs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	// va r�cup�rer le document de num�ro numDocument dans la BD
	// et le renvoie
	// si pas trouv�, renvoie null
	@Override
	public Document getDocument(int numDocument) {
	    Document doc = null;
        String query = "select id, name, type from public.document where id=?";
        try {
			PreparedStatement  preparedStatementUser = connection.prepareStatement(query);
            preparedStatementUser.setInt(1, numDocument);
            ResultSet res = preparedStatementUser.executeQuery();

			if (res.next()) {
				DocumentFactory factory = new DocumentFactory();
                doc = factory.getDocument(res.getString("name"),
										  Type.valueOf(res.getString("type").toUpperCase()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(doc);
        return doc;
	}

	@Override
	public void nouveauDocument(int type, Object... args) {
		// args[0] -> le titre
		// args [1] --> l'auteur
		// etc...
	}

}
