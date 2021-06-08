package com.slapps.sljobs.ui.jobactivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.Response;

import com.slapps.sljobs.databinding.ActivityJobactivityclassBinding;
import com.slapps.sljobs.ui.webviewativity.webviewactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;

import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.slapps.sljobs.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class jobactivityclass extends AppCompatActivity {
    private List<jobmodel> joblist;
    private AppBarConfiguration appBarConfiguration;
    private ActivityJobactivityclassBinding binding;
    jobmodel jobobj;
    private ArrayList<jobmodel> contatclist;
    private RecyclerView recyclerView;
    JobsRecyclerViewAdapter recyclerViewAdapter;
    private LinearLayoutManager layoutManager;
    ProgressDialog progressDialogload;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

      setContentView(R.layout.activity_jobactivityclass);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(myToolbar);


//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_jobactivityclass);
//        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        //getcontatcs();

        getServerData();







    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_jobactivityclass);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }



    private void getServerData() {


        progressDialogload = new ProgressDialog(this);
        progressDialogload.setMessage("Loading...");
        progressDialogload.setCancelable(false);
        progressDialogload.show();

        String str = "http://slapphub99.xyz/aSL_Jobs/jobs.json";
        //System.out.print(str);
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = str;


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener < String > () {


                    @Override
                    public void onResponse(String response) {

                        JSONObject json = null;
                        try {
                            json = new JSONObject(response);

                            JSONArray ja_data = json.getJSONArray("heroes");
                           // Log.d("ishan tag", "all json objetcs are for jobs "+json.getString("heroes"));
                            Log.d("ishan tag", "all json objetcs are for jobs  aa"+ja_data);
                           int length = ja_data .length();
                            joblist = new ArrayList<>();
                            contatclist = new ArrayList<>();
                            for(int i=0; i<length; i++) {

                                JSONObject jObj = ja_data.getJSONObject(i);
                                String name = jObj.getString("name");
                                String imageurl = jObj.getString("tamil");
                                String link = jObj.getString("sinhala");
                                Log.d("nameis ",name);
                                jobobj= new jobmodel(name,link,imageurl,name);
                                contatclist.add(jobobj);
                            }
                            progressDialogload.cancel();

                            loaddata(contatclist);


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d("errror", e.toString()); // Not getting any error
                        }




                    }


                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("errror", error.toString()); // Not getting any error
            }
        });

        queue.add(stringRequest);

    }
    private void loaddata(ArrayList<jobmodel> contatclist) {
        LinearLayoutManager mGridLayoutManager = new LinearLayoutManager(this);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerViewjobs);
        JobsRecyclerViewAdapter recyclerViewAdapter = new JobsRecyclerViewAdapter(contatclist, this);
        layoutManager = new LinearLayoutManager(this);

        //recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setLayoutManager(mGridLayoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);

        recyclerViewAdapter.setOnItemClickListener(new ishanClickListenerforjob<jobmodel>() {
            @Override
            public void onItemClickcontacs(jobmodel postion) {

             String link=postion.getSinhala();

                Intent ii=new Intent(jobactivityclass.this, webviewactivity.class);
                ii.putExtra("link", postion.getSinhala());

                startActivity(ii);


            }
        });
    }
}