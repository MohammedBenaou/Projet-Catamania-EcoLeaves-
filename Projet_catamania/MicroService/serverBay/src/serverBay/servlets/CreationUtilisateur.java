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
import serverBay.beans.Utilisateur;

public class CreationUtilisateur extends HttpServlet {
    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

        // Récupération des données saisies, envoyées en tant que paramètres de
        // la requête GET générée à la validation du formulaire
        String nom = request.getParameter( "nomUtilisateur" );
        String password = request.getParameter( "passwordUtilisateur" );
        String email = request.getParameter( "emailUtilisateur" );

        String message = "";

        // Initialisation du message à afficher
        if ( nom == null || nom.trim().isEmpty() ||
                password == null || password.trim().isEmpty() ||
                email == null || email.trim().isEmpty() ) {
            this.getServletContext().getRequestDispatcher( "/creationUtilisateur.jsp" ).forward( request, response );
        } else {
            message = "Utilisateur créé avec succès !";
        }

        // Ajout dans la base de données
        ElasticSearchDriver driver = new ElasticSearchDriver( InetAddress.getLoopbackAddress(), 9300 );

        String index = "users";
        String type = "credentials";
        Crypt engine = new Crypt();
        String mdpCrypte = Base64.getEncoder().encodeToString( engine.createDBPassword( password ) );

        Map<String, Object> jsonData = new HashMap<>();
        jsonData.put( "nom", nom );
        jsonData.put( "password", mdpCrypte );
        jsonData.put( "email", email );

        try {
            driver.indexWithId( index, type, Crypt.md5( nom ), jsonData );
            Thread.sleep( 1000 );
        } catch ( NoSuchAlgorithmException e ) {
            e.printStackTrace();
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }

        JSONObject membre = driver.searchCustom( index, type,
                "nom:\"" + nom + "\" AND password:\"" + mdpCrypte + "\"" );

        JSONObject benevole = membre.getJSONObject( "hits" ).getJSONArray( "hits" ).getJSONObject( 0 )
                .getJSONObject( "_source" );

        // Création du bean Utilisateur et initialisation avec les données
        // récupérées
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom( benevole.getString( "nom" ) );
        utilisateur.setPassword( benevole.getString( "password" ) );
        utilisateur.setEmail( benevole.getString( "email" ) );

        /* Ajout du bean et du message à l'objet requête */
        request.setAttribute( "utilisateur", utilisateur );
        request.setAttribute( "message", message );

        /* Transmission à la page JSP en charge de l'affichage des données */
        this.getServletContext().getRequestDispatcher( "/afficherUtilisateur.jsp" ).forward( request, response );
    }

}
