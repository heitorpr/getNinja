package br.heitor.getninja.interfaces;

import br.heitor.getninja.collections.LeadCollection;
import br.heitor.getninja.collections.OfferCollection;
import retrofit2.Call;
import retrofit2.http.GET;

public interface InterfaceLead {
    @GET("leads")
    Call<LeadCollection> fetchList();
}
