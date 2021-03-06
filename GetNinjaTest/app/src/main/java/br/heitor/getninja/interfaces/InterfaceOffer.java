package br.heitor.getninja.interfaces;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface InterfaceOffer {
    @GET("offers")
    Call<ResponseBody> fetchList();

    @GET
    Call<ResponseBody> fetch(@Url String url);
}
