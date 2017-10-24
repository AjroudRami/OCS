![Constellation header](https://github.com/Monierv/OCS/blob/master/Documentation/resources/img/constellation_header.jpg)
# Projet Constellation - Objets Connectés et Services

## Plan

* Présentation
   * [Équipe](#Équipe)
   * [Présentation générale](#présentation-générale)
   * [Description du projet](#description-du-projet)
     * [La Terre](#la-terre)
     * [Les satellites](#les-satellites)
* [Conception du logiciel](https://github.com/Monierv/OCS/blob/master/Documentation/SOFTWARE.md)
* [Conception des objets](https://github.com/Monierv/OCS/blob/master/Documentation/MATERIAL.md)

## Équipe
* **AJROUD** Rami - Si5 Architecture logiciel - Scrum Master
* **DIOP** Mamadou - Master 2 IAM
* **MÔNIER** Marhold - SI5 IHM

## Présentation générale

Constellation est un ensemble d’objets connectés entre eux, représentés par la planète Terre ainsi qu’une constellation de satellites. Chaque objet remplit une tâche bien définie et occupe une place différente dans la maison. Leur rôle est d'assister l'utilisateur au quotidien : ils l'informeront des conditions météo, le réveilleront à l’heure pour ses rendez-vous. Constellation s'appuie sur l'emploi du temps et le temps de trajet nécessaire, et utilise Spotify pour trouver la musique appropriée.
Pour aller plus loin dans la thématique du projet, la Terre pourra informer l'utilisateur des actualités par rapport à zone selectionnée (via une manipulation précise) sur le globe.

## Description du projet

Constellation est composé d’un élément central, la Terre, ainsi que de multiples éléments
spécialisés, les satellites.

Constellation se présente sous la forme de plusieurs objets connectés entre eux : un élément central (la Terre), ainsi que de multiples éléments spécialisés qui vont interagir avec (les satellites). Notre projet peut se décliner en une gamme très large d'objets connectés ; cependant, nous nous concentrerons ici sur 2 objets simplement.

### La Terre
![Hubble header](https://github.com/Monierv/OCS/blob/master/Documentation/resources/img/earth_header.jpg)  

Elément central de notre projet, La Terre en est le chef d’orchestre. Elle communique avec les services Tiers (Spotify, Google Calendar, Google Maps) afin de déterminer les décisions à prendre. Elle communiquera avec les satellites pour distribuer ses consignes ou récupérer des informations utiles, comme les informations des capteurs. Ces satellites peuvent également la notifier de l’arrivée de nouvelles informations ou commandes.  
Elle est par ailleurs un élément de décoration pour tous les amateurs de science. Cette sphère représentant le globe terrestre sera montée sur un support rotatif.

### Les satellites
Les satellites représentent des objets connectés qui interagissent principalement avec **La Terre**, mais sont également capable de communiquer entre eux via leurs API respectives.
Les tâches qu’ils exécutent sont variées, par exemple : diffusion de flux audio, détecteur de mouvement, mesure des conditions météorologiques (Fonctionnalité non implémentée dans le cadre du projet).

#### Galileo-IOV PFM
![Galileo header](https://github.com/Monierv/OCS/blob/master/Documentation/resources/img/galileo_header.jpg)  
Galileo-IOV PFMG est le premier satellite opérationnel du systeme de positionnement européen du même nom. Ce satellite portera un capteur de proximité afin de detecter la présence d'un utilisateur dans la pièce.

#### Hubble
![Hubble header](https://github.com/Monierv/OCS/blob/master/Documentation/resources/img/hubble_header.jpg)

## Stack technologique
### Support matériel
<img src="https://github.com/Monierv/OCS/blob/master/Documentation/resources/img/logos/raspberrypi_logo.png" width="200px" height="100px">
<img src="https://github.com/Monierv/OCS/blob/master/Documentation/resources/img/logos/java_logo.jpg" width="200px" height="100px">
<img src="https://github.com/Monierv/OCS/blob/master/Documentation/resources/img/logos/grovepi_logo.jpg" width="200px" height="100px">
