package com.example.myapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.Model.CourseModel;
import com.example.myapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CourseRVAdapter extends RecyclerView.Adapter<CourseRVAdapter.ViewHolder> {

    private Context context;
    private ArrayList<CourseModel> courseModalArrayList;

    // creating a constructor class.
    public CourseRVAdapter(Context context, ArrayList<CourseModel> courseModalArrayList) {
        this.context = context;
        this.courseModalArrayList = courseModalArrayList;
    }

    @NonNull
    @Override
    public CourseRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // passing our layout file for displaying our card item
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_row, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull CourseRVAdapter.ViewHolder holder, int position) {
        // setting data to our text views from our modal class.
        CourseModel courses = courseModalArrayList.get(position);
        holder.courseNameTV.setText(courses.getCourseName());
        holder.courseTracksTV.setText(courses.getcourseTracks());
        holder.courseModesTV.setText(courses.getcourseModes());
        Picasso.get().load(courses.getCourseImg()).into(holder.courseIV);
    }

    @Override
    public int getItemCount() {
        return courseModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // creating variables for our text views.
        private final TextView courseNameTV;
        private final TextView courseTracksTV;
        private final TextView courseModesTV;
        private final ImageView courseIV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views.
            courseIV = itemView.findViewById(R.id.idIVCourse);
            courseNameTV = itemView.findViewById(R.id.idTVCourseName);
            courseTracksTV = itemView.findViewById(R.id.idTVCourseDuration);
            courseModesTV = itemView.findViewById(R.id.idTVCourseDescription);

            //CardView itemClick listener
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    // item clicked

                    int a=getAdapterPosition();
                    CourseModel superHero =  courseModalArrayList.get(a);
                    Toast.makeText(context, "#" +  superHero.getCourseName() , Toast.LENGTH_SHORT).show();

                    //   Toast.makeText(context, "#" +  superHero.getTitle() , Toast.LENGTH_SHORT).show();

                }
            });
        }
    }
}