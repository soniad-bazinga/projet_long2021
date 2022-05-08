import os
import numpy as np
from sklearn.cluster import KMeans
import matplotlib.pyplot as plt
import cv2
from collections import Counter
# A Counter is a container that tracks how many times equivalent values are added.
from skimage.color import rgb2lab, deltaE_cie76
import json


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
    #new_format = image
    #new_format = new_format.reshape(new_format.shape[0]*new_format.shape[1], 3)
    # utilisation de l'algo de k-partition
    clf = KMeans(n_clusters=nb_couleurs)
    # Compute cluster centers and predict cluster index for each sample.
    labels = clf.fit_predict(new_format)

    compteur = Counter(labels)
    # il faut ordonner pour que les pourcentages correspondent aux bonnes couleurs
    # il y'avais une erreur ici mais c'est résolu maintenant
    compteur = dict(sorted(compteur.items()))
    couleur_principales = clf.cluster_centers_
    # on peut les ordonner  ?
    ordered_colors = [couleur_principales[i] for i in compteur.keys()]
    hex_colors = [RGBtoHEX(ordered_colors[i]) for i in compteur.keys()]
    # dans le cas où on veut renvoyer les valeurs en RGB plutot qu'en hex
    rgb_colors = [ordered_colors[i] for i in compteur.keys()]

    if(tmp):
        # mettre les valeurs de compteur.values dans la donnee de sortie
        # un vecteur ou une liste ??
        # ou on renvoie direct compteur ?
        #
        plt.figure(figsize=(8, 6))
        plt.pie(compteur.values(), labels=hex_colors, colors=hex_colors)
    # construire un dictionnaire pour renvoyer les valeurs avec leurs pourcentages

    return build_dico(compteur, hex_colors)


def build_dico(compteur, hex_colors):
    sum_data = sum(compteur.values())
    dict = {}
    for i in compteur.keys():
        freq = compteur[i] / sum_data  # de 0 à 1
        dict.update({hex_colors[i]: float("%.2f" % freq)})
    return dict


if __name__ == "__main__":

    # reading images using cv2
    image = cv2.imread('tableau1.jpg')
    #print("The type of this input is {}".format(type(image)))
    #print("shape: {}".format(image.shape))
    # exemple d'affichage: (2456, 4856, 3)
    # deux premiere valeurs representent le pixel, et la derniere
    # represente la couleur, 3 pour 3 couleurs RGB
    # OpenCV lit les images sous le format BGR, donc il
    # faut les convertir dans le format RGB
    # sinon l'affichage des couleurs n'est pas correct.
    image = cv2.cvtColor(image, cv2.COLOR_BGR2RGB)
    plt.imshow(image)

    # to resize the image when needed
    colors_string = get_colors(get_image('tableau1.jpg'), 6, True)
    # color identification part
    dico_couleurs = colors_string
    # serializing json
    # write the output data in a json file
    with open("colors.json", "w") as outfile:
        json.dump(dico_couleurs, outfile)
    # pour l'affichage
    plt.show()

    # ce programme doit retourner:
    # les valeurs en RGB et HEX des couleurs identifiees
    # le pourcentage associe a la presence de chaque couleur dans le tableau
    # les données sont stockés dans le dictionnaire dict
