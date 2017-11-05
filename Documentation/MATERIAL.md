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
 * L'orientation de la sphere servira plus tard pour d'autre fonctionnalités (controle de volume par example)

#### Scenarii d'utilisation

* C’est vendredi après-midi, Bob planifie sa séance de Jogging hebdomadaire du samedi matin. Il ajoute à son calendrier un créneau pour son jogging à 9h avec le tag “Sport”. Constellation calcule l'heure du reveil. Le lendemain, Bob est réveillé par une musique adapté à l’activité et un clignotement de la Terre. Il désactive le systeme en appuyant sur un bouton clignotant sur n'importe quel appareil connécté du systeme.

* Bob a un rendez-vous pour un entretien d’embauche le lendemain. Constellation calcule le temps de sommeil requis pour Bob. Dans la soirée, Bob est dans le salon, constellation detécte sa position grace aux satellites (capteur de proximité). Le systeme envoi alors un signal audio et lumineux au dernier objet ayant détécté la présence de bob pendant 1min. Si bob n'appuie pas sur le bouton <<désactiver>> le systeme émet alors sur tout les appareil connéctés pendant 1min. Bob sais alors que c'est l'heure de dormir.

#### Contraintes technologiques

Les contraintes technologiques sont les suivantes:  
* Le Raspberry étant un micro ordinateur, il y a un risque de surchauffe du systeme. Il faut donc y insérer des trous de ventilation.
* Le systeme néccéssite des trou et emplacement pour les connexions (cable d'alimentation, cable ethernet, usb).
* Le systeme doit etre intégré a la base du globe, sur la face arriere. L'emplacement est visible sur le modele solidworks du socle.
* Le support supperieur du globe doit acceuillir un systeme permettant de faire léviter le globe. Il contiendra une bobine (éléctroaimant) et un capteur Hall.
* Le globe doit etre suffisament léger pour pouvoir etre maintenu en lévitation par un petit éléctroaimant. Il doit etre **creux** et la **coque fine** dans un matériau léger.
* La répartition de la masse de la sphere doit etre au moins symétrique à son axe vertical.
* La face du socle contient un afficheur led (segments) et un bouton.
* La partie supérieure de la sphere doit acceuillir un élément métalique (pour etre maintenue par l'électroaimant)
* La partie inférieur de la sphere doit acceuillir une bobine (pour alimenter de petites led dans la sphere).
* La partie inférieur du support (support_magnétique.SLDPRT dans le dossier [Models](https://github.com/Monierv/OCS/tree/master/Models) ) doit acceuillir une bobine pour alimenter la bobine dans la partie inférieure de la sphere.
* Le socle acceuillera un haut parleur (tube sous la piece nommée socle.SLDPRT dans [Models](https://github.com/Monierv/OCS/tree/master/Models) )

#### Contraintes esthétiques
La déscription du design de l'objet est fait dans le document [Design Terre](https://github.com/Monierv/OCS/blob/master/Documentation/EARTH_DESIGN.md)

* La sphere doit etre le plus sphérique possible.
* Tout les plastiques doivent etre lisse et brillant.
* De preference, la couleur sera noir pour le support de la sphere.
* De preference, la couleur de la sphere sera le blanc.
* La sphere sera translucide.

#### Contraintes environnementales

L'objet sera posé en intérieur, de préférence dans la chambre ou un bureau. Aucune résistance spécifique n'est a prévoir pour le socle.  
Le globe étant en lévitation, il est possible qu'il tombe. Il faudra donc qu'il soit un minimum résistant aux chutes.
 
#### Contraintes économiques

* Aucune machine ou type d'usinage n'est imposé.
* Le budget maxi est fixé par le responsable du module.

#### Production attendue

Nous nous attendons a recevoir des premiers retours sur l'objet, des modifications de nos croquis, une discussion active sur le site Fusion A360, et surtout un objet.
Les modeles solidworks que nous avons préparé sont disponible dans le dossier: 
Nous attendons un résultat similaire.

### Galileo-IOV PFM
#### Expression du besoin

#### Contraintes technologiques

#### Contraintes esthétiques

#### Contraintes environnementales

#### Contraintes économiques

#### Production attendue  
Aucune production n'est attendue pour cet objet. Il s'agit d'un projet annexe qui sera réalisé par les membres de l'équipe OCS à l'aide du matériel que nous avons à notre disposition, a savoir:
* Imprimante 3D
* Raspberry Pi 3
* Arduino Leonardo  

Il ne constitue en rien un engagement contractuel. A défaut d'etre fabriqué, il sera mocké.
