import os
import numpy as np
from sklearn.cluster import KMeans
import matplotlib.pyplot as plt
import cv2
from collections import Counter
# A Counter is a container that tracks how many times equivalent values are added.
from skimage.color import rgb2lab, deltaE_cie76


# color identification part

# to have a string vector of colors, we'll have to
# convert RGB to hex
# returns a string, {:02x} displays the hex value
def RGBtoHEX(color):
    return "#{:02x}{:02x}{:02x}".format(int(color[0]), int(color[1]), int(color[2]))


# lecture de l'image en RGB
def get_image(chemin):
    image = cv2.imread(chemin)
    image = cv2.cvtColor(image, cv2.COLOR_BGR2RGB)
    return image

# methode qui prend les couleurs


def get_colors(image, nb_couleurs, tmp):
    # on reduit la taille de l'image pour reduire le nombre de pixels a traiter
    # et reduire le temps de traitement
    new_format = cv2.resize(image, (600, 400), interpolation=cv2.INTER_AREA)
    new_format = new_format.reshape(new_format.shape[0]*new_format.shape[1], 3)

    # utilisation de l'algo de k-partition
    clf = KMeans(n_clusters=nb_couleurs)
    # Compute cluster centers and predict cluster index for each sample.
    labels = clf.fit_predict(new_format)

    compteur = Counter(labels)

    couleur_principales = clf.cluster_centers_
    # on peut les ordonner  ?
    ordered_colors = [couleur_principales[i] for i in compteur.keys()]
    # print(ordered_colors)
    hex_colors = [RGBtoHEX(ordered_colors[i]) for i in compteur.keys()]
    rgb_colors = [ordered_colors[i] for i in compteur.keys()]
    if(tmp):
        # mettre les valeurs de compteur.values dans la donnee de sortie
        # un vecteur ou une liste ??
        # ou on renvoie direct compteur ?
        #
        plt.figure(figsize=(8, 6))
        print(compteur.values())
        print("compteur: ", compteur)
        plt.pie(compteur.values(), labels=hex_colors, colors=hex_colors)
    # construire un dictionnaire pour renvoyer les valeurs

    return hex_colors


if __name__ == "__main__":

    # reading images using cv2
    image = cv2.imread('djurdjura.jpeg')
    print("The type of this input is {}".format(type(image)))
    print("shape: {}".format(image.shape))
    # exemple d'affichage: (2456, 4856, 3)
    # deux premiere valeurs representent le pixel, et la derniere
    # represente la couleur, 3 pour 3 couleurs RGB
    # OpenCV lit les images sous le format BGR, donc il
    # faut les convertir dans le format RGB
    # sinon l'affichage des couleurs n'est pas correct.
    image = cv2.cvtColor(image, cv2.COLOR_BGR2RGB)
    plt.imshow(image)

    # to resize the image when needed
    colors_string = get_colors(get_image('djurdjura.jpeg'), 8, True)
    #print(colors_string[i] for i in range(len(colors_string)))
    # color identification part
    # RGB to hex conversion
    print(colors_string)
    plt.show()

    # ce programme doit retourner:
    # les valeurs en RGB et HEX des couleurs identifiees
    # le pourcentage associe a la presence de chaque couleur dans le tableau
