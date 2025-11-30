package biblio.ihmswing;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Vector;
import javax.swing.*;

import biblio.Livre;
import java.util.List;

public class Tableau extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String[] entetes = {"id", "Titre", "Année de publication", "Auteur"};
	private Vector<Object[]> donnees;
	
	private MonModel monmodel;
	
	private JPanel jContentPane, jContentHeader, jContentResult;
	private JTextField jTFin;
	private JLabel jLin;
	private JButton jBafficher;

	public Tableau() {
		this.donnees = new Vector<Object[]>();
		donnees.add(new Object[4]);
		/** CHARGEUR **/
		monmodel = new MonModel(donnees, entetes);
		initialize_empty();
		monmodel.addTableModelListener(new MonJTListener());
	}
	
	private void initialize_empty() {
		jContentPane = this;
		jContentPane.setLayout(new BorderLayout());
		
		jContentHeader = new JPanel();
		jContentHeader.setLayout(new FlowLayout());
		
		jLin = new JLabel();
		jLin.setText("");
		jContentHeader.add(jLin);
		
		jTFin = new JTextField();
		jTFin.setPreferredSize(new java.awt.Dimension(100,20));
		jContentHeader.add(jTFin);
						
		jBafficher = new JButton("Afficher");
		
		jContentHeader.add(jBafficher);
		jContentHeader.setPreferredSize(new java.awt.Dimension(200,140));
		jContentPane.add(jContentHeader, BorderLayout.NORTH);
		
		jContentResult = new JPanel();
		jContentResult.setLayout(new BorderLayout());
		
		JTable grille = new JTable(monmodel);
		jContentResult.add(grille.getTableHeader(), BorderLayout.NORTH);
		jContentResult.add(grille, BorderLayout.CENTER);	
	
		jContentPane.add(jContentResult, BorderLayout.CENTER);
		jContentPane.setVisible(false);
	}
	
	public void afficherGetById() {
		jLin.setText("Entrer l'id du livre recherché : ");
		jTFin.setText("");
		
		removeListeners();
		jBafficher.addActionListener((ActionEvent e) -> {jBgetLivreById();});
		
		monmodel.clearData();
		jContentPane.setVisible(true);
	}
	
	public void afficherGetByAuteurId() {
		jLin.setText("Entrer l'id de l'auteur recherché : ");
		jTFin.setText("");
		
		removeListeners();
		jBafficher.addActionListener((ActionEvent e) -> {jBgetLivreByAuteurId();});
		
		monmodel.clearData();
		jContentPane.setVisible(true);
	}
	
	public void afficherGetByAuteurNom() {
		jLin.setText("Entrer le nom de l'auteur recherché : ");
		jTFin.setText("");
		
		removeListeners();
		jBafficher.addActionListener((ActionEvent e) -> {jBgetLivreByAuteurNom();});
		
		monmodel.clearData();
		jContentPane.setVisible(true);
	}
	
	private void removeListeners() {
		ActionListener[] listeners = jBafficher.getActionListeners();
		for (ActionListener listener : listeners) {
		    jBafficher.removeActionListener(listener);
		}
	}
	
	void jBgetLivreById() {
		try {
			Livre l = monmodel.getLivreById(Integer.parseInt(jTFin.getText()));
			Object[] ligne = {l.getId(),
					l.getTitre(),
					l.getAnneePublication(),
					l.getAuteur().getNom() + " " + l.getAuteur().getPrenom()};
			monmodel.addLigne(ligne);
			monmodel.fireTableDataChanged();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	void jBgetLivreByAuteurId() {
		try {
			List<Livre> livres = monmodel.getLivreByAuteurId(Integer.parseInt(jTFin.getText()));
			for (Livre livre : livres) {
				Object[] ligne = {livre.getId(),
						livre.getTitre(),
						livre.getAnneePublication(),
						livre.getAuteur().getNom() + " " + livre.getAuteur().getPrenom()
				};
				monmodel.addLigne(ligne);
			}
			monmodel.fireTableDataChanged();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	void jBgetLivreByAuteurNom() {
		try {
			List<Livre> livres = monmodel.getLivreByAuteurNom(jTFin.getText());
			for (Livre livre : livres) {
				Object[] ligne = {livre.getId(),
						livre.getTitre(),
						livre.getAnneePublication(),
						livre.getAuteur().getNom() + " " + livre.getAuteur().getPrenom()
				};
				monmodel.addLigne(ligne);
			}
			monmodel.fireTableDataChanged();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
