package com.slapps.sljobs.ui.gazzatteactivity;

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

class gazetterecycleviewadapter extends RecyclerView.Adapter<gazetterecycleviewadapter.MyViewHolder>{

    private List<gazettemodel> contatcsList;
    private clicklistnerforgazette<gazettemodel> clickListener;
    private Context appctx;

    public int counter=1;

    public gazetterecycleviewadapter(List<gazettemodel> contatcslist, Context ctx) {
        this.contatcsList = contatcslist;
        this.appctx=ctx;
    }

    @Override
    public gazetterecycleviewadapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gazettecardview, parent, false);
        return new gazetterecycleviewadapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(gazetterecycleviewadapter.MyViewHolder holder, int position) {

        final gazettemodel contatcsobj = contatcsList.get(position);


        holder.name.setText(contatcsobj.getDate());
        holder.jobcount.setText(""+(position+1));


        //  holder.email.setText(contatcsobj.getEnglish());
        // holder.mobile.setText(contatcsobj.getSinhala());
       Glide.with(appctx).load(contatcsobj.getImageurl()).into(holder.image);

      //  holder.image.setBackgroundResource(movie.getImage());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClickcontacs(contatcsobj);


               // Toast.makeText(appctx.getApplicationContext(), "clicked on "+contatcsList.get(position).getDate(),Toast.LENGTH_LONG).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return contatcsList.size();
    }

    public void setOnItemClickListener(clicklistnerforgazette<gazettemodel> movieClickListener) {
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
            name = itemView.findViewById(R.id.txtdate);
            jobcount = itemView.findViewById(R.id.txtcount);
            // mobile = itemView.findViewById(R.id.txtmobile);
             image = itemView.findViewById(R.id.imageView2);
            cardView = itemView.findViewById(R.id.carView);
        }
    }


}

