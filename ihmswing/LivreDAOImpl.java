package biblio.ihmswing;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import biblio.Auteur;
import biblio.Livre;

public class LivreDAOImpl implements LivreDAO{
	
	private static LivreDAOImpl uniqueinstance;
	private static Connection conn;
	
	public LivreDAOImpl(){}

	public static LivreDAOImpl getInstance() {
		if (uniqueinstance == null) {
			uniqueinstance = new LivreDAOImpl();
		}
		return uniqueinstance;
	}
	
	private static Connection getConnexion() {
		if (conn == null) {
			try {
				// Établissement de la connexion
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dblivres","sio","slam");
				System.out.println("Connexion réussie !");
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
		return conn;
	}
	
	@Override
	public Livre findById(int id) {
		getConnexion();
		try {
			String sql = "SELECT * FROM livre JOIN auteur ON livre.idauteur = auteur.id WHERE livre.id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery(); rs.next();
			return new Livre(
					rs.getInt(1),
					rs.getString(2),
					rs.getInt(3),
					new Auteur(rs.getInt(5),rs.getString(6),rs.getString(7)));
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Livre> findLivreByAuteurId(int auteurId) {
		String sql = "SELECT * FROM livre "
				+ "JOIN auteur ON livre.idauteur = auteur.id "
				+ "WHERE auteur.id = ?";
		getConnexion();
		List<Livre> list = new ArrayList<>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, auteurId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new Livre(rs.getInt("id"), 
						rs.getString("titre"), 
						rs.getInt("annee_pub"),
						new Auteur(rs.getInt("auteur.id"),rs.getString("nom"),rs.getString("prenom"))));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<Livre> findLivreByAuteurNom(String nomAuteur) {
		String sql = "SELECT * FROM livre "
				+ "JOIN auteur ON livre.idauteur = auteur.id "
				+ "WHERE auteur.nom LIKE ? OR auteur.prenom LIKE ?";
		getConnexion();
		List<Livre> list = new ArrayList<>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+nomAuteur+"%");
			pstmt.setString(2, "%"+nomAuteur+"%");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new Livre(rs.getInt("id"), 
						rs.getString("titre"), 
						rs.getInt("annee_pub"), 
						new Auteur(rs.getInt("auteur.id"),rs.getString("nom"),rs.getString("prenom"))));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public void updateLivre(Object idLivre, Object value, String column) {
		//Connection conn = getConnexion();
		String sqlU = "UPDATE livre SET "
				+ "? = ? "
				+ "WHERE id = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sqlU);
			pstmt.setString(1, column);
			pstmt.setString(2, value);
			pstmt.setInt(3, idLivre);
			pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
