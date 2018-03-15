package com.macormap.newtest;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.macormap.newtest.data.Obj_post;
import com.macormap.newtest.data.SubComment;

import java.util.List;

/**
 * Created by carlo on 13/03/2018.
 */

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.MyViewHolder>{

    private List<SubComment> listcommentAdapter;

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.other_comment,parent,false);
        return  new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        SubComment subComment = listcommentAdapter.get(position);
        holder.picAuthor.setImageResource(subComment.getAuthor_idImg());
        holder.nameAuthor.setText(subComment.getAuthor_name());
        holder.subComment.setText(subComment.getSubCommenttxt());
        holder.subDateComment.setText(subComment.getComment_date());


    }

    @Override
    public int getItemCount() {
      return (listcommentAdapter==null) ? 0 : listcommentAdapter.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView picAuthor;
        public TextView  nameAuthor;
        public TextView  subComment;
        public TextView  subDateComment;

        public MyViewHolder(View itemView) {
            super(itemView);
            picAuthor      = (ImageView) itemView.findViewById(R.id.idSubPicAuthor);
            nameAuthor     = (TextView) itemView.findViewById(R.id.idSubNameAuthor);
            subComment     = (TextView) itemView.findViewById(R.id.idSubComment);
            subDateComment = (TextView) itemView.findViewById(R.id.idDateSubComment);
        }
    }


    public void setListData( List<SubComment> listComment) {
        listcommentAdapter = listComment;
        notifyDataSetChanged();
    }

}
