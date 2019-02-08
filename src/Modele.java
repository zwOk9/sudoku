
public class Modele {

	int[][] sudoku;
	int[][] solution;
	
	Modele(){
		sudoku = new int[9][9];
		initSudoku();
	}
	
	void initSudoku(){
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				sudoku[i][j] = 0;
			}
		}
	}
	
	public int[][] getSudoku(){
		return this.sudoku;
	}
	
	public int[][] getSolution(){
		return this.solution;
	}
	
	public void setSudoku(int[][] tab){
		for(int i=0;i<tab.length;i++){
			for(int j=0;j<tab[i].length;j++){
				sudoku[i][j] = tab[i][j];
			}
		}
	}
	
	void affichage(int[][] sudo){
		String ligne;
		System.out.println("|-----------------------------|");
		for(int i=0;i<9;i++){
			ligne = "|";
			for(int j=0;j<9;j++){
				if(sudo[i][j]==0)
					ligne += "   ";
				else
					ligne += " "+sudo[i][j]+" ";
				if(j%3==2)
					ligne += "|";
			}
			System.out.println(ligne);
			if(i%3==2)
				System.out.println("|-----------------------------|");
		}
	}
	
	boolean respectDesRegles(int[][] sudo){
		int compteur;
		for(int chiffre=1;chiffre<10;chiffre++){
			/* vérification des lignes */
			for(int i=0;i<9;i++){
				compteur = 0;
				for(int j=0;j<9;j++){
					if(sudo[i][j]==chiffre)
						compteur++;
				}
				if(compteur>1)
					return false;
			}
			/* vérification des colonnes */
			for(int j=0;j<9;j++){
				compteur = 0;
				for(int i=0;i<9;i++){
					if(sudo[i][j]==chiffre)
						compteur++;
				}
				if(compteur>1)
					return false;
			}
			
			/* vérification des sous-carrés */
			for(int a=0;a<9;a=a+3){
				for(int b=0;b<9;b=b+3){
					compteur = 0;
					for(int i=a;i<a+3;i++){
						for(int j=b;j<b+3;j++){
							if(sudo[i][j]==chiffre)
								compteur++;
						}
					}
					if(compteur>1)
						return false;
				}
			}
		}
		return true;
	}
	
	int[][] copieSudo(int[][] sudo){
		int[][] copie = new int[9][9];
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				copie[i][j] = sudo[i][j];
			}
		}
		return copie;
	}
	
	int[][] resolution(int[][] sudo){
		int[][] result = null;
		// recherche d'une case non remplie
		int ligne = -1;
		int colonne = -1;
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				if(sudo[i][j]==0){
					ligne = i;
					colonne = j;
				}
			}
		}
		if((ligne!=-1)&&(colonne!=-1)){
			// on teste les différentes valeurs
			int[][] copie = copieSudo(sudo);
			int essai = 0;
			while((result==null)&&(essai<9)){
				essai++;
				copie[ligne][colonne] = essai;
				if(respectDesRegles(copie))
					result = resolution(copie);
			}
		}
		else {
			// le sudoku récupéré en paramètre est déjà rempli : c'est la solution
			result = sudo;
		}
		return result;
	}
	
	public void resoudre(){
		this.solution = resolution(this.sudoku);
	}
	
}
