package br.heitor.getninja.models;

public class Address {
    private String city;
    private String street;
    private String neighborhood;
    private String uf;

    private AddressGeoLocation geolocation;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public AddressGeoLocation getGeolocation() {
        return geolocation;
    }

    public void setGeolocation(AddressGeoLocation geolocation) {
        this.geolocation = geolocation;
    }

    public String getAddress() {
        return getNeighborhood() + " - " + getCity();
    }
}
