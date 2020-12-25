/**
 * Cette classe met en oeuvre l'algorithme par essai/erreur récursif du 
 * déplacement d'un cavalier sur un échiquier. A partir d'une case de départ, 
 * si un chemin pour parcourir toutes les cases une seule fois est trouvé 
 * alors l'échiquier est affiché avec le parcours complet ducavalier.
 * @author L.Damien
 */

class Cavalier {
	
	int tailleEchec = 5; /** La taille de l'échiquier */
	int[][] damier = new int [tailleEchec] [tailleEchec];
	/** L'échiquier qui permet de visualiser les déplacements du cavalier */
	char role;
	
	
	
	/**
	  * Affiche le damier à l'écran. Le coin supérieur gauche correspond 
	  * aux coordonnées (0, 0). L'affichage consistera simplement à
	  * imprimer à l'écran tailleEchec lignes de tailleEchec entiers, 
	  * chaque entier étant le numéro du déplacement. Chaque mouvement du
	  * cavalier sur le damier incrémente de un le numéro du déplacement 
	  * qui commence à la valeur 1 jusqu'à tailleEchec X tailleEchec
	  * (lorsque le cavalier a terminé son parcours).
	  */
	
	void afficherDamier() {
		if (damier != null) {
			for ( int i = 0; i < tailleEchec; i++) {
				int j = 0;
				for ( j = 0; j < tailleEchec; j++) {
					System.out.print(damier[i][j] + "      ");
				}
				System.out.println("\n\n");
			}
		} else {
			System.err.println("ERREUR afficherDamier:" + " damier null");
		}
	}
	
	/**
	  * Test la méthode afficherDamier
	  */
	  
	void testAfficherDamier(){
		System.out.println("***Test de la méthode AfficherDamier ***");
		System.out.println("***Cas Normaux :");
		afficherDamier();
		System.out.println();
	}
	
	
	/**
	  * A partir des cordonnées (posX, posY), cette méthode remplit le 
	  * tableau "candidats" des 8 déplacements possibles du cavalier. 
	  * Elle ne vérifie pas que les 8 déplacements sont valides. Cette 
	  * vérification est réalisée par la méthode "estCeValide(...)". 
	  * Les déplacements sont mémorisés dans un tableau à 2 entrées de 
	  * taille 8 lignes X 2 colonnes. A chaque ligne correspond 1 position, 
	  * sur ledamier, possible (parmi 8) du cavalier après son déplacement. 
	  * Une position est représentée par le couple de coordonnées (posX,posY).
	  * @param posX la position du cavalier en X (horizontale)
	  * @param posY la position du cavalier en Y (verticale)
	  * @return candidats : le tableau (8 lignes X 2 colonnes) qui 
	  * mémorisent les 8 déplacements possibles du cavalier
	  */
	  
	  
	int[][] donnerSuivants (int posX, int posY) {
		int[][] candidats = new int [8][2];
		
		if ( role == 'c') {
			candidats[0][0]= posX-2;
			candidats[0][1]= posY+1;
			
			candidats[1][0]= posX-1;
			candidats[1][1]= posY+2;
			
			candidats[2][0]= posX+1;
			candidats[2][1]= posY+2;
			
			candidats[3][0]= posX+2;
			candidats[3][1]= posY+1;
			
			candidats[4][0]= posX+2;
			candidats[4][1]= posY-1;
			
			candidats[5][0]= posX+1;
			candidats[5][1]= posY-2;
			
			candidats[6][0]= posX-1;
			candidats[6][1]= posY-2;
			
			candidats[7][0]= posX-2;
			candidats[7][1]= posY-1;
			
		} else {
			
			candidats[0][0]= posX-3;
			candidats[0][1]= posY+1;
			
			candidats[1][0]= posX-1;
			candidats[1][1]= posY+3;
			
			candidats[2][0]= posX+1;
			candidats[2][1]= posY+3;
			
			candidats[3][0]= posX+3;
			candidats[3][1]= posY+1;
			
			candidats[4][0]= posX+3;
			candidats[4][1]= posY-1;
			
			candidats[5][0]= posX+1;
			candidats[5][1]= posY-3;
			
			candidats[6][0]= posX-1;
			candidats[6][1]= posY-3;
			
			candidats[7][0]= posX-3;
			candidats[7][1]= posY-1;
		}
		return candidats;
	}
	
	/**
	  * Test la méthode donnerSuivants
	  */
	  
	void testDonnerSuivants(){
		System.out.println();
		System.out.println("***Test de la méthode donnerSuivants ***");
		System.out.println("***Cas Normaux :");
		int posX = 4;
		int posY = 5;
		int [] [] tab = donnerSuivants(posX, posY);
		for (int i = 0; i < 8; i++) {
			System.out.print(tab[i][0] + "    ");
			System.out.print(tab[i][1]);
			System.out.println("\n");
			}
	}
	
	/**
	  * Méthode qui renvoie vrai si la nouvelle coordonnée 
	  * (ou nouveau déplacement) du cavalier est possible. Le déplacement 
	  * est possible si :la nouvelle coordonnée ne sort pas du damieret 
	  * la nouvelle case (newX, newY) n'a pas encore été visitée
	  * @param newX la nouvelle coordonnée en X
	  * @param newY la nouvelle coordonnée en Y
	  * @return vrai si la nouvelle coordonnée (newX, newY) est valide
	  */
	
	boolean estCeValide (int newX, int newY) {
		boolean ret;
		if(newX < 0 || newX > tailleEchec-1){
			ret = false;
		} else if(newY <0 || newY > tailleEchec-1){
			ret = false;
		} else if(damier[newX][newY] != 0){
			ret = false;
		} else {
			ret = true;
		}
		return ret;
	}
	
	/**
	  * Test la méthode estCeValide
	  */
	  
	void testEstCeValide(){
		System.out.println();
		System.out.println("***Test de la méthode estCeValide ***");
		boolean ret;
		System.out.println("***Cas Normaux :");
		ret = estCeValide(3,3);
		System.out.println("Le résultat est " + ret);
		ret = estCeValide(3,4);
		System.out.println("Le résultat est " + ret);
		System.out.println();
		
		System.out.println("***Cas Erreur :");
		System.out.println("***Test pour newX < 0");
		ret = estCeValide(-1,5);
		System.out.println("Le résultat est " + ret);
		System.out.println();
		System.out.println("***Test pour newX > tailleEchec");
		ret = estCeValide(tailleEchec,2);
		System.out.println("Le résultat est " + ret);
		System.out.println();
		System.out.println("***Test pour newY < 0");
		ret = estCeValide(4,-1);
		System.out.println("Le résultat est " + ret);
		System.out.println();
		System.out.println("***Test pour newY > tailleEchec");
		ret = estCeValide(3,tailleEchec);
		System.out.println("Le résultat est " + ret);
		System.out.println();
		System.out.println("***Test pour case[newX][newY] déja visité");
		damier[3][4] = 1;
		ret = estCeValide(3,4);
		System.out.println("Le résultat est " + ret);
	}
	
	/**
	  * Si le numéro du déplacement reçu en paramètre est &gt; 
	  * (tailleEchec X tailleEchec) alors la solution est trouvée et 
	  * renvoyer vrai (fin de la récursivité). Sinon :A partir des 
	  * coordonnées (posX, posY) du cavalier, calculer les 8 déplacements
	  * suivants possibles par appel de la méthode «donnerSuivants(...) ». 
	  * Ensuite, examiner les 8 déplacements possibles (newXi, newYi) un 
	  * par un. Vérifier la validité de la coordonnée (newX, newY) à 
	  * l’aide de la méthode « estCeValide(...) ».Si la coordonnée est 
	  * valide, inscrire le numéro du déplacement (passé en paramètre) 
	  * dans la case de coordonnées (newX, newY)du tableau « damier » et
	  * appeler récursivement la méthode « essayer (...) » avec cette 
	  * nouvelle case valide (newX, newY) et(numéro de déplacement + 1).
	  * Si ce nouvel appel de « essayer (...) » renvoie vrai, la solution 
	  * finale est trouvée. Sinon, revenir "en arrière" en écrivant zéro 
	  * dans la case aux coordonnées (newX, newY) du damier puis examiner 
	  * la case suivante parmi les 8 déplacements possibles.
	  * @param posX la position du cavalier en X (horizontale)
	  * @param posY a position du cavalier en Y (verticale)
	  * @param numDeplacmnt le numéro du déplacement qui permet de 
	  * mémoriser chaque mouvement du cavalier sur le damier et de savoir
	  * si le jeu est terminé
	  * @return vrai si le chemin complet des déplacements est trouvé
	  */
	
	boolean essayer(int posX, int posY, int numDeplacmnt) {
		boolean ret = false;
		
		if ( numDeplacmnt > tailleEchec * tailleEchec) {
			ret = true;
		} else {
			int[][] tab = new int[8][2];
			int newX;
			int newY;
			int i=0;
			
			tab = donnerSuivants(posX, posY);		
			while(i < 8 && ret == false) {
				newX = tab[i][0];
				newY = tab[i][1];
				boolean check = estCeValide(newX, newY);
				if(check == true) {
					damier[newX][newY] = numDeplacmnt;
					
					
					ret = essayer(newX, newY, numDeplacmnt+1);
					if(ret == false) {
						damier[newX][newY] = 0;
						
					}
				}
				i++;
			}
			
		}
		return ret;
	}
	
	/**
	  * Test la méthode essayer
	  */
	  
	void testEssayer(){
		System.out.println();
		System.out.println("***Test de la méthode essayer ***");
		System.out.println("***Cas Normaux :");
		int numDeplacmnt = 1;
		int posX = 0;
		int posY = 4;
		boolean trouver;
		damier = new int[tailleEchec][tailleEchec];
		damier[posX][posY] = numDeplacmnt;
		numDeplacmnt++;
		trouver = essayer(posX, posY, numDeplacmnt);
		afficherDamier();
		System.out.println();
	}
	
	/**
	  * Lanceur de l'application : crée le damier à la bonne taille et 
	  * positionne le cavalier sur une première case en X et en Y. Le 
	  * numéro du déplacement (variable locale) est initialisé à 1. 
	  * La coordonnée (0, 0) correspond au coin supérieur gauche du 
	  * damier. Ensuite, appeler une première fois "essayer(...)". Suite 
	  * au résultat booléen renvoyé par "essayer(...)", soit le damier 
	  * est affiché car le chemin est trouvé, soit il n'y a pas de 
	  * solution possible.
	  */

	void lanceur(){
		
		int posX;
		int posY;
		int numDeplacmnt = 1;
		boolean trouver;
		
		tailleEchec = SimpleInput.getInt("Donner la taille du tableau");
		damier = new int[tailleEchec][tailleEchec];
		
		role = SimpleInput.getChar("Clow ou cavalier ? \n c pour cavalier, w pour clown");
		while(role != 'w' && role != 'c') {
			System.err.println("Erreur dans la saisie du rôle");
			role = SimpleInput.getChar("Choix de départ clown ou cavalier?");
		}
		
		posX = SimpleInput.getInt("Donner la position X de depart");
		posY = SimpleInput.getInt("Donner la position Y de depart");

		damier[posX][posY] = numDeplacmnt;
		numDeplacmnt++;
		trouver = essayer(posX,posY, numDeplacmnt);
		System.out.println("Lancement du damier : ");
		if(trouver == true ) {
			afficherDamier();
		}else {
			System.out.println("Il n'y a pas de solution");
		}
	}
	
	void principal () {
		testAfficherDamier();
		testDonnerSuivants();
		testEstCeValide();
		testEssayer();
		lanceur();
	}
}
