package com.example.motorest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ViewReview extends AppCompatActivity {

    private TextView postInfo;
    private RequestQueue myQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_review);

        //Retrieved Json Data
        postInfo = findViewById(R.id.textView_read);

        //adding a scroll feature in case the data fills the textview
        postInfo.setMovementMethod(new ScrollingMovementMethod());

        //for the request queue using volley
        myQueue = Volley.newRequestQueue(this);

        //calling this method so everytime the activity is called the data is updated
        getRequest();

    }

    public void back(View v){
        Intent myIntent = new Intent(ViewReview.this, MainActivity.class);
        ViewReview.this.startActivity(myIntent);
    }


    private void getRequest(){

        String url = "https://motorela6.000webhostapp.com/index.php"; //api endpoint

        //json request using GET
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            //the root object inside the json data is users
                            //browse the api endpoint to check the root object
                            //users is an JSONArray
                            JSONArray jsonArray = response.getJSONArray("rela"); //your table

                            //loop the json array according to its length
                            //assign and append data every loop
                            for(int i = 0; i < jsonArray.length(); i++){
                                JSONObject users = jsonArray.getJSONObject(i);
                                //variables
                                String Rela = users.getString("Rela");
                                String Color = users.getString("Color");
                                String Ratings = users.getString("Ratings");
                                String Remarks = users.getString("Remarks");

                                postInfo.append("Rela: "+Rela+"\n"+
                                        "Color: "+Color+"\n"+
                                        "Ratings: "+Ratings+"\n"+
                                        "Remarks:\n"+ Remarks+"\n\n"
                                );
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        //add the request queue
        myQueue.add(request);

    }
}