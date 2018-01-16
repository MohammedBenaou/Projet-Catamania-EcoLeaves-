/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univlr.elasticsearch;

import fr.univlr.crypt.Crypt;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Base64;
import org.json.JSONObject;

/**
 * Classe de test de la librairie.
 *
 * @author Thomas Chambon
 * @author Thomas Chambon, Grégory Daly
 */
public class Test {

    /**
     * Fonction main
     *
     * @param args
     * @throws UnknownHostException
     */
    public static void main(String[] args) throws UnknownHostException {
        ElasticSearchDriver els = new ElasticSearchDriver(InetAddress.getLoopbackAddress(), 9300);

        // Boucle de génération de couple username, password aléatoire.
        for (int i = 0; i < 10; i++) {
            String index = "users";
            String type = "credentials";
            String username = String.format("%010d", i);
            Crypt engine = new Crypt();
            String password = Base64.getEncoder().encodeToString(engine.createDBPassword(Crypt.generateRandomPassword()));

            JSONObject req = new JSONObject();
            req.accumulate("username", username);
            req.accumulate("password", password);

            // Appel fonction pour indexation.
            //els.index(index, type, req.toString());
        }

        // Appel fonction pour la recherche.
        JSONObject search = els.search("users", "credentials", "username", "1", "");
        search.toString();

    }

}
