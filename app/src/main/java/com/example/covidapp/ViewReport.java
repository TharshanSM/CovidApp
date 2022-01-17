package com.example.covidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class ViewReport extends AppCompatActivity {
    //variables
    private final String url="http://192.168.8.103:3050/api/covid/getDailyReport";
    private RequestQueue myqueue;
    TextView updatedTime,totalCases,newCases,totalDeath,newDeath,totalRecovered;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_report);
        myqueue= Volley.newRequestQueue(this);
        updatedTime=findViewById(R.id.txtUpdatedTime);
        totalCases=findViewById(R.id.txtTotalCases);
        newCases=findViewById(R.id.txtNewCases);
        totalDeath=findViewById(R.id.txtTotalDeath);
        newDeath=findViewById(R.id.txtNewDeath);
        totalRecovered=findViewById(R.id.txtTotalRecoverd);
        updatedTime=findViewById(R.id.txtUpdatedTime);
        onLoad();
    }

    private void onLoad() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject obj=response.getJSONObject("message");
                            updatedTime.setText(obj.getString("update_date_time"));
                            totalCases.setText(obj.getString("local_total_cases"));
                            newCases.setText(obj.getString("local_new_cases"));
                            totalDeath.setText(obj.getString("local_deaths"));
                            newDeath.setText(obj.getString("local_new_deaths"));
                            totalRecovered.setText(obj.getString("local_recovered"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });

        // Access the RequestQueue through your singleton class.
        myqueue.add(jsonObjectRequest);

    }


    public void backActivity(View view) {
        Intent intent=new Intent(ViewReport.this,Dashboard.class);
        startActivity(intent);
    }
}