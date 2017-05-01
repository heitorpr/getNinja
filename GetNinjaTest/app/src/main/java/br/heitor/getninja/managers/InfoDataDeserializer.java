package br.heitor.getninja.managers;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import br.heitor.getninja.models.InfoData;

public class InfoDataDeserializer implements JsonDeserializer<InfoData> {
    @Override
    public InfoData deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {
        JSONObject myItem;
        InfoData data = new InfoData();

        try {
            myItem = new JSONObject(je.toString());
            data.setLabel(myItem.getString("label"));

            List<String> values = new ArrayList<>();

            try {
                JSONArray array = myItem.getJSONArray("value");
                for (int i = 0; i < array.length(); i++) {
                    values.add(array.getString(i));
                }
            } catch (Exception ex) {
                String value = myItem.getString("value");
                values.add(value);
            }

            data.setValue(values);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }
}