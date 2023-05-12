package com.example.motorest;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class AddReview extends AppCompatActivity {

    private EditText relaId;
    private EditText relaColor;
    private EditText relaRating;
    private EditText relaRemarks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_review);

        //ui variables
        relaId = findViewById(R.id.addRelaName);
        relaColor = findViewById(R.id.addRelaColor);
        relaRating = findViewById(R.id.addRelaRating);
        relaRemarks = findViewById(R.id.addRelaRemarks);

    }

    //method for data insert
    public void addReview(View v){
        insertData();
    }
    public void back(View view) {
        Intent myIntent = new Intent(AddReview.this, MainActivity.class);
        AddReview.this.startActivity(myIntent);
    }

    private void insertData(){
        //assigning values and using trim to remove unnecessary spaces
        String id = relaId.getText().toString().trim();
        String color = relaColor.getText().toString().trim();
        String rating = relaRating.getText().toString().trim();
        String remarks = relaRemarks.getText().toString().trim();

        //progress bar
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Aysa Lang Lods...");

        progressDialog.show();
        //POST request on insert api endpoint
        StringRequest request = new StringRequest(Request.Method.POST, "https://motorela6.000webhostapp.com/post_data.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        //the params is pass to the php request
                        //if the php post request returns a "data inserted" string,
                        // the request is successful and close this activity
                        if(response.equalsIgnoreCase("Data Inserted")){
                            Toast.makeText(AddReview.this,"Data Inserted", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            finish();
                        }else{
                            //else if the data insertion failed show this toast error
                            Toast.makeText(AddReview.this,"Nasulod na!",Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(AddReview.this,error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        ){
            //params to be pass on the php request
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String,String>();
                //pass variables to url parameters
                params.put("Rela", id);
                params.put("Color", color);
                params.put("Ratings", rating);
                params.put("Remarks", remarks);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(AddReview.this);
        requestQueue.add(request);
    }

}