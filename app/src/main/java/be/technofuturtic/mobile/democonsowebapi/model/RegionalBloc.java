
package be.technofuturtic.mobile.democonsowebapi.model;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegionalBloc implements Parcelable
{

    @SerializedName("acronym")
    @Expose
    private String acronym;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("otherAcronyms")
    @Expose
    private List<String> otherAcronyms = null;
    @SerializedName("otherNames")
    @Expose
    private List<String> otherNames = null;
    public final static Parcelable.Creator<RegionalBloc> CREATOR = new Creator<RegionalBloc>() {


        @SuppressWarnings({
            "unchecked"
        })
        public RegionalBloc createFromParcel(Parcel in) {
            return new RegionalBloc(in);
        }

        public RegionalBloc[] newArray(int size) {
            return (new RegionalBloc[size]);
        }

    }
    ;

    protected RegionalBloc(Parcel in) {
        this.acronym = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.otherAcronyms, (java.lang.String.class.getClassLoader()));
        in.readList(this.otherNames, (java.lang.String.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public RegionalBloc() {
    }

    /**
     * 
     * @param otherNames
     * @param acronym
     * @param name
     * @param otherAcronyms
     */
    public RegionalBloc(String acronym, String name, List<String> otherAcronyms, List<String> otherNames) {
        super();
        this.acronym = acronym;
        this.name = name;
        this.otherAcronyms = otherAcronyms;
        this.otherNames = otherNames;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getOtherAcronyms() {
        return otherAcronyms;
    }

    public void setOtherAcronyms(List<String> otherAcronyms) {
        this.otherAcronyms = otherAcronyms;
    }

    public List<String> getOtherNames() {
        return otherNames;
    }

    public void setOtherNames(List<String> otherNames) {
        this.otherNames = otherNames;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(acronym);
        dest.writeValue(name);
        dest.writeList(otherAcronyms);
        dest.writeList(otherNames);
    }

    public int describeContents() {
        return  0;
    }

}
