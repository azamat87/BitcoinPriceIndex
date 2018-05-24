package azamat.kz.bitcoinpriceindex.models;

import android.util.Log;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * Created by Asus on 23.05.2018.
 */

public class CurrencyDeserializer implements JsonDeserializer<Currency> {

    @Override
    public Currency deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        Currency currency = new Currency();
        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            // For individual City objects, we can use default deserialisation:
            Log.e("myLog", "getKey " + entry.getKey());
            Log.e("myLog", "getValue " + entry.getValue());
            currency = context.deserialize(entry.getValue(), Currency.class);
        }
        return currency;
    }
}
