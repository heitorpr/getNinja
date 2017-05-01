package br.heitor.getninja.models;

import java.text.NumberFormat;
import java.util.Date;
import java.util.List;

import br.heitor.getninja.Events.ItemUpdateEvent;
import br.heitor.getninja.ProjectApplication;
import br.heitor.getninja.R;
import br.heitor.getninja.interfaces.InterfaceLead;
import br.heitor.getninja.managers.ServiceGenerator;
import br.heitor.getninja.managers.parser.LeadParser;
import de.greenrobot.event.EventBus;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Lead {
    private String state;
    private String selfLink;
    private String title;

    private Date created_at;
    private int lead_price;
    private double distance;

    private User user;
    private Address address;
    private List<InfoData> info;

    public Lead() {
    }

    public static void fetch(final String link) {
        InterfaceLead service = ServiceGenerator.createService(InterfaceLead.class);
        service.fetch(link).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                EventBus.getDefault().post(new ItemUpdateEvent(link, LeadParser.parseFromSelf(response.body())));
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            }
        });
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSelfLink() {
        return selfLink;
    }

    public void setSelfLink(String selfLink) {
        this.selfLink = selfLink;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public int getLead_price() {
        return lead_price;
    }

    public void setLead_price(int lead_price) {
        this.lead_price = lead_price;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<InfoData> getInfo() {
        return info;
    }

    public void setInfo(List<InfoData> info) {
        this.info = info;
    }

    public String getDistanceTextInKM() {
        double km = getDistance()/1000;
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMaximumFractionDigits(0);

        return String.format(ProjectApplication.getAppContext().getString(R.string.info_distance_in_km),
                numberFormat.format(km));
    }
}
