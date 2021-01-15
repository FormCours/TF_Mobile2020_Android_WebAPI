package be.technofuturtic.mobile.democonsowebapi.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RequestHelper {

    private RequestHelper() {}

    public static String get(String url) {

        HttpURLConnection connection = null;
        String data = null;

        try {
            URL urlTarget = new URL(url);

            // Création de la connexion
            connection = (HttpURLConnection) urlTarget.openConnection();
            connection.setRequestMethod("GET");

            // Envoie la requete
            connection.connect();

            // Attente la réponse du serveur
            if(connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                return null;
            }


            // Lecture des données reçus
            InputStreamReader streamReader = new InputStreamReader(connection.getInputStream());
            BufferedReader reader = new BufferedReader(streamReader);

            StringBuilder builder = new StringBuilder();
            String line;
            while((line = reader.readLine()) != null) {
                builder.append(line);
            }
            reader.close();
            streamReader.close();

            data = builder.toString();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

        return data;
    }


}
