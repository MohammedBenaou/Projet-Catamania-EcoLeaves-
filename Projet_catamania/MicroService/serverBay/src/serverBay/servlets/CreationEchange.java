package serverBay.servlets;

import java.io.IOException;
import java.net.InetAddress;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import fr.univlr.crypt.Crypt;
import fr.univlr.elasticsearch.ElasticSearchDriver;
import serverBay.beans.Echange;
import serverBay.beans.Utilisateur;

public class CreationEchange extends HttpServlet {
    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        // Récupération des données saisies, envoyées en tant que paramètres de
        // la requête GET générée à la validation du formulaire
        String nom = request.getParameter( "nomUtilisateur" );
        String password = request.getParameter( "passwordUtilisateur" );
        String email = request.getParameter( "emailUtilisateur" );

        String intitule = request.getParameter( "intituleEchange" );
        String categorie = request.getParameter( "categorieEchange" );
        String descriptif = request.getParameter( "descriptifEchange" );

        String message = "";

        // Initialisation du message à afficher
        if ( nom == null || nom.trim().isEmpty() ||
                password == null || password.trim().isEmpty() ||
                email == null || email.trim().isEmpty() ||
                intitule == null || intitule.trim().isEmpty() ||
                categorie == null || categorie.isEmpty() ||
                descriptif == null || descriptif.isEmpty() ) {
            this.getServletContext().getRequestDispatcher( "/creationEchange.jsp" ).forward( request, response );
        } else {
            message = "Echange créé avec succès !";
        }

        // Ajout dans la base de données
        ElasticSearchDriver driver = new ElasticSearchDriver( InetAddress.getLoopbackAddress(), 9300 );

        String index1 = "users";
        String type = "credentials";
        Crypt engine = new Crypt();
        String mdpCrypte = Base64.getEncoder().encodeToString( engine.createDBPassword( password ) );
        Map<String, Object> jsonData1 = new HashMap<>();
        jsonData1.put( "nom", nom );
        jsonData1.put( "password", mdpCrypte );
        jsonData1.put( "email", email );

        String index2 = "exchanges";
        Map<String, Object> jsonData2 = new HashMap<>();
        jsonData2.put( "intitule", intitule );
        jsonData2.put( "categorie", categorie );
        jsonData2.put( "descriptif", descriptif );

        try {
            driver.indexWithId( index1, type, Crypt.md5( nom ), jsonData1 );
            driver.indexWithId( index2, type, Crypt.md5( intitule ), jsonData2 );
            Thread.sleep( 1000 );
        } catch ( NoSuchAlgorithmException e ) {
            e.printStackTrace();
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }

        JSONObject membre = driver.searchCustom( index1, type,
                "nom:\"" + nom + "\" AND password:\"" + mdpCrypte + "\"" );
        JSONObject change = driver.searchCustom( index2, type,
                "intitule:\"" + intitule + "\" AND categorie:\"" + categorie + "\"" );

        JSONObject benevole = membre.getJSONObject( "hits" ).getJSONArray( "hits" ).getJSONObject( 0 )
                .getJSONObject( "_source" );
        JSONObject commerce = change.getJSONObject( "hits" ).getJSONArray( "hits" ).getJSONObject( 0 )
                .getJSONObject( "_source" );

        // Création des beans Utilisateur et Echange et initialisation avec les
        // données récupérées
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom( benevole.getString( "nom" ) );
        utilisateur.setPassword( benevole.getString( "password" ) );
        utilisateur.setEmail( benevole.getString( "email" ) );

        Echange echange = new Echange();
        echange.setUtilisateur( utilisateur );
        echange.setIntitule( commerce.getString( "intitule" ) );
        echange.setCategorie( commerce.getString( "categorie" ) );
        echange.setDescriptif( commerce.getString( "descriptif" ) );

        /* Ajout du bean et du message à l'objet requête */
        request.setAttribute( "echange", echange );
        request.setAttribute( "message", message );

        /* Transmission à la page JSP en charge de l'affichage des données */
        this.getServletContext().getRequestDispatcher( "/afficherEchange.jsp" ).forward( request, response );
    }
}