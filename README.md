**Paquets à installer:

	1.Numpy:
		pip install numpy
	2. sklearn.cluster:
		pip install scikit-learn
	3. matplotlib:
		pip install -U matplotlib
	4. OpenCV:
		sudo apt-get install python3-opencv
	5. scikit image:
		python3 -m pip install -U scikit-image
	6. collections: already built-in :) 

## Pour exécuter le projet :
# Pour la partie reconnaissance d'image : depuis la racine du projet
	La commande suivante produit un fichier *colors.json* :
	python3 color-code.py *nom-de_l'image*
# Pour la partie conversion musique : depuis /java-project
	La commande suivante récupère *colors.json* et produit un fichier *conversion.txt* :
	mvn compile  
	mvn exec:java -Dexec.mainClass=chopin.App



