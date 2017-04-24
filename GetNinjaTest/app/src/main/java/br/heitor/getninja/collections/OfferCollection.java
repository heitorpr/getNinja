package br.heitor.getninja.collections;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import br.heitor.getninja.interfaces.InterfaceOffer;
import br.heitor.getninja.managers.ServiceGenerator;
import br.heitor.getninja.models.Offer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OfferCollection {
    @SerializedName("offers")
    private List<Offer> list;

    public List<Offer> getList() {
        return list;
    }

    public void fetch() {
        InterfaceOffer service = ServiceGenerator.createService(InterfaceOffer.class);
        service.fetchList().enqueue(new Callback<OfferCollection>() {
            @Override
            public void onResponse(Call<OfferCollection> call, Response<OfferCollection> response) {
                list = response.body().getList();
                Log.d("teste", "teste");
            }

            @Override
            public void onFailure(Call<OfferCollection> call, Throwable t) {
                Log.d("teste", "teste");
            }
        });
    }
}
