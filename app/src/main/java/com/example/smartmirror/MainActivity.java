package com.example.smartmirror;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Button save;
    Switch time;
    int s_time=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        save = findViewById(R.id.button);
        time = findViewById(R.id.switch1);

        update_layout();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (time.isChecked())
                    s_time = 1;
                else
                    s_time = 0;

                doGet();
            }
        });
    }
    public  void update_layout()
    {
        Toast.makeText(MainActivity.this, "Running", Toast.LENGTH_SHORT).show();
        RequestQueue getQueue = Volley.newRequestQueue(this);
        String url = "http://kloud.cf/getVaishali.php";
        StringRequest getRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.equals("0"))
                            time.setChecked(false);
                        else
                            time.setChecked(true);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        //Log.d("Error.Response", response);
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                return params;
            }
        };
        getQueue.add(getRequest);
    }


    public void doGet()
    {
        Toast.makeText(MainActivity.this, "Running", Toast.LENGTH_SHORT).show();
        RequestQueue getQueue = Volley.newRequestQueue(this);
        String url = "http://kloud.cf/vaishali.php";
        StringRequest getRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        //Log.d("Error.Response", response);
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("status", String.valueOf(s_time));
                return params;
            }
        };
        getQueue.add(getRequest);
    }


}
