package com.macormap.newtest;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.macormap.newtest.data.Obj_post;


public class DetailFragment extends Fragment {

    private OnDetailFragmentListener mListener;
    private Obj_post postSelected;

    private ImageView imagePost;
    private ImageView imageAuthor;
    private TextView  txtNameAuthor;
    private TextView  txtCommentAuthor;
    private TextView  txtNumWatch;
    private TextView  txtNumSubComment;
    private TextView  txtNumLove;


    public interface OnDetailFragmentListener {
        void onCloseFragmentInteraction();
        Obj_post onGetPostSelected();
    }


    public DetailFragment() {
    }

    public static DetailFragment newInstance() {
        DetailFragment fragment = new DetailFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        postSelected = mListener.onGetPostSelected();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);


        ImageView
        imagePost = (ImageView) view.findViewById(R.id.idImgPost);
        imagePost.setImageResource(postSelected.getPost_idImg());

        imageAuthor = (ImageView) view.findViewById(R.id.idPicAuthor);
        imageAuthor.setImageResource(postSelected.getAuthor_idImg());

        txtNameAuthor = (TextView) view.findViewById(R.id.idNameAuthor);
        txtNameAuthor.setText(postSelected.getAuthor_name());

        txtCommentAuthor = (TextView) view.findViewById(R.id.idCommentAuthor);
        txtCommentAuthor.setText(postSelected.getMainComment());

        txtNumWatch = (TextView) view.findViewById(R.id.idNumWatch);
        txtNumWatch.setText(String.format("%,d", postSelected.getNum_watch()));

        txtNumSubComment = (TextView) view.findViewById(R.id.idNumSubComment);
        txtNumSubComment.setText(String.format("%,d", postSelected.getNum_subcomment()));

        txtNumLove = (TextView) view.findViewById(R.id.idNumLove);
        txtNumLove.setText(String.format("%,d", postSelected.getNum_love()));


        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.idCommentRecycler);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mRecyclerlayoutManager;
        mRecyclerlayoutManager = new LinearLayoutManager( getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mRecyclerlayoutManager);

        CommentAdapter  commentAdapter = new CommentAdapter();
        recyclerView.setAdapter(commentAdapter);

        commentAdapter.setListData(postSelected.getListComments());

        ImageButton butClose = (ImageButton) view.findViewById(R.id.idclosefrag);
        butClose.setOnClickListener(v->{onButtonClose(); });

        return view;
    }

    public void onButtonClose() {
        if (mListener != null) {
            mListener.onCloseFragmentInteraction();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnDetailFragmentListener) {
            mListener = (OnDetailFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }



}
