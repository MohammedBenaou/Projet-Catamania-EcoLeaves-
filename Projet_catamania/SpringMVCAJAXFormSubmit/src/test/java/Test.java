import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Base64;

import org.json.JSONObject;

import fr.univlr.crypt.Crypt;
import fr.univlr.elasticsearch.ElasticSearchDriver;

public class Test {

	public static void main(String[] args) throws UnknownHostException {
		// TODO Auto-generated method stub
		ElasticSearchDriver els = new ElasticSearchDriver(InetAddress.getLoopbackAddress(), 9300);

        // Boucle de génération de couple username, password aléatoire.
        for (int i = 0; i < 10; i++) {
            String index = "user";
            String type = "credentials";
            String username = String.format("%010d", i);
            Crypt engine = new Crypt();
            String password = Base64.getEncoder().encodeToString(engine.createDBPassword(Crypt.generateRandomPassword()));

            JSONObject req = new JSONObject();
            req.accumulate("username", username);
            req.accumulate("password", password);

            // Appel fonction pour indexation.
            els.index(index, type, req.toString());
        }
	}

}
