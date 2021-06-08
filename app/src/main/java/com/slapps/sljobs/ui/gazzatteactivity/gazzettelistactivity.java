package com.slapps.sljobs.ui.gazzatteactivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.slapps.sljobs.R;
import com.slapps.sljobs.databinding.ActivityJobactivityclassBinding;
import com.slapps.sljobs.ui.jobactivity.JobsRecyclerViewAdapter;
import com.slapps.sljobs.ui.jobactivity.jobmodel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class gazzettelistactivity  extends AppCompatActivity {
    private List<jobmodel> joblist;
    private AppBarConfiguration appBarConfiguration;
    private ActivityJobactivityclassBinding binding;
    gazettemodel jobobj;
    private ArrayList<gazettemodel> contatclist;
    private RecyclerView recyclerView;
    JobsRecyclerViewAdapter recyclerViewAdapter;
    private LinearLayoutManager layoutManager;
    private ProgressDialog progressDialogload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.gazzettemainlayout);



//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_jobactivityclass);
//        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        //getcontatcs();

        getServerData();







    }



    private void getServerData() {
        Log.d("ishan tag", "getting data gazztte");
        String str = "http://slapphub99.xyz/aSL_Jobs/gazette.json";
         System.out.print(str);
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = str;

        progressDialogload = new ProgressDialog(this);
        progressDialogload.setMessage("Loading...");
        progressDialogload.setCancelable(false);
        progressDialogload.show();


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener < String > () {


                    @Override
                    public void onResponse(String response) {

                        Log.d("ishan tag", "gazette respoonse is "+response);

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
                                jobobj= new gazettemodel(name,link,imageurl);
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
    private void loaddata(ArrayList<gazettemodel> contatclist) {
        LinearLayoutManager mGridLayoutManager = new LinearLayoutManager(this);
        recyclerView = (RecyclerView)findViewById(R.id.resviewgazzette);
        gazetterecycleviewadapter recyclerViewAdapter = new gazetterecycleviewadapter(contatclist, this);
        layoutManager = new LinearLayoutManager(this);

        //recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setLayoutManager(mGridLayoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);

        recyclerViewAdapter.setOnItemClickListener(new clicklistnerforgazette<gazettemodel>() {
            @Override
            public void onItemClickcontacs(gazettemodel postion) {

                   String link=postion.getDate();
//
                     Intent ii=new Intent(gazzettelistactivity.this, gazzetteactivitypdfview.class);
                      ii.putExtra("link", postion.getLink());
                      ii.putExtra("date", postion.getDate());
//
                      startActivity(ii);


            }
        });
    }
}
