package br.heitor.getninja.managers;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import br.heitor.getninja.BuildConfig;
import br.heitor.getninja.ProjectApplication;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    @SuppressWarnings("ConstantConditions")
    private static final HttpLoggingInterceptor.Level LOG_LEVEL =
            BuildConfig.LOG_HTTP_REQUESTS ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE;

    private static final String API_BASE_URL = getBaseUrl();
    private static OkHttpClient httpClient = OkHttpClientBuilder();

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder()));

    public static <S> S createService(Class<S> serviceClass) {
        Retrofit retrofit = builder.client(httpClient).build();
        return retrofit.create(serviceClass);
    }

    private static OkHttpClient OkHttpClientBuilder() {
        File cacheDirectory = ProjectApplication.getInstance().getCacheRequestDir();
        Cache cache = new Cache(cacheDirectory, 10 * 1024 * 1024); // 10M

        return getClientCommonBuilder()
                .cache(cache)
                .addInterceptor(new HttpLoggingInterceptor().setLevel(LOG_LEVEL))
                .build();
    }

    @NonNull
    private static OkHttpClient.Builder getClientCommonBuilder() {
        return new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS)
                .pingInterval(10, TimeUnit.SECONDS);
    }

    private static Gson GsonBuilder() {
        GsonBuilder builder = new GsonBuilder().registerTypeAdapter(Date.class, new DateDeserializer());
        return builder.create();
    }

    private static String getBaseUrl() {
        return BuildConfig.API_URL;
    }
}