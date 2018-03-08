![Constellation header](https://github.com/Monierv/OCS/blob/master/Documentation/resources/img/constellation_header.jpg)
# Conception des objets

## Plan

1. [Présentation](https://github.com/Monierv/OCS/blob/master/README.md)
2. [Conception du logiciel](https://github.com/Monierv/OCS/blob/master/Documentation/SOFTWARE.md)
3. [Conception des objets](https://github.com/Monierv/OCS/blob/master/Documentation/MATERIAL.md)

## Contraintes

Tout au long de la conception des objets physiques nous garderons en tete les contraintes décrites ci-dessous:  

![Contraintes du matérielles](https://github.com/Monierv/OCS/blob/master/Documentation/resources/img/contraintes.jpg)


### La Terre
#### Expression du besoin
Les besoins se décomposent comme tels:
 * L'utilisateur doit pouvoir bouger la terre dans tous les sens afin de montrer une zone géographique
 * L'orientation de la sphere servira pour d'autre fonctionnalités (controle de volume par example)

#### Contraintes technologiques

Les contraintes technologiques sont les suivantes:  
* Le Raspberry étant un micro ordinateur, il y a un risque de surchauffe du systeme. Il faut donc y insérer des trous de ventilation.
* Le systeme néccéssite des trous et emplacements pour les connexions (cable d'alimentation, cable ethernet, usb).
* Le systeme doit etre intégré à la base du globe, sur la face arrière. L'emplacement est visible sur le modele solidworks du socle.
* La répartition de la masse de la sphere doit etre parfaitement symétrique
* La face du socle contient un afficheur led (segments) et un bouton.
* La partie supérieure de la sphere doit acceuillir un élément métalique (pour etre maintenue par l'électroaimant)
* La partie inférieur de la sphere doit acceuillir une bobine (pour alimenter de petites led dans la sphere).
* La sphere sera composée, aux poles, de leds RGB.
* Le socle acceuillera un haut parleur

#### Contraintes esthétiques
La déscription du design de l'objet est fait dans le document [Design Terre](https://github.com/Monierv/OCS/blob/master/Documentation/EARTH_DESIGN.md)

* La sphere doit etre le plus "sphérique" possible.
* Tout les plastiques doivent etre lisse et brillant.
* De preference, la couleur sera noir pour le support de la sphere.
* La couleur de la sphere n'a aucune importance puisqu'une surface y sera coller pour apporter les informations géographiques.

#### Contraintes environnementales

L'objet sera posé en intérieur, de préférence dans la chambre ou un bureau. Aucune résistance spécifique n'est a prévoir pour le socle. 
 
#### Contraintes économiques

* Aucune machine ou type d'usinage n'est imposé.
* Le budget maxi est fixé par le responsable du module.

