package com.example.myapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapp.Adapters.CourseRVAdapter;
import com.example.myapp.Model.CourseModel;
import com.example.myapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public class OnScrollRecyclerViewActivity extends AppCompatActivity {


    // creating variables for our UI components.
    int count = 0;
    String url = "https://jsonkeeper.com/b/WO6S";
    private ArrayList<CourseModel> courseArrayList;
    private RecyclerView courseRV;
    private CourseRVAdapter courseRVAdapter;
    private ProgressBar loadingPB;
    private NestedScrollView nestedSV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_scroll_recycler_view);


        // initializing our variables.
        courseRV = findViewById(R.id.idRVCourses);
        loadingPB = findViewById(R.id.idPBLoading);
        nestedSV = findViewById(R.id.idNestedSV);

        // initializing our array list.
        courseArrayList = new ArrayList<>();

        // calling a method to add data to our array list.
        getData1();

        // on below line we are setting layout manager to our recycler view.
        LinearLayoutManager manager = new LinearLayoutManager(this);
        courseRV.setLayoutManager(manager);
        // adding on scroll change listener method for our nested scroll view.
        nestedSV.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                // on scroll change we are checking when users scroll as bottom.
                if (scrollY == v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()) {
                    // in this method we are incrementing page number,
                    // making progress bar visible and calling get data method.
                    count++;
                    // on below line we are making our progress bar visible.
                    loadingPB.setVisibility(View.VISIBLE);
                    if (count < 20) {
                        // on below line we are again calling
                        // a method to load data in our array list.

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                getData1();
                            }
                        }, 5000);

                    }
                }
            }
        });
    }

    private void getData1() {
        for (int i=0; i<=9; i++){
            String courseName = "courseName"+i;
            String courseTracks = "courseTracks"+i;
            String courseMode = "courseMode"+i;
            String courseImageURL = "https://www.crayoninfotech.com/wp-content/uploads/2020/05/ux-design.png";
            courseArrayList.add(new CourseModel(courseName, courseMode, courseTracks, courseImageURL));
        }

        // on below line we are adding our array list to our adapter class.
        courseRVAdapter = new CourseRVAdapter(OnScrollRecyclerViewActivity.this, courseArrayList);

        // on below line we are setting
        // adapter to our recycler view.
        courseRV.setAdapter(courseRVAdapter);

        loadingPB.setVisibility(View.GONE);

    }

    private void getData() {
        // creating a new variable for our request queue
        RequestQueue queue = Volley.newRequestQueue(OnScrollRecyclerViewActivity.this);
        // in this case the data we are getting is in the form
        // of array so we are making a json array request.
        // below is the line where we are making an json array
        // request and then extracting data from each json object.
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                courseRV.setVisibility(View.VISIBLE);
                for (int i = 0; i < response.length(); i++) {
                    // creating a new json object and
                    // getting each object from our json array.
                    try {
                        // we are getting each json object.
                        JSONObject responseObj = response.getJSONObject(i);

                        // now we get our response from API in json object format.
                        // in below line we are extracting a string with
                        // its key value from our json object.
                        // similarly we are extracting all the strings from our json object.
                        String courseName = responseObj.getString("courseName");
                        String courseTracks = responseObj.getString("courseTracks");
                        String courseMode = responseObj.getString("courseMode");
                        String courseImageURL = responseObj.getString("courseimg");
                        courseArrayList.add(new CourseModel(courseName, courseMode, courseTracks, courseImageURL));

                        // on below line we are adding our array list to our adapter class.
                        courseRVAdapter = new CourseRVAdapter(OnScrollRecyclerViewActivity.this, courseArrayList);

                        // on below line we are setting
                        // adapter to our recycler view.
                        courseRV.setAdapter(courseRVAdapter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loadingPB.setVisibility(View.GONE);
                Log.d("Error12:", error.getMessage());
                Toast.makeText(OnScrollRecyclerViewActivity.this, "Fail to get the data..", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(jsonArrayRequest);
    }
}