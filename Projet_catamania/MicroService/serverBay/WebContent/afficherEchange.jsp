<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Affichage d'un échange</title>
        <link type="text/css" rel="stylesheet" href="inc/style.css" />
    </head>
    <body>
        <%-- Affichage de la chaîne "message" transmise par la servlet --%>
        <p class="info">${ message }</p>
        <%-- Puis affichage des données enregistrées dans le bean "echange" transmis par la servlet --%>
        <p>Utilisateur</p>
        <%-- Les 3 expressions suivantes accèdent aux propriétés de l'utilisateur, qui est lui-même une propriété du bean echange --%>
        <p>Nom : ${ echange.utilisateur.nom }</p>
        <p>Password : ${ echange.utilisateur.password }</p>
        <p>Email : ${ echange.utilisateur.email }</p>
        <p>Echange</p>
        <p>Intitulé  : ${ echange.intitule }</p>
        <p>Catégorie  : ${ echange.categorie }</p>
        <p>Descriptif  : ${ echange.descriptif }</p>
    </body>
</html>