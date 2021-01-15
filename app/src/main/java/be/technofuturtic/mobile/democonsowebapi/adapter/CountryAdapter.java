package be.technofuturtic.mobile.democonsowebapi.adapter;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import be.technofuturtic.mobile.democonsowebapi.R;
import be.technofuturtic.mobile.democonsowebapi.model.Country;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> {

    /**
     * Création du View Holder => Logique pour les elements de la liste
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvCurrencies, tvPopulation;
        ImageView imgFlag;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_item_country_name);
            tvCurrencies = itemView.findViewById(R.id.tv_item_country_currencies);
            tvPopulation = itemView.findViewById(R.id.tv_item_country_population);
            imgFlag = itemView.findViewById(R.id.img_item_country_flag);
        }

        public TextView getTvName() {
            return tvName;
        }

        public TextView getTvCurrencies() {
            return tvCurrencies;
        }

        public TextView getTvPopulation() {
            return tvPopulation;
        }

        public ImageView getImgFlag() {
            return imgFlag;
        }
    }



    /**
     * Définition de la collection (et de ses méthodes)
     */
    private List<Country> countries;
    private Activity context;

    public CountryAdapter(Activity context) {
        this.countries = new ArrayList<>();
        this.context = context;
    }

    public void add(List<Country> countries) {
        this.countries.addAll(countries);
        notifyDataSetChanged();
    }

    public void clear() {
        this.countries.clear();
        notifyDataSetChanged();
    }



    /**
     * Définition de la méthode qui créer les views de la liste
     *  => Utiliser par le gestionnaire de vue !
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_country, parent, false);

        return new ViewHolder(v);
    }

    /**
     * Définition de la méthode qui remplace le contenue de la view
     *  => Utiliser par le gestionnaire de vue !
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Country country = countries.get(position);

        holder.tvName.setText(country.getName());

        if(!country.getCurrencies().isEmpty()) {
            String money = country.getCurrencies().get(0).getName();
            holder.tvCurrencies.setText(money);
        }

        holder.tvPopulation.setText(String.valueOf(country.getPopulation()));

        Uri flag = Uri.parse(country.getFlag());

        GlideToVectorYou.justLoadImage(context, flag, holder.imgFlag);
    }

    /**
     * Définition de la méthode qui renvoie la taille de la liste
     *  => Utiliser par le gestionnaire de vue !
     */
    @Override
    public int getItemCount() {
        return countries.size();
    }
}

