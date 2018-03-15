package com.macormap.newtest;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.macormap.newtest.data.Obj_post;
import com.macormap.newtest.data.SubComment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by carlo on 11/03/2018.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder>{

    private List<Obj_post> listPostAdapter;


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_row,parent,false);
        return  new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Obj_post obj_post = listPostAdapter.get(position);

        holder.picAuthor.setImageResource(obj_post.getAuthor_idImg());
        holder.picImgPost.setImageResource(obj_post.getPost_idImg());

        holder.nameAuthor.setText(obj_post.getAuthor_name());
        holder.mainCommentAuthor.setText(obj_post.getMainComment());
        holder.txt_num_watch.setText( String.format("%,d", obj_post.getNum_watch()));
        holder.txt_num_subcomment.setText(String.format("%,d",obj_post.getNum_subcomment()));
        holder.txt_num_love.setText(String.format("%,d",obj_post.getNum_love()));
    }
    @Override
    public int getItemCount() {
        return (listPostAdapter==null) ? 0 : listPostAdapter.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public CardView cardView;
        public ImageView picAuthor;
        public ImageView picImgPost;
        public TextView nameAuthor;
        public TextView mainCommentAuthor;
        public TextView txt_num_watch;
        public TextView txt_num_subcomment;
        public TextView txt_num_love;

        public MyViewHolder(View itemView) {
            super(itemView);
            cardView           = (CardView) itemView.findViewById(R.id.idCardrow);
            picAuthor          = (ImageView) itemView.findViewById(R.id.idPicAuthor);
            picImgPost         = (ImageView) itemView.findViewById(R.id.idImgPost);
            nameAuthor         = (TextView) itemView.findViewById(R.id.idNameAuthor);
            mainCommentAuthor  = (TextView) itemView.findViewById(R.id.idCommentAuthor);
            txt_num_watch      = (TextView) itemView.findViewById(R.id.idNumWatch);
            txt_num_subcomment = (TextView) itemView.findViewById(R.id.idNumSubComment);
            txt_num_love       = (TextView) itemView.findViewById(R.id.idNumLove);
        }
    }

    public void updateListData( List<Obj_post> listPost) {
       listPostAdapter = listPost;
       notifyDataSetChanged();
    }


}
