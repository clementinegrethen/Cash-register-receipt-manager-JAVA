import java.util.ArrayList;
/*
//import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
*/
/**
 * Historique gère un historique chronologique des réels enregistrés.
 * L'historique est non borné (utilisation d'une ArrayList), sauf par
 * rapport à la quantité de mémoire disponible (OutOfMemoryException) !
 *
 * @author	Xavier Crégut
 */
public class Historique {

	private ArrayList<TicketCaisse> historique;	// les valeurs enregistrées

	/** Construire un historique vide.  */
	//@ ensures getNbValeurs() == 0;	// historique vide
	public Historique() {
		this.historique = new ArrayList<TicketCaisse>();
	}

	/** Enregistrer une nouvelle information dans l'historique
	 * @param info l'information à enregistrer dans l'historique
	 */
	//@ ensures getNbValeurs() == \old(getNbValeurs()) + 1;	// entier enregistré
	//@ ensures getValeur(getNbValeurs()) == info;	// n ajouté en fin d'historique
	public void enregistrer(TicketCaisse info) {
		this.historique.add(info);
	}

	/** La i<SUP>è</SUP> valeur de l'historique, 1 correspond à la plus
	 * ancienne, getNbValeurs() à la plus récente (la dernière).
	 *
	 * <b>Attention :</b> Cette convention est différente de celle
	 * traditionnellement adoptée en Java pour les tableaux et vecteurs !
	 * @param i indice de l'opération compris en 1 et getNbValeurs().
	 */
	//@ requires 1 <= i && i <= getNbValeurs();	// indice correct
	public TicketCaisse getValeur(int i) {
		return this.historique.get(i);
	}

	/** Le nombre d'entiers enregistrés dans l'historique
	 * @return le nombre d'entiers dans l'historique
	 */
	//@ ensures getNbValeurs() >= 0;	// nb positif de valeurs
	public /*@ pure @*/ int getNbValeurs() {
		return this.historique.size();
	}
	
	public void supprimer(TicketCaisse info) {
		this.historique.remove(info);
	}

	
	
	
}

