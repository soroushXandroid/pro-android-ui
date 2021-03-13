package com.soroush.protrial;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonParser {

     /*public static List<BrandModel> parseBrands(String content){

        List<BrandModel> list = new ArrayList<>();

        try {

            JSONArray array = new JSONArray(content);
            for (int i = 0; i < array.length(); i++){

                JSONObject object = array.getJSONObject(i);
                BrandModel model = new BrandModel();
                model.setId(object.getInt("id"));
                //model.setTypeId(object.getInt("brandId"));
                model.setPrice(object.getString("price"));
                model.setBrand(object.getString("brand"));
                model.setLogoPath(object.getString("path"));
                model.setFarsi(object.getString("farsi"));
                model.setLock(object.getInt("lock"));
                model.setDesc(object.getString("desc"));
                list.add(model);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return list;
    }

      static List<BrandModel> parseOrders(String content){

        List<BrandModel> list = new ArrayList<>();

        try {

            JSONArray array = new JSONArray(content);
            for (int i = 0; i < array.length(); i++){

                JSONObject object = array.getJSONObject(i);
                BrandModel model = new BrandModel();
                model.setId(object.getInt("id"));
                model.setBrandId(object.getInt("brandId"));
                model.setPrice(object.getString("price"));
                model.setBrand(object.getString("brand"));
                model.setLogoPath(object.getString("path"));
                model.setFarsi(object.getString("farsi"));
                model.setLock(object.getInt("lock"));
                model.setDesc(object.getString("desc"));
                list.add(model);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return list;
    }*/

}
