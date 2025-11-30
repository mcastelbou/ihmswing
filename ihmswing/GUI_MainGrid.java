package biblio.ihmswing;

import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.*;

public class GUI_MainGrid extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	private Tableau jContentResult;
	private JPanel jContentPane, jContentBoutons;
	private JButton jBafficher, jBafficher1, jBafficher2;
	
	public GUI_MainGrid() {
		super();
		initialize();
	}
	
	private void initialize() {
		this.setSize(700, 350);
		this.setTitle("Parcourir la bibliothèque");
		
		jContentPane = new JPanel();
		this.setContentPane(jContentPane);
		jContentPane.setLayout(new GridLayout(1,2));
		
		jContentBoutons = new JPanel();
		jContentBoutons.setLayout(new GridLayout(3,1));
						
		jBafficher = new JButton("Rechercher par l'id");
		jBafficher1 = new JButton("Rechercher par l'id de l'auteur");
		jBafficher2 = new JButton("Rechercher par le nom de l'auteur");
		
		jContentBoutons.add(jBafficher);
		jBafficher.addActionListener((ActionEvent e) -> {jBafficheById();});
		jContentBoutons.add(jBafficher1);
		jBafficher1.addActionListener((ActionEvent e) -> {jBafficheByAuteurId();});
		jContentBoutons.add(jBafficher2);
		jBafficher2.addActionListener((ActionEvent e) -> {jBafficheByAuteurNom();});
		
		jContentResult = new Tableau();
		
		jContentPane.add(jContentBoutons);
		jContentPane.add(jContentResult);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	void jBafficheById(){
		try {
			jContentResult.afficherGetById();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	void jBafficheByAuteurId() {
		try {
			jContentResult.afficherGetByAuteurId();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	void jBafficheByAuteurNom() {
		try {
			jContentResult.afficherGetByAuteurNom();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
