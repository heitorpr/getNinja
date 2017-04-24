package br.heitor.getninja.interfaces;

import br.heitor.getninja.collections.OfferCollection;
import retrofit2.Call;
import retrofit2.http.GET;

public interface InterfaceOffer {
    @GET("offers")
    Call<OfferCollection> fetchList();
}
