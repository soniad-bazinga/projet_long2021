from tensorflow import keras
import os
os.environ['TF_CPP_MIN_LOG_LEVEL'] = '2'
os.environ['CUDA_VISIBLE_DEVICES'] = "0"

# training a model to identify the class of a painting
# pour cela, j'ai créé deux dossiers, l'un pour les données d'entrainements qui sont
# mal rangées et un autre pour les données de validation qui sont les classes bien ordonnées

# ce code prend BEAUCOUP de temps à s'exécuter mais il a l'air de marcher
# attention à bien installer les bons paquets sinon tensorflow il boude


train_ds = keras.utils.image_dataset_from_directory(
    directory='../neural_network/training_data/',
    labels='inferred',
    label_mode='categorical',
    batch_size=32,
    image_size=(256, 256))
validation_ds = keras.utils.image_dataset_from_directory(
    directory='../neural_network/validation_data/',
    labels='inferred',
    label_mode='categorical',
    batch_size=32,
    image_size=(256, 256))

model = keras.applications.Xception(
    weights=None, input_shape=(256, 256, 3), classes=11)
model.compile(optimizer='rmsprop', loss='categorical_crossentropy')
model.fit(train_ds, epochs=11, validation_data=validation_ds)
