<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Affichage d'un événement</title>
        <link type="text/css" rel="stylesheet" href="inc/style.css" />
    </head>
    <body>
        <%-- Affichage de la chaîne "message" transmise par la servlet --%>
        <p class="info">${ message }</p>
        <%-- Puis affichage des données enregistrées dans le bean "evenement" transmis par la servlet --%>
        <p>Utilisateur</p>
        <%-- Les 3 expressions suivantes accèdent aux propriétés de l'utilisateur, qui est lui-même une propriété du bean evenement --%>
        <p>Nom : ${ evenement.utilisateur.nom }</p>
        <p>Password : ${ evenement.utilisateur.password }</p>
        <p>Email : ${ evenement.utilisateur.email }</p>
        <p>Evénement</p>
        <p>Intitulé  : ${ evenement.intitule }</p>
        <p>Descriptif  : ${ evenement.descriptif }</p>
        <p>Lieu  : ${ evenement.lieu }</p>
        <p>Heure  : ${ evenement.horaire }</p>
    </body>
</html>