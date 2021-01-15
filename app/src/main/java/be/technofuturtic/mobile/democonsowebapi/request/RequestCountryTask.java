package be.technofuturtic.mobile.democonsowebapi.request;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import be.technofuturtic.mobile.democonsowebapi.helper.RequestHelper;
import be.technofuturtic.mobile.democonsowebapi.model.Country;

public class RequestCountryTask extends AsyncTask<String, Void, List<Country>> {

    //region Event par Callback (Listener)
    @FunctionalInterface
    public interface OnResultListener {
        void onResult(List<Country> countries);
    }

    private OnResultListener resultListener;

    public void setResultListener(OnResultListener resultListener) {
        this.resultListener = resultListener;
    }
    //endregion


    private final String URL_COUNTRY =  "https://restcountries.eu/rest/v2/name/__search__";

    @Override
    protected List<Country> doInBackground(String... searchValues) {
        if(searchValues == null || searchValues.length != 1 || searchValues[0].trim().isEmpty()) {
           return null;  // Test de garde
        }

        // Envoie de la requete
        String url = URL_COUNTRY.replace("__search__", searchValues[0]);
        String data = RequestHelper.get(url);

        // Skip la conversion en cas de resultat null
        if(data == null) {
            return null;
        }

        // Conversion des données en collection de country
        Gson gson = new Gson();
        Type collectionType = new TypeToken<List<Country>>(){}.getType();
        List<Country> response = gson.fromJson(data, collectionType);

        return response;
    }

    @Override
    protected void onPostExecute(List<Country> countries) {
        super.onPostExecute(countries);

        if(resultListener != null) {
            resultListener.onResult(countries);
        }
    }
}
