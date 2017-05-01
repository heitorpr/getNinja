package br.heitor.getninja.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class User {
    private String name;
    private String email;

    @SerializedName("_embedded")
    private UserPhones userPhones;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<Phone> getPhones() {
        return userPhones.getPhones();
    }

    private class UserPhones {
        private List<Phone> phones;

        public List<Phone> getPhones() {
            return phones;
        }

        public void setPhones(List<Phone> phones) {
            this.phones = phones;
        }
    }

    public String getPhonesInString() {
        String valueInString = "";
        for (Phone phone : getPhones()) {
            valueInString += phone.getNumber() + ", ";
        }

        valueInString = valueInString.substring(0, valueInString.lastIndexOf(","));
        return valueInString;
    }
}
