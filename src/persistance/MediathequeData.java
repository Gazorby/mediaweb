package persistance;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import mediatheque.*;
import persistance.Document.ADocument;
import persistance.Document.DocumentFactory;
import persistance.Document.Type;

// classe mono-instance  dont l'unique instance n'est connue que de la bibliotheque
// via une auto-d�claration dans son bloc static

public class MediathequeData implements PersistentMediatheque {
// Jean-Fran�ois Brette 01/01/2018
	private static final String JDBC_DRIVER = "org.postgresql.Driver";
	private static final String DB_URL = "jdbc:postgresql://localhost:5432/mediaweb";
	private static final String USER = "DEVERDUX";
	private static final String PASS = "DEVERDUX";


	private Connection connection;


	static {
		try {
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
	public List<Document> tousLesDocuments() 	{
		List<Document> listDoc = new ArrayList<>();
		DocumentFactory facD = new DocumentFactory();
		List<Integer> borrowedDocs = null;
		String listDocQuery = "select name, type, id, subscriber from public.document";
		String userQuery = "select u.login, u.isbibliothecaire, d.subscriber, d.id from subscriber as u, document as d where d.subscriber=?";

		try {
			PreparedStatement preparedStatementList = connection.prepareStatement(listDocQuery);
			PreparedStatement preparedStatementUser = connection.prepareStatement(userQuery);
			ResultSet resList = preparedStatementList.executeQuery();

			while (resList.next()) {

				Utilisateur user = null;
				preparedStatementUser.setString(1, resList.getString("subscriber"));
				ResultSet resUser = preparedStatementUser.executeQuery();

				if (resUser.next()) {
					borrowedDocs = new ArrayList<>();
					borrowedDocs.add(resUser.getInt("id"));
					user = new User(resUser.getString("login"), false, borrowedDocs);
				}

				while (resUser.next()) {
					borrowedDocs.add(resUser.getInt("id"));
				}

				listDoc.add(facD.getDocument(resList.getString("name"), Type.valueOf(resList.getString("type").toUpperCase()), resList.getInt("id"), user));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listDoc;
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
		Utilisateur user = null;
		List<Integer> borrowedDocs = null;
		String docQuery = "select id, name, type, subscriber from public.document where id=?";
        String userQuery = "select u.login, u.isbibliothecaire, d.subscriber, d.id from subscriber as u, document as d where u.login=?";
        try {
			PreparedStatement preparedStatementDoc = connection.prepareStatement(docQuery);
			PreparedStatement preparedStatementUser = connection.prepareStatement(userQuery);
            preparedStatementDoc.setInt(1, numDocument);
            ResultSet resDoc = preparedStatementDoc.executeQuery();

			if (resDoc.next()) {
				DocumentFactory factory = new DocumentFactory();
				borrowedDocs = new ArrayList<>();
				preparedStatementUser.setString(1, resDoc.getString("subscriber"));
				ResultSet resUser = preparedStatementUser.executeQuery();

				if (resUser.next()) {
					borrowedDocs = new ArrayList<>();
					borrowedDocs.add(resUser.getInt("id"));
					user = new User(resUser.getString("login"), false, borrowedDocs);
				}

				while (resUser.next()) {
					borrowedDocs.add(resUser.getInt("id"));
				}

				doc = factory.getDocument(resDoc.getString("name"),
						Type.valueOf(resDoc.getString("type").toUpperCase()), resDoc.getInt("id"),
						user);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(doc);
        return doc;
	}

	@Override
	public void nouveauDocument(int type, Object... args) {
		String newDocQuery;
        ADocument document = (ADocument) args[0];
        PreparedStatement preparedStatementDoc = null;


            try {

                if (type == -1) {
                    newDocQuery = "insert into public.document(id,name,type,subscriber) values (nextval('document_seq'),?,?,null)";
					preparedStatementDoc = connection.prepareStatement(newDocQuery);
					preparedStatementDoc.setString(1, document.getName());
                    preparedStatementDoc.setString(2, document.getType().toString());
                }
                else {
                    newDocQuery = "update public.document set name=?, type=?, subscriber=?";
					preparedStatementDoc = connection.prepareStatement(newDocQuery);
//                    System.out.println(document.getName() + document.getType().toString());
                    preparedStatementDoc.setString(1, document.getName());
                    preparedStatementDoc.setString(2, document.getType().toString());
                    String[] user = document.getUser().toString().split(", ");
                    preparedStatementDoc.setString(3, user[user.length - 1]);
                }
			}
            catch (SQLException ex) {
                ex.printStackTrace();

        }
        System.out.println(args[0].toString());
        try {
            preparedStatementDoc.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
