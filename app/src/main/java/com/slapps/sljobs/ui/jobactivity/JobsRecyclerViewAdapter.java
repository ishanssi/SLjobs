package com.slapps.sljobs.ui.jobactivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.slapps.sljobs.R;

import java.util.List;

public class JobsRecyclerViewAdapter extends RecyclerView.Adapter<JobsRecyclerViewAdapter.MyViewHolder>{

   private List<jobmodel> contatcsList;
   private ishanClickListenerforjob<jobmodel> clickListener;
   private Context appctx;

   public int counter=1;

   public JobsRecyclerViewAdapter(List<jobmodel> contatcslist, Context ctx) {
       this.contatcsList = contatcslist;
       this.appctx=ctx;
   }

   @Override
   public JobsRecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contactgalerycard, parent, false);
       return new JobsRecyclerViewAdapter.MyViewHolder(view);
   }

   @Override
   public void onBindViewHolder(JobsRecyclerViewAdapter.MyViewHolder holder, int position) {

       final jobmodel contatcsobj = contatcsList.get(position);

        String newname=contatcsobj.getName().replace("–","");
       holder.name.setText(newname);
       holder.jobcount.setText(""+(position+1));



     //  holder.email.setText(contatcsobj.getEnglish());
      // holder.mobile.setText(contatcsobj.getSinhala());
        Glide.with(appctx).load(contatcsobj.getTamil()).into(holder.image);

       //  holder.image.setBackgroundResource(movie.getImage());
       holder.cardView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               clickListener.onItemClickcontacs(contatcsobj);
           }
       });


   }

   @Override
   public int getItemCount() {
       return contatcsList.size();
   }

   public void setOnItemClickListener(ishanClickListenerforjob<jobmodel> movieClickListener) {
       this.clickListener = movieClickListener;
   }

   public class MyViewHolder extends RecyclerView.ViewHolder {

       private TextView name;
       private TextView email;
       private TextView jobcount;
       private ImageView image;

       private CardView cardView;

       public MyViewHolder(View itemView) {
           super(itemView);
           name = itemView.findViewById(R.id.txtname);
           jobcount = itemView.findViewById(R.id.txtcount);
           // mobile = itemView.findViewById(R.id.txtmobile);
           image = itemView.findViewById(R.id.imageView2);
           cardView = itemView.findViewById(R.id.carView);
       }
   }


}



