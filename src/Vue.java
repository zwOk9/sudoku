import java.awt.*;
import javax.swing.*;

public class Vue extends JFrame{

	JTextField[][] tabChamps;
	Modele monModele;
	
	Vue(){
		this.setTitle("Un sudoku en MVC");
		this.setSize(600,600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	
		monModele = new Modele();
		
		// la zone des champs de saisie
		JPanel zoneChamps = new JPanel();
		zoneChamps.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
		zoneChamps.setLayout(new GridLayout(3,3));
		JPanel[][] carres = new JPanel[3][3];
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				carres[i][j] = new JPanel();
				carres[i][j].setLayout(new GridLayout(3,3));
				carres[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
				zoneChamps.add(carres[i][j]);
			}
		}
		tabChamps = new JTextField[9][9];
		EcouteurSaisie ecout = new EcouteurSaisie(this);
		Font f = new Font("Serif",Font.PLAIN,36);
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				tabChamps[i][j] = new JTextField();
				tabChamps[i][j].addFocusListener(ecout);
				tabChamps[i][j].setFont(f);
				tabChamps[i][j].setHorizontalAlignment(JTextField.CENTER);
				carres[i/3][j/3].add(tabChamps[i][j]);
			}
		}
		// la zone des boutons
		EcouteurBoutons ecoutB = new EcouteurBoutons(this,monModele);
		JPanel zoneBoutons = new JPanel();
		zoneBoutons.setLayout(new GridLayout(1,2));
		JButton editer = new JButton("Nouvelle grille");
		editer.addActionListener(ecoutB);
		JButton resoudre = new JButton("Résoudre la grille");
		resoudre.addActionListener(ecoutB);
		zoneBoutons.add(editer);
		zoneBoutons.add(resoudre);
		
		// ajout des 2 zones dans la fenêtre
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(zoneChamps,BorderLayout.CENTER);
		this.getContentPane().add(zoneBoutons,BorderLayout.SOUTH);
		
		this.setVisible(true);
	}
	
	public void setEdition(boolean b){
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				tabChamps[i][j].setEditable(b);
			}
		}
	}
	
	public void affichage(){
		int[][] initsudo = monModele.getSudoku();
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				this.tabChamps[i][j].setForeground(Color.BLACK);
				if(initsudo[i][j]!=0){
					this.tabChamps[i][j].setText(initsudo[i][j]+"");
				}
				else {
					this.tabChamps[i][j].setText("");
				}
			}
		}
	}
	
	public void affichageSolution(){
		int[][] solsudo = monModele.getSolution();
		int[][] initsudo = monModele.getSudoku();
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				this.tabChamps[i][j].setForeground(Color.RED);
				this.tabChamps[i][j].setText(solsudo[i][j]+"");
			}
		}
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				if(initsudo[i][j]!=0){
					this.tabChamps[i][j].setForeground(Color.BLACK);
					this.tabChamps[i][j].setText(initsudo[i][j]+"");
				}
			}
		}
	}
	
}
