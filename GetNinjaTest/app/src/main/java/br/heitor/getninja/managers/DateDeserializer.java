package br.heitor.getninja.managers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.Date;

import br.heitor.getninja.utils.DateUtils;
import br.heitor.getninja.utils.Utils;

class DateDeserializer implements JsonDeserializer<Date> {
    @Override
    public Date deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {
        String myDate = je.getAsString();

        if (myDate == null || myDate.equals("null") || myDate.isEmpty()) return null;
        return DateUtils.parseDateObject(myDate);
    }
}