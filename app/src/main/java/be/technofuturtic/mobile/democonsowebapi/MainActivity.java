package be.technofuturtic.mobile.democonsowebapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import be.technofuturtic.mobile.democonsowebapi.adapter.CountryAdapter;
import be.technofuturtic.mobile.democonsowebapi.model.Country;
import be.technofuturtic.mobile.democonsowebapi.request.RequestCountryTask;

public class MainActivity extends AppCompatActivity implements RequestCountryTask.OnResultListener {

    Button btnSearch;
    EditText etQuery;
    TextView tvResultTitle;
    RecyclerView lvResultCountries;

    CountryAdapter countryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Recup des views
        btnSearch = findViewById(R.id.btn_main_search);
        etQuery = findViewById(R.id.et_main_query);
        tvResultTitle = findViewById(R.id.tv_main_result_title);
        lvResultCountries = findViewById(R.id.lv_main_result_countries);

        // Activé le bouton en fonction des valeurs recherché
        etQuery.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                String content = s.toString().trim();
                btnSearch.setEnabled(!content.isEmpty());
            }
        });

        // Comportement du bouton
        btnSearch.setOnClickListener(v -> {
            String query = etQuery.getText().toString();

            RequestCountryTask task = new RequestCountryTask();
            task.setResultListener(this);
            task.execute(query);

            tvResultTitle.setText(R.string.result_loading);
            countryAdapter.clear();
        });

        // Gestion de la RecyclerView
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());
        lvResultCountries.setLayoutManager(manager);

        countryAdapter = new CountryAdapter(this);
        lvResultCountries.setAdapter(countryAdapter);
    }

    @Override
    public void onResult(List<Country> countries) {
        // Changement du title des resutlats
        if (countries == null) {
            tvResultTitle.setText(R.string.result_none);
        } else {
            String title = getString(R.string.result_success, countries.size());
            tvResultTitle.setText(title);
        }

        if (countries != null) {
            countryAdapter.add(countries);
        }
    }
}