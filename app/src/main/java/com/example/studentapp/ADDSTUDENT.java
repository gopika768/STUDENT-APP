package com.example.studentapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.PixelCopy;
import android.view.View;
import android.view.textclassifier.TextSelection;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class ADDSTUDENT extends AppCompatActivity {
    EditText es1,es2,es3,es4,es5,es6,es7,es8;
    AppCompatButton btn1,btn2;
    String apiurl="https://courseapplogix.onrender.com/addstudents";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_addstudent);
        es1=(EditText) findViewById(R.id.nameid);
        es2=(EditText) findViewById(R.id.lnameid);
        es3=(EditText) findViewById(R.id.collegeid);
        es4=(EditText) findViewById(R.id.dobid);
        es5=(EditText) findViewById(R.id.courseid);
        es6=(EditText) findViewById(R.id.mobid);
        es7=(EditText) findViewById(R.id.emailid);
        es8=(EditText) findViewById(R.id.addressid);
        btn1=(AppCompatButton) findViewById(R.id.addstuddetid);
        btn2=(AppCompatButton) findViewById(R.id.backstudid);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getname=es1.getText().toString();
                String getlname=es2.getText().toString();
                String getcollege=es3.getText().toString();
                String getdob=es4.getText().toString();
                String getcourse=es5.getText().toString();
                String getmobile=es6.getText().toString();
                String getemail=es7.getText().toString();
                String getaddress=es8.getText().toString();
                //json object
                JSONObject student=new JSONObject();
                try {
                    student.put("firstname",getname);
                    student.put("lastname",getlname);
                    student.put("college",getcollege);
                    student.put("dob",getdob);
                    student.put("course",getcourse);
                    student.put("mobile",getmobile);
                    student.put("email",getemail);
                    student.put("address",getaddress);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                //json object request
                JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(
                        Request.Method.POST,
                        apiurl,
                        student,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Toast.makeText(getApplicationContext(), "added", Toast.LENGTH_SHORT).show();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_SHORT).show();
                            }
                        }
                );
                //request queue
                RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(jsonObjectRequest);




            }
        });
    }
}