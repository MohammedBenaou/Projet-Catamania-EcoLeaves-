/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univlr.elasticsearch;

import fr.univlr.crypt.Crypt;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/**
 * Classe de test de la librairie.
 *
 * @author Thomas Chambon, Grégory Daly
 */
public class Test {

    /**
     * Fonction main
     *
     * @param args
     * @throws UnknownHostException
     */
    public static void main(String[] args) throws UnknownHostException, NoSuchAlgorithmException, InterruptedException, UnsupportedEncodingException {
        ElasticSearchDriver driver = new ElasticSearchDriver(InetAddress.getLoopbackAddress(), 9300);

        for (int i = 0; i < 10; i++) {
            String index = "users";
            String type = "credentials";
            Crypt engine = new Crypt();

            String username = String.format("%010d", i);

            Map<String,Object> jsonData = new HashMap<>();
            jsonData.put("nom", username);
            jsonData.put("password", Base64.getEncoder().encodeToString(engine.createDBPassword(Crypt.generateRandomPassword())));
            jsonData.put("email", "mdr@test.ru");

            //driver.index(index, type, req.toString());
            driver.indexWithId(index, type, Crypt.md5(username), jsonData);

        }

        //public JSONObject search(String index, String type, String field, String query, boolean wildcard)
        //public JSONObject searchSorted(String index, String type, String field, String query, String fieldsort, boolean wildcard, boolean asc)

        //puts the thread to sleep for 1 second to prevent searching before the
        //es node refreshes
        Thread.sleep(1000);
        JSONObject test2 = driver.search("users", "credentials", "nom", "1", true);
        JSONObject test3 = driver.searchSorted("users", "credentials", "email", "mdr", "nom.keyword", true, false);
        System.out.println(test2.get("hits"));
        System.out.println(test3.get("hits"));
    }
}
