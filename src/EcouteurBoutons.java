import java.awt.event.*;
import javax.swing.*;

public class EcouteurBoutons implements ActionListener{
	
	Vue maVue;
	Modele monSudo;
	
	EcouteurBoutons(Vue v,Modele m){
		maVue = v;
		monSudo = m;
	}
	
	public void actionPerformed(ActionEvent e){
		JButton cible = (JButton)e.getSource();
		if(cible.getActionCommand().equals("Nouvelle grille")){
			monSudo.initSudoku();
			monSudo.affichage(monSudo.getSudoku());
			maVue.affichage();
			maVue.setEdition(true);
		}
		else {
			int[][] grille = new int[9][9];
			for(int i=0;i<9;i++){
				for(int j=0;j<9;j++){
					if(maVue.tabChamps[i][j].getText().equals(""))
						grille[i][j] = 0;
					else
						grille[i][j] = Integer.parseInt(maVue.tabChamps[i][j].getText());
				}
			}
			monSudo.setSudoku(grille);
			monSudo.resoudre();
			monSudo.affichage(monSudo.getSolution());
			maVue.affichageSolution();
			maVue.setEdition(false);
		}
	}
	
	
}
