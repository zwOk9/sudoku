import java.awt.event.*;
import javax.swing.*;

public class EcouteurSaisie extends FocusAdapter{
	
	Vue maVue;
	
	EcouteurSaisie(Vue v){
		maVue = v;
	}
	
	public void focusGained(FocusEvent e){
		// ?????
	}
	
	public void focusLost(FocusEvent e){
		int saisie = -1;
		JTextField champ = (JTextField)e.getSource();
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				if(champ==maVue.tabChamps[i][j]){
					try {
						saisie = Integer.parseInt(champ.getText());
					}
					catch(NumberFormatException ex){
						//System.out.println("Problème de formet de nombre");
					}
					if((saisie<1)||(saisie>9))
						champ.setText("");
				}
			}
		}
	}
	
	
	
	
}
