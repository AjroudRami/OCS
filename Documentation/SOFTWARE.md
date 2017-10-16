![Constellation header](https://github.com/Monierv/OCS/blob/master/Documentation/resources/img/constellation_header.jpg)
# Conception du logiciel
## Plan

1. [Présentation](https://github.com/Monierv/OCS/blob/master/README.md)
2. [Conception du logiciel](https://github.com/Monierv/OCS/blob/master/Documentation/SOFTWARE.md)
3. [Conception des objets](https://github.com/Monierv/OCS/blob/master/Documentation/MATERIAL.md)

## Description fonctionnelle
### La Terre
#### Fonctionnalités
Ci-dessous l'ensemble des fonctionnalités de l'objet Terre. Elles serons remplies au fur et a mesure du devellopement:
- [ ] L’objet sera capable de se connecter au service de calendrier de l’utilisateur
(préalablement configuré par l’utilisateur) afin de récupérer son emploi du temps.
- [ ] Il pourra réveiller l’utilisateur à des horaires différents en fonction de son emploi du
temps en émettant des sons ou de la musique. L’utilisateur désactivera cette alarme
en appuyant sur la Terre ou un des satellites émettant le son (tous possèdent au
moins un bouton principal).
- [ ] Il pourra lire des playlists musicales (prédéfini par l’utilisateur) grâce au service
Spotify (l’ajout d’autre service pourra être ajouté lors de future mise à jour) lors du
réveil.
- [ ] Il pourra adapter les playlists à jouer lors du réveil en fonction du type d'événement
(dans la description de l'événement).
- [ ] Il décidera de l’heure du déclenchement du réveil en fonction des conditions de trafic
jusqu’au point de l'évènement, si l’adresse est dans la description de l'événement, en
effectuant une requête à Google Maps. Il réveillera l’utilisateur en utilisant une marge
par défaut le cas échéant.
- [ ] Le soir, elle calcule l’heure du coucher en fonction du réveil du lendemain. Puis il sera
capable d’identifier l’appareil ayant détecté l’utilisateur en dernier (en scannant le
réseau local et envoyant des requêtes aux satellites) et enverra à celui-ci un signal
(qui sera émis par le haut-parleur ou là diode/lampe du satellite) afin de rappeler à
l’utilisateur qu’il est important de dormir.
- [ ] L’utilisateur pourra choisir la Terre comme appareil de lecture de musique. La Terre
enverra alors aux satellites sur le réseau, le flux audio. Les satellites détectant une
présence auront pour consigne d'émettre le flux (via les haut-parleur). La musique
suit donc l’utilisateur à travers la maison.
- [ ] Cette dernière fonctionnalité peut être activé lors du réveil. Afin que l’utilisateur
puisse être réveillé et continuer d’écouter sa musique à travers la maison.

#### Diagramme Use-Case
Ci-dessous le digramme use-case correspondant aux fonctionnalités vues précédement:    

![Use-case Terre](https://github.com/Monierv/OCS/blob/master/Documentation/resources/img/use_case_terre.jpg)

#### Scenarii d'utilisation

 Scénario 1 
- C’est vendredi après-midi, Bob planifie sa séance de Jogging hebdomadaire du samedi matin afin d’être en forme pour ce début de week-end.
Ainsi il ajoute à son calendrier un créneau pour son jogging à 9h avec le tag “Sport”.
Avec une durée bien définie en avance, Bob sera réveillé par une musique adapté à l’activité qui l’attend histoire d’annoncer la couleur.

Scénario 2

- Bob a un rendez-vous pour un entretien d’embauche demain matin le soir, alors qu’il est dans sa salle, la Terre  calcule l’heure du coucher nécessaire en fonction du réveil du début son rendez-vous. La Terre ne détectant pas avec son capteur de proximité sa présence demande à son satellite s’il détecte sa présence de l’utilisateur. Son satellite répond par l'affirmative et la terre lui ordonne d’émettre un flux audio accompagné de signaux lumineux pour lui rappeler que c’est l’heure de dormir dès maintenant.

