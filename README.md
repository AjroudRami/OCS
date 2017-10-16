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

Constellation est un ensemble d’objet connecté entre eux. Ces objets sont la planète Terre
ainsi qu’une constellation de satellites. Chaque objet remplit une tâche bien définie et
occupe une place différente dans la maison. Leur rôle sera de vous assister au quotidien, ils
vous informeront des conditions météo ou vous réveilleront avant l’heure de votre rendez-vous.
Tout cela en prenant en compte votre emploi du temps ou le temps de route nécessaire le tout en musique grâce à Spotify.

## Description du projet

Notre projet se présente sous la forme de plusieurs objets connecté entre eux. Nous nous
concentrerons ici sur deux types d’objets que nous créerons. Cependant le concept se
décline dans une gamme plus large et nous pouvons imaginer de nouvelles utilisations.
Constellation est composé d’un élément central, la Terre, ainsi que de multiples éléments
spécialisés, les satellites

### La Terre
![Hubble header](https://github.com/Monierv/OCS/blob/master/Documentation/resources/img/earth_header.jpg)  

Elle est l'élément central de notre projet. La Terre est le chef d’orchestre du projet. C’est elle
qui va communiquer avec les services Tiers (Spotify, Google Calendar, Google Maps) afin
de déterminer les décisions à prendre. Elle communiquera avec ses satellites pour distribuer
ses consignes ou récupérer des informations utiles (ex: savoir si un capteur de proximité à été déclenché). Inversement, ces satellites peuvent la notifier de l’arrivée de nouvelles informations ou commandes.  
Elle sera, en plus de contenir l’intelligence du projet, un élément de décoration pour tous les
amateurs de science.  
Cette sphère représentant le globe terrestre sera montée sur un support rotatif.

### Les satellites
Les satellites représentent des objets ayant un rôle bien défini et qui interagissent avec **La Terre**.
Leurs rôles sont variés et se complètent mutuellement afin d’avoir une orchestration harmonieuse depuis l’élément central.
Cependant, ils restent tout a fait capable de communiquer avec d'autre entités (autres objets connéctés) ou avec d'autre satellites via leurs API respectives.
Les tâches qu’ils exécutent sont, entre autres : diffusion de flux audio, indication de la
position de l’utilisateur, mesure des conditions météorologiques.
Nous possédons plusieurs types de satellites, dont chacun à une fonction et des possibilitées differentes.

#### Galileo-IOV PFM
![Galileo header](https://github.com/Monierv/OCS/blob/master/Documentation/resources/img/galileo_header.jpg)  
Galileo-IOV PFMG est le premier satellite opérationnel du systeme de positionnement européen du même nom. Ce satellite portera un capteur de proximité afin de detecter la présence d'un utilisateur pres de lui. Il permettra la localisation des utilisateur a travers la maison. Il pourra aussi grace à des leds et un haut parleur, emettre des signaux lumineux ou du son. 

#### Hubble
![Hubble header](https://github.com/Monierv/OCS/blob/master/Documentation/resources/img/hubble_header.jpg)

## Stack technologique
### Support matériel
![Raspberry Pi logo](https://github.com/Monierv/OCS/blob/master/Documentation/resources/img/logos/raspberrypi_logo.png =200x50)
![Java logo](https://github.com/Monierv/OCS/blob/master/Documentation/resources/img/logos/java_logo.jpg =200x50)
![Grove pi logo](https://github.com/Monierv/OCS/blob/master/Documentation/resources/img/logos/grovepi_logo.jpg =200x50)
