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
import br.heitor.getninja.models.Lead;
import br.heitor.getninja.models.Offer;
import br.heitor.getninja.models.User;
import br.heitor.getninja.utils.DateUtils;
import br.heitor.getninja.utils.ErrorHandler;
import okhttp3.ResponseBody;

public class LeadParser {
    public static Lead parseFromSelf(ResponseBody body) {
        JSONObject object;
        Lead lead = new Lead();

        try {
            String json = body.string();
            object = new JSONObject(json);

            lead.setTitle(object.getString("title"));
            lead.setDistance(object.getDouble("distance"));
            lead.setLead_price(object.getInt("lead_price"));

            String userObject = object.getJSONObject("_embedded").getString("user");
            lead.setUser(new Gson().fromJson(userObject, User.class));

            String addressObject = object.getJSONObject("_embedded").getString("address");
            lead.setAddress(new Gson().fromJson(addressObject, Address.class));

            JSONArray array = object.getJSONObject("_embedded").getJSONArray("info");
            List<InfoData> videos = new GsonBuilder().registerTypeAdapter(InfoData.class, new InfoDataDeserializer())
                    .create()
                    .fromJson(array.toString(), new TypeToken<List<InfoData>>(){}.getType());

            lead.setInfo(videos);
        } catch (Exception ex) {
            ErrorHandler.logError(ex);
        }

        return lead;
    }

    private static Lead parseFromList(String body) {
        JSONObject object;
        Lead lead = new Lead();

        try {
            object = new JSONObject(body);

            lead.setCreated_at(DateUtils.parseDateObject(object.getString("created_at")));
            lead.setTitle(object.getJSONObject("_embedded").getJSONObject("request").getString("title"));

            String userObject = object.getJSONObject("_embedded").getString("user");
            lead.setUser(new Gson().fromJson(userObject, User.class));

            String addressObject = object.getJSONObject("_embedded").getString("address");
            lead.setAddress(new Gson().fromJson(addressObject, Address.class));

            lead.setSelfLink(object.getJSONObject("_links").getJSONObject("self").getString("href"));
        } catch (Exception ex) {
            ErrorHandler.logError(ex);
        }

        return lead;
    }

    public static List parseList(ResponseBody body) {
        List<Lead> list = new ArrayList<>();

        try {
            JSONObject json = new JSONObject(body.string());

            JSONArray jsonArray = json.getJSONArray("leads");
            for (int i = 0; i < jsonArray.length(); i++) {
                list.add(parseFromList(jsonArray.getString(i)));
            }

        } catch (Exception ex) {
            ErrorHandler.logError(ex);
        }

        return list;
    }
}
