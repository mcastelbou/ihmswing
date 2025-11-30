package biblio.ihmswing;

import java.util.List;
import java.util.Vector;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

import biblio.Livre;
import biblio.dao.LivreDAOImpl;

public class MonModel extends AbstractTableModel{
	
	private static final long serialVersionUID = 1L;
	private LivreDAOImpl dao = LivreDAOImpl.getInstance();

	private Vector<Object[]> data;
	private String[] title;
	
	public MonModel(Vector<Object[]> data, String[] title){
		this.data = data;
		this.title = title;
		}
	
	@Override
	public int getRowCount() {
		return this.data.size();
	}

	@Override
	public int getColumnCount() {
		return this.title.length;
	}

	@Override
	public Object getValueAt(int row, int col) {
		if (col == -1) return this.data.get(row)[0];
		return this.data.get(row)[col];
	}
	
	public String getColumnName(int col) {
		return this.title[col];
	}
	
	public boolean isCellEditable(int row, int col){
		return true;
	}
	
	public void setValueAt(Object aValue, int row, int col) {
		Object idLivre = getValueAt(row, 0);
		String column;
		if (col == 1) {
			column = "titre";
		} else if (col == 2) {
			column = "annee_pub";
		} else if (col == 3) {
			column = "idauteur";
		}
		this.data.get(row)[col]=aValue;
		dao.updateLivre(idLivre, aValue, column);
		fireTableCellUpdated(row, col);
	}
	
	public Class<?> getColumnClass(int col){
		//On retourne le type de la cellule à la colonne demandée
		//On se moque de la ligne puisque les types de données sont les mêmes quelle que soit la ligne
		//On choisit donc la première ligne
		return this.data.getFirst()[col].getClass();
	}
	
	void addLigne(Object[] ligne) {
		data.add(ligne);
	}
	
	void clearData() {
		data.clear();
	}
	
	public Livre getLivreById(int param) {
		clearData();
		try {
		return dao.findById(param);
		} catch (Exception e) {
			return null;
		}
	}
	
	public List<Livre> getLivreByAuteurId(int param){
		clearData();
		try {
		return dao.findLivreByAuteurId(param);
		} catch (Exception e) {
		return null;
	}
	}
	
	public List<Livre> getLivreByAuteurNom(String param){
		clearData();
		try {
		return dao.findLivreByAuteurNom(param);
		} catch (Exception e) {
		return null;
	}
	}
}

class MonJTListener implements TableModelListener{
	public void tableChanged(TableModelEvent e) {
		if (e.getType() == TableModelEvent.UPDATE) {
			////final int row = e.getFirstRow();
			////final int column = e.getColumn();
			//On appelle la méthode getValueAt qui retourne la
			//valeur d'une cellule
			////final Object value = ((MonModel)e.getSource()).getValueAt(row, column);
			////System.out.println("Ecouteur alerté : " + value +" ");
			//C'est ici que l'on peut jouer sur la persistence.
			//DataLivresMySQL data = DataLivresMySQL.getInstance();
		}
	}
}
