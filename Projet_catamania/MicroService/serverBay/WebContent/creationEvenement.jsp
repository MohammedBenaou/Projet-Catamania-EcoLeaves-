<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Création d'un événement</title>
        <link type="text/css" rel="stylesheet" href="inc/style.css" />
    </head>
    <body>
        <div>
            <form method="get" action="creationEvenement">
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
                    <legend>Informations événement</legend>
                    
                    <label for="intituleEvenement">Intitulé<span class="requis">*</span></label>
                    <input type="text" id="intituleEvenement" name="intituleEvenement" value="" size="20" maxlength="20" />
                    <br />
                    
                    <label for="descriptifEvenement">Descriptif<span class="requis">*</span></label>
                    <input type="text" id="descriptifEvenement" name="descriptifEvenement" value="" size="20" maxlength="100" />
                    <br />
                    
                    <label for="lieuEvenement">Lieu<span class="requis">*</span></label>
                    <input type="text" id="lieuEvenement" name="lieuEvenement" value="" size="20" maxlength="20" />
                    <br />
                    
                    <label for="heureEvenement">Date et heure<span class="requis">*</span></label>
                    <input type="text" id="heureEvenement" name="heureEvenement" value="" size="20" maxlength="20" />
                    <br />
                </fieldset>
                <input type="submit" value="Valider"  />
                <input type="reset" value="Remettre à zéro" /> <br />
            </form>
        </div>
    </body>
</html>