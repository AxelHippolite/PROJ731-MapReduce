# MapReduce Words Counter

## Introduction
Voici une reproduction de l'algorithme de MapReduce de Hadoop. Le but ici est de compter le nombre d'occurences de mots provenant d'un corpus de textes.
Le système créé un unique *Mapper* par fichier, et deux *Reducer*. Les résultats de chaque *Mapper* sont envoyés au deux *Reducer*, qui traitent les données corrrespondantes à leur fonction : un *Reducer* pour les mots de longueur paire et un *Reducer* pour les mots de longeur impaire. Le système utilise également le principe de **multithreading** afin de réduire le temps d'exécution.

## Fonctionnement
La classe *Main* contient la méthode qui permet de lancer l'algorithme. Il récupère d'abord les chemins d'accès de chaque fichier et créé un *Mapper* par fichier. Le programme execute les différentes fonctions et les *Map* issues des 2 *Reducers* sont ensuite fusionnées et triées par ordre alphabetique avant d'être affichées à l'utilisateur avec le temps d'execution du programme.

## La Classe *Mapper*
Un *Mapper* va lire un fichier texte et créer une liste de 2 *Map* : une pour les mots de longueur paire et une autre pour ceux de longueur impaire. Chaque *Map* contient différents mots et le nombre d'occurences de celui-ci (nombre d'apparition du mot dans l'ensemble du fichier). Les *Map* sont ensuite ajoutées dà la liste qui peut ensuite être récupéré grâce au *getter* de la classe. Les *Mapper* sont des *Thread* afin de pouvoir réaliser les calculs en parallele.

## La Classe *Reducer*
Le *Reducer* prend en paramètre les résultats des *Mapper*. Autrement dit, nous créeons 2 *Reducer*, un pour les mots pairs et un autres pour les mots impairs. Le *Reducer* travaillant avec les mots pairs (respectivement les mots impairs) va prendre en paramètre toutes les *Map* contenant les mots pairs (respectivement les mots impairs) issues des résultats des *Mapper*. Ensuite, chaque *Reducer* va soit garder le nombre d'occurence si le mot apparait qu'une fois dans tout le corpus, soit ajouter le nombre d'occurences si le mot apparait plusieurs dans les différents textes. Un *Reducer* renvoies une *Map* contenant les mots et leur nombre d'appartions. De la même manière que pour les *Mapper*, chaque *Reducer* est un *Thread* permettant d'effectuer les calculs en parallèle.

## Améliorations
* Implémenter un fonction de permettant de découper les textes afin d'avoir plus d'un *Mapper* par texte.
* Améliorer la règle de *Shuffle* (mots pairs/mots impairs).
