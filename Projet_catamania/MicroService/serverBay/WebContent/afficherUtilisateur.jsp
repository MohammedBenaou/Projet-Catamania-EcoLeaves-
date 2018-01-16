<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Affichage d'un utilisateur</title>
        <link type="text/css" rel="stylesheet" href="inc/style.css" />
    </head>
    <body>
        <%-- Affichage de la chaîne "message" transmise par la servlet --%>
        <p class="info">${ message }</p>
        <%-- Puis affichage des données enregistrées dans le bean "utilisateur" transmis par la servlet --%>
        <p>Nom : ${ utilisateur.nom }</p>
        <p>Password : ${ utilisateur.password }</p>
        <p>Email : ${ utilisateur.email }</p>
    </body>
</html>