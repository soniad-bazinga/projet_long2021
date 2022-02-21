import os
import numpy as np
from sklearn.cluster import KMeans
import matplotlib.pyplot as plt
import cv2
from collections import Counter
impot os
from skimage.color import rgb2lab, deltaE_cie76



#color identification part

#to have a string vector of colors, we'll have to 
#convert RGB to hex
def RGBtoHEX(color): 
    return "#{:02x}{:02x}{:02x}".format(int(color[0]), int(color[1]), int(color[2]))


#lecture de l'image en RGB
def get_img(chemin):
    image= cv2.imread(chemin)
    image= cv2.cvtColor(image, cv2.COLOR_BGR2RGB)
    return image

#méthode qui prend les couleurs 
def get_colors(image, nb_couleurs, tmp):
    #on réduit la taille de l'image pour réduire le nombre de pixels à traiter
    #et réduire le temps de traitement
    new_format= cv2.resize(image, (600, 400), interpolation= cv2.INTER_AREA)
    new_format= new_format.reshape(new_format.shape[0]*new_format.shape[1], 3)

    #utilisation de l'algo de k-partition
    clf= KMeans(nb_clusters= nb_couleurs)
    labels= clf.fit_predict(new_format) #Compute cluster centers and predict cluster index for each sample.

    compteur= Counter(labels)

    couleur_principales= clf.cluster_centers_
    #on peut les ordonner  ?
    ordered_colors= [couleur_principales[i] for i in compteur.keys()]
    hex_colors = [RGBtoHEX(ordered_colors[i] for i in compteur.keys())]
    rgb_colors = [ordered_colors[i] for i in compteur.keys()]

    #if(tmp): 
        #mettre les valeurs de compteur.values dans la donnée de sortie 
        #un vecteur ou une liste ?? 
        #où on renvoie direct compteur ? 
        #
    return rgb_colors


if __name__ == "__main__": 

 #reading images using cv2
 image= cv2.imread('tableau1.jpg')
 print("shape: {}".format(image.shape))
 #exemple d'affichage: (2456, 4856, 3)
 #deux première valeurs représentent le pixel, et la dernière
 #représente la couleur, 3 pour 3 couleurs RGB
 plt.imshow(image)


#OpenCV lit les images sous le format BGR, donc il 
#faut les convertir dans le format RGB 
#sinon l'affichage des couleurs n'est pas correct. 
image= cv2.cvtColor(image, cv2.COLOR_BGR2RGB)
plt.imshow(image)

#to resize the image when needed 
#new_format = cv2.resize(image, (1200, 600))
#plt.imshow(resized_image)