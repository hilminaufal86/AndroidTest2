package com.example.app_test1;

import android.net.Uri;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class NetworkUtils {

    private static final String GUEST_API_URL = "https://reqres.in/api/users";

    public static GuestStruct getGuest(int page, int per_page) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        GuestStruct guestStruct = null;

        try {
            Uri builtURI = Uri.parse(GUEST_API_URL).buildUpon()
                    .appendQueryParameter("page", ((Integer.toString(page))))
                    .appendQueryParameter("per_page", ((Integer.toString(per_page))))
                    .build();
            URL requestURL = new URL(builtURI.toString());
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();

            // Create a buffered reader from that input stream.
            reader = new BufferedReader(new InputStreamReader(inputStream));

            // Use a StringBuilder to hold the incoming response.
            StringBuilder builder = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }

            if (builder.length() == 0) {
                // Stream was empty. No point in parsing.
                return null;
            } else {
                try {
                    String rawResult = builder.toString();
                    JSONObject jsonObject = new JSONObject(rawResult);
                    JSONArray guestArray = jsonObject.getJSONArray("data");

                    int pg = parseInt(jsonObject.getString("page"));
                    int per_pg = parseInt(jsonObject.getString("per_page"));
                    int total = parseInt(jsonObject.getString("total"));
                    int total_pg = parseInt(jsonObject.getString("total_pages"));

                    guestStruct = new GuestStruct(pg,per_pg,total,total_pg);

                    int i = 0;
                    int guestID = 0;
                    String first_name = "";
                    String last_name = "";
                    String email = "";
                    String avatar = "";


                    while (i<guestArray.length()) {
                        JSONObject person = guestArray.getJSONObject(i);
                        guestID = parseInt(person.getString("id"));
                        first_name = person.getString("first_name");
                        last_name = person.getString("last_name");
                        email = person.getString("email");
                        avatar = person.getString("avatar");

                        if (guestID!= 0) {
                            guestStruct.setGuests(new Guest(guestID,email,first_name,last_name,avatar));
                        }

                        i += 1;
                    }
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return guestStruct;
    }
}
