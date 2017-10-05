# OCS Projet Objets Connectés et Services
# Hophop - Le réveil connécté

## Team
* AJROUD Rami - Si5 Architecture logiciel - Scrum Master
* DIOP Mamadou - Master 2 IAM
* MÔNIER Marhold - SI5 IHM

## Présentation du projet : 

Hophop! sera installé dans la chambre à coucher, probablement sur une table de chevet. Il s’agit d’un réveil qui utilisera la météo, l’emploi du temps, ainsi qu’une playlist de musiques pour réveiller à temps l’utilisateur, et selon une musique adaptée. A terme, il sera capable de prédire à quelle heure l’utilisateur va se lever, et pourra l’inviter à aller se coucher la veille.

Si le temps est ensoleillé, la musique utilisée par le réveil sera joyeuse, tandis que si son emploi du temps indique une activité sportive, la musique sera motivante. Le son pourra être envoyé sur des enceintes ou tout autre appareil connecté. Durant une durée de quelques semaines, l’utilisateur devra régler l’heure de son réveil manuellement ; ensuite, le réveil proposera automatiquement une heure de réveil en fonction des expériences passées. Il lui rappellera également déjà d’aller se coucher la veille de manière à avoir autant d’heures de sommeil que nécessaire pour démarrer sa journée en forme. On suppose que l’utilisateur active le réveil avant d’aller se coucher : la durée de sommeil est donc environ la durée entre l’activation du réveil et la sonnerie effective.


Il possède également un petit écran sur lequel sera indiqué l’heure courante, l’heure prévue de réveil, ainsi qu’un petit symbole représentant la météo du lendemain. Un petit haut-parleur sera installé pour réveiller l’utilisateur, et le prévenir de l’heure de réveil prévu le lendemain. Dans l’idéal, il possédera un micro pour pouvoir être contrôlé vocalement.

L’écran sera un écran à faible consommation d’énergie, il sera rétro-éclairé et donc visible de nuit seulement à l’appui d’un bouton. Le haut-parleur devra également consommer peu d’énergie, la puissance requise est faible car Hophop! s'utilisera en milieu silencieux. Afin de subvenir aux besoins de tout ce matériel, le réveil devra être branché sur une prise pour fonctionner.

Une interface Web (adaptée également aux appareils mobiles) permettra également de configurer le réveil, et de visualiser les différents temps de préparation en fonction des jours et des activités.


## Scénario d’utilisation :

Prérequis : Bob a une playlist musicale fournie, il utilise également Hophop! quotidiennement depuis plus d’un mois, et a tout son emploi du temps en ligne sur Google Agenda 

Le soir, Bob est notifié qu’il doit aller se coucher pour être en forme le lendemain. Demain, il commence à 9h. En général, il se lève 1h avant le début de son activité, Hophop! lui annonce qu’il a prévu le réveil pour 8h. Bob ne se prononce pas, l’horaire est confirmée. Le lendemain matin, Hophop! se connecte à la météo et enregistre qu’il fait beau, il est connecté aux enceintes de sa chambre et lance une musique douce et chaude (comme le soleil), puis annonce la météo et l’activité du jour. Bob dit “hophop” et le réveil s’éteint.


## Boitier

### Contraintes mécaniques

L’objet sera vraisemblablement utilisé dans la chambre à coucher, sur une table de chevet. Le principal risque est de pouvoir tomber : il faut donc qu’il résiste à des chutes d’une hauteur de 1m. Aucune résistance à l’humidité n’est requise.

### Autre contrainte du boîtier:

Le boîtier doit avoir des compartiments spéciaux blindé pour les haut parleurs. Ceux ci ont deux missions, éviter les perturbations électromagnétiques générées par les haut parleurs mais aussi maintenir une qualité audio suffisante à partir de haut parleurs très petits.

### Finition

L'appareil sera fait d'un plastique glossy de couleur.

## Croquis

![croquis](https://github.com/Monierv/OCS/blob/master/Documentation/resources/croquis.png)

## Matériel : 
* Ecran (faible consommation d’énergie, rétroéclairage commandé par bouton, pas besoin de couleurs)
* Haut-parleur (faible puissance : utilisation en milieu silencieux)
* 1 bouton ou surface capacitive : pour l’éclairage de l’écran (clic simple), pour régler l’heure (clic enfoncé), et pour activer/désactiver l’alarme (double clic)
* Micro (optionel)
* petit amplificateur audio (20 watt maximum)

