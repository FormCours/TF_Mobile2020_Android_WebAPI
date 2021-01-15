package be.technofuturtic.mobile.democonsowebapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import be.technofuturtic.mobile.democonsowebapi.request.RequestCountryTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // FIXME Quick test => Delete this after !
        RequestCountryTask task = new RequestCountryTask();
        task.setResultListener(countries -> {
            int nbCountry = countries.size();
            String msg = String.format("Request finish. Nb country : %d", nbCountry);

            Toast.makeText(getApplicationContext(), msg,Toast.LENGTH_LONG ).show();
        });
        task.execute("bel");


    }
}