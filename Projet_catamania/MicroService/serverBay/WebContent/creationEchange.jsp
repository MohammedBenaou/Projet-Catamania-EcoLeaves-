<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Création d'un échange</title>
        <link type="text/css" rel="stylesheet" href="inc/style.css" />
    </head>
    <body>
        <div>
            <form method="get" action="creationEchange">
                <fieldset>
                    <legend>Informations utilisateur</legend>
    
                    <label for="nomUtilisateur">Nom d'utilisateur<span class="requis">*</span></label>
                    <input type="text" id="nomUtilisateur" name="nomUtilisateur" value="" size="20" maxlength="20" />
                    <br />
    
                    <label for="passwordUtilisateur">Mot de passe<span class="requis">*</span></label>
                    <input type="password" id="passwordUtilisateur" name="passwordUtilisateur" value="" size="20" maxlength="20" />
                    <br />
                    
                    <label for="emailUtilisateur">Adresse email<span class="requis">*</span></label>
                    <input type="email" id="emailUtilisateur" name="emailUtilisateur" value="" size="20" maxlength="60" />
                    <br />
                </fieldset>
                <fieldset>
                    <legend>Informations échange</legend>
                    
                    <label for="intituleEchange">Intitulé<span class="requis">*</span></label>
                    <input type="text" id="intituleEchange" name="intituleEchange" value="" size="20" maxlength="20" />
                    <br />
                    
                    <label for="categorieEchange">Catégorie<span class="requis">*</span></label>
                    <input type="text" id="categorieEchange" name="categorieEchange" value="" size="20" maxlength="20" />
                    <br />
                    
                    <label for="descriptifEchange">Descriptif<span class="requis">*</span></label>
                    <input type="text" id="descriptifEchange" name="descriptifEchange" value="" size="20" maxlength="100" />
                    <br />
                </fieldset>
                <input type="submit" value="Valider"  />
                <input type="reset" value="Remettre à zéro" /> <br />
            </form>
        </div>
    </body>
</html>