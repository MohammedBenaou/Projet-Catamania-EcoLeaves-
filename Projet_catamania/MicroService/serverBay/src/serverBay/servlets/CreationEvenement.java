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
import serverBay.beans.Evenement;
import serverBay.beans.Utilisateur;

public class CreationEvenement extends HttpServlet {
    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        // Récupération des données saisies, envoyées en tant que paramètres de
        // la requête GET générée à la validation du formulaire
        String nom = request.getParameter( "nomUtilisateur" );
        String password = request.getParameter( "passwordUtilisateur" );
        String email = request.getParameter( "emailUtilisateur" );

        String intitule = request.getParameter( "intituleEvenement" );
        String descriptif = request.getParameter( "descriptifEvenement" );
        String lieu = request.getParameter( "lieuEvenement" );
        String horaire = request.getParameter( "heureEvenement" );

        String message = "";

        // Initialisation du message à afficher
        if ( nom == null || nom.trim().isEmpty() ||
                password == null || password.trim().isEmpty() ||
                email == null || email.trim().isEmpty() ||
                intitule == null || intitule.trim().isEmpty() ||
                descriptif == null || descriptif.isEmpty() ||
                lieu == null || lieu.isEmpty() ||
                horaire == null || horaire.isEmpty() ) {
            this.getServletContext().getRequestDispatcher( "/creationEvenement.jsp" ).forward( request, response );
        } else {
            message = "Evénement créé avec succès !";
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

        String index2 = "events";
        Map<String, Object> jsonData2 = new HashMap<>();
        jsonData2.put( "intitule", intitule );
        jsonData2.put( "descriptif", descriptif );
        jsonData2.put( "lieu", lieu );
        jsonData2.put( "horaire", horaire );

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
        JSONObject manifestation = driver.searchCustom( index2, type,
                "lieu:\"" + lieu + "\" AND horaire:\"" + horaire + "\"" );

        JSONObject benevole = membre.getJSONObject( "hits" ).getJSONArray( "hits" ).getJSONObject( 0 )
                .getJSONObject( "_source" );
        JSONObject activite = manifestation.getJSONObject( "hits" ).getJSONArray( "hits" ).getJSONObject( 0 )
                .getJSONObject( "_source" );

        /*
         * Création des beans Utilisateur et Evénement et initialisation avec
         * les données récupérées
         */
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom( benevole.getString( "nom" ) );
        utilisateur.setPassword( benevole.getString( "password" ) );
        utilisateur.setEmail( benevole.getString( "email" ) );

        Evenement evenement = new Evenement();
        evenement.setUtilisateur( utilisateur );
        evenement.setIntitule( activite.getString( "intitule" ) );
        evenement.setDescriptif( activite.getString( "descriptif" ) );
        evenement.setLieu( activite.getString( "lieu" ) );
        evenement.setHoraire( activite.getString( "horaire" ) );

        /* Ajout du bean et du message à l'objet requête */
        request.setAttribute( "evenement", evenement );
        request.setAttribute( "message", message );

        /* Transmission à la page JSP en charge de l'affichage des données */
        this.getServletContext().getRequestDispatcher( "/afficherEvenement.jsp" ).forward( request, response );
    }
}
