package br.heitor.getninja.managers.parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.heitor.getninja.managers.InfoDataDeserializer;
import br.heitor.getninja.models.Address;
import br.heitor.getninja.models.InfoData;
import br.heitor.getninja.models.Offer;
import br.heitor.getninja.models.User;
import br.heitor.getninja.utils.DateUtils;
import br.heitor.getninja.utils.ErrorHandler;
import okhttp3.ResponseBody;

public class OfferParser {
    public static Offer parseFromSelf(ResponseBody body) {
        JSONObject object;
        Offer offer = new Offer();

        try {
            String json = body.string();
            object = new JSONObject(json);

            offer.setTitle(object.getString("title"));
            offer.setDistance(object.getDouble("distance"));
            offer.setLead_price(object.getInt("lead_price"));

            String userObject = object.getJSONObject("_embedded").getString("user");
            offer.setUser(new Gson().fromJson(userObject, User.class));

            String addressObject = object.getJSONObject("_embedded").getString("address");
            offer.setAddress(new Gson().fromJson(addressObject, Address.class));

            offer.setAcceptLink(object.getJSONObject("_links").getJSONObject("accept").getString("href"));
            offer.setRejectLink(object.getJSONObject("_links").getJSONObject("reject").getString("href"));

            JSONArray array = object.getJSONObject("_embedded").getJSONArray("info");
            List<InfoData> videos = new GsonBuilder().registerTypeAdapter(InfoData.class, new InfoDataDeserializer())
                    .create()
                    .fromJson(array.toString(), new TypeToken<List<InfoData>>(){}.getType());

            offer.setInfo(videos);
        } catch (Exception ex) {
            ErrorHandler.logError(ex);
        }

        return offer;
    }

    private static Offer parseFromList(String body) {
        JSONObject object;
        Offer offer = new Offer();

        try {
            object = new JSONObject(body);

            offer.setState(object.getString("state"));

            JSONObject request = object.getJSONObject("_embedded").getJSONObject("request");
            offer.setCreated_at(DateUtils.parseDateObject(request.getString("created_at")));
            offer.setTitle(request.getString("title"));

            String userObject = request.getJSONObject("_embedded").getString("user");
            offer.setUser(new Gson().fromJson(userObject, User.class));

            String addressObject = request.getJSONObject("_embedded").getString("address");
            offer.setAddress(new Gson().fromJson(addressObject, Address.class));

            offer.setSelfLink(object.getJSONObject("_links").getJSONObject("self").getString("href"));
        } catch (Exception ex) {
            ErrorHandler.logError(ex);
        }

        return offer;
    }

    public static List parseList(ResponseBody body) {
        List<Offer> list = new ArrayList<>();

        try {
            JSONObject json = new JSONObject(body.string());

            JSONArray jsonArray = json.getJSONArray("offers");
            for (int i = 0; i < jsonArray.length(); i++) {
                list.add(parseFromList(jsonArray.getString(i)));
            }

        } catch (Exception ex) {
            ErrorHandler.logError(ex);
        }

        return list;
    }
}
