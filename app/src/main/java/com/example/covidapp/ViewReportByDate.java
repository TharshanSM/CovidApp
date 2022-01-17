package com.example.covidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class ViewReportByDate extends AppCompatActivity {
    //variables
    private final String url="http://192.168.8.103:3050/api/covid/getDailyReportByDate";


    private RequestQueue myqueue;
    TextView updatedTime2,totalCases2,newCases2,totalDeath2,newDeath2,totalRecovered2;
    EditText date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_report_by_date);
        myqueue= Volley.newRequestQueue(this);
        updatedTime2=findViewById(R.id.txtUpdatedTime_);
        totalCases2=findViewById(R.id.txtTotalCases_);
        newCases2=findViewById(R.id.txtNewCases_);
        totalDeath2=findViewById(R.id.txtTotalDeath_);
        newDeath2=findViewById(R.id.txtNewDeath_);
        totalRecovered2=findViewById(R.id.txtTotalRecovered_);
        date=findViewById(R.id.txtDate);


    }

    public void BackView(View view) {
        Intent intent=new Intent(ViewReportByDate.this,Dashboard.class);
        startActivity(intent);
    }

    public void ViewReport(View view) {

        String reqdate=date.getText().toString();
        JSONObject requestbody=new JSONObject();
        try {
            requestbody.put("date",reqdate);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("Tree", "Date: "+reqdate);
        Log.d("Tree", "JSON: "+String.valueOf(requestbody));
        Log.d("Tree", "url: "+url);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, requestbody, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String status=response.getString("status");
                            updatedTime2.setText(status);
//                            if(status.equals("Find the Data")){
//                                JSONObject obj=response.getJSONObject("message");
//                                updatedTime2.setText(obj.getString("update_date_time"));
//                                totalCases2.setText(obj.getString("local_total_cases"));
//                                newCases2.setText(obj.getString("local_new_cases"));
//                                totalDeath2.setText(obj.getString("local_deaths"));
//                                newDeath2.setText(obj.getString("local_new_deaths"));
//                                totalRecovered2.setText(obj.getString("local_recovered"));
//                            }else{
//                                updatedTime2.setText("Cannot Find Data");
//                                totalCases2.setText("Cannot Find Data");
//                                newCases2.setText("Cannot Find Data");
//                                totalDeath2.setText("Cannot Find Data");
//                                newDeath2.setText("Cannot Find Data");
//                                totalRecovered2.setText("Cannot Find Data");
//                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        updatedTime2.setText("Error:");
                        Log.d("Error", "onErrorResponse: "+error);
                    }

                });

        // Access the RequestQueue through your singleton class.
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        myqueue.add(jsonObjectRequest);


    }
}