package com.macormap.newtest;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.macormap.newtest.data.DecodeAuthor;
import com.macormap.newtest.data.Obj_post;
import com.macormap.newtest.data.SubComment;

import java.util.ArrayList;
import java.util.List;


public class OverFragment extends Fragment  {

    private OnOverFragmentListener mListener;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager mRecyclerlayoutManager;
    private OrientationHelper mHorizontalHelper;

    private MainAdapter mainAdapter;
    private List<Obj_post> listPost;

    private List<SubComment> listSubComment;

    private TextView    txtNum1;
    private TextView    txtNum2;

    private ImageButton bottom_but_1;
    private ImageButton bottom_but_2;
    private ImageButton bottom_but_3;

    private ImageView   bottomArrow_1;
    private ImageView   bottomArrow_2;
    private ImageView   bottomArrow_3;



    public interface OnOverFragmentListener {
        void onPostSelectFragmentInteraction(View view, int index);
        void setbackImages(int ind, int codRes, float traspPar);
    }


    public OverFragment() {
    }


    public Obj_post getPostSelected(int index) {
        return listPost.get(index);
    }


    public static OverFragment newInstance() {
        OverFragment fragment = new OverFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_over, container, false);
        setupTheView(view);
        return view;
    }


    private void setupTheView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.idMainRecycle);
        recyclerView.setHasFixedSize(true);
        mRecyclerlayoutManager = new LinearLayoutManager( getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(mRecyclerlayoutManager);

        mHorizontalHelper = OrientationHelper.createHorizontalHelper(mRecyclerlayoutManager);

        mainAdapter = new MainAdapter();
        recyclerView.setAdapter(mainAdapter);

        createDataMainRecycler();

        TextView txtnumImg = view.findViewById(R.id.idtxtTotNumImg);
        txtnumImg.setText(Integer.toString(listPost.size()));

        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        recyclerView.addOnScrollListener(mScrollListener);

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(),
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        onPostClicked(view, position);
                    }
                }));



        txtNum1  = (TextView) view.findViewById(R.id.idtxtIndex1);
        txtNum2  = (TextView) view.findViewById(R.id.idtxtIndex2);
        txtNum2.setAlpha(0.0f);

        setBottomButton(view);

        // first time set the second background image to null and trasparent
        mListener.setbackImages(2,0,0.0f);
    }

    public void onPostClicked(View view, int index) {
        if (mListener != null) {
            mListener.onPostSelectFragmentInteraction(view, index);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnOverFragmentListener) {
            mListener = (OnOverFragmentListener) context;
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

    private void setBottomButton(View view) {
        bottom_but_1   = (ImageButton) view.findViewById(R.id.idBottomBut1);
        bottom_but_2   = (ImageButton) view.findViewById(R.id.idBottomBut2);
        bottom_but_3   = (ImageButton) view.findViewById(R.id.idBottomBut3);

        bottom_but_1.setOnClickListener(v -> { setBottomArrow(1); });
        bottom_but_2.setOnClickListener(v -> { setBottomArrow(2); });
        bottom_but_3.setOnClickListener(v -> { setBottomArrow(3); });

        bottomArrow_1  = (ImageView) view.findViewById(R.id.idBottomArrow1);
        bottomArrow_2  = (ImageView) view.findViewById(R.id.idBottomArrow2);
        bottomArrow_3  = (ImageView) view.findViewById(R.id.idBottomArrow3);

        setBottomArrow(1);
    }

    private void setBottomArrow(int ind) {
//        Toast.makeText(this, "Clicked on Button Num : "+Integer.toString(ind),  Toast.LENGTH_SHORT).show();

        bottom_but_1.setColorFilter(Color.WHITE);
        bottom_but_2.setColorFilter(Color.WHITE);
        bottom_but_3.setColorFilter(Color.WHITE);

        bottomArrow_1.setVisibility(View.INVISIBLE);
        bottomArrow_2.setVisibility(View.INVISIBLE);
        bottomArrow_3.setVisibility(View.INVISIBLE);
        switch (ind) {
            case 1 :
                bottomArrow_1.setVisibility(View.VISIBLE);
                bottom_but_1.setColorFilter(Color.RED);
                break;
            case 2 :
                bottomArrow_2.setVisibility(View.VISIBLE);
                bottom_but_2.setColorFilter(Color.RED);
                break;
            case 3 :
                bottomArrow_3.setVisibility(View.VISIBLE);
                bottom_but_3.setColorFilter(Color.RED);
                break;
        }
    }

    // used just to inform that the recycler is scrolled
    private final RecyclerView.OnScrollListener mScrollListener =
            new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    int offer = recyclerView.computeHorizontalScrollOffset();
                    setScaleCardbyScrolled(offer, dx);
                }
            };


    private void setScaleCardbyScrolled(int offval,int locdx) {
        float minScale= 1.0f;
        float maxScale= 1.30f;
        int offer = recyclerView.computeHorizontalScrollOffset();

        int childCount = mRecyclerlayoutManager.getChildCount();
        if (childCount == 0) { return ; }

        final int center;
        if (mRecyclerlayoutManager.getClipToPadding()) {
            center = mHorizontalHelper.getStartAfterPadding() + mHorizontalHelper.getTotalSpace() / 2;
        } else {
            center = mHorizontalHelper.getEnd() / 2;
        }

        int nearest_1=-1;
        int nearest_2=-1;
        float paramtxt_1 = 0.0f;
        float paramtxt_2 = 0.0f;


        for (int i = 0; i < childCount; i++) {
            final View child = mRecyclerlayoutManager.getChildAt(i);

            int intVisibleItem = recyclerView.getChildAdapterPosition(child);

            int childCenter = mHorizontalHelper.getDecoratedStart(child)
                    + (mHorizontalHelper.getDecoratedMeasurement(child) / 2);
            int absDistance = Math.abs(childCenter - center);

            CardView cardView = child.findViewById(R.id.idCardrow);
            if (absDistance<center) {
                float param = ((center-absDistance)*1.0f)/(center*1.0f);
                float locscale = 1+(param*(maxScale-minScale));
                cardView.setScaleX(locscale);
                cardView.setScaleY(locscale);
                if (nearest_1<0) { nearest_1 = intVisibleItem; paramtxt_1 = param; }
                else {
                    if (nearest_2 < 0) {
                        nearest_2 = intVisibleItem;
                        paramtxt_2 = param;
                    }
                }
            }
            else  {
                cardView.setScaleX(1.0f);
                cardView.setScaleY(1.0f);
            }


            if (nearest_1==0) {
                if (offval==0) {
                    paramtxt_1 = 1.0f;
                    paramtxt_2 = 0.0f;
                }
            }

            if (nearest_1==(listPost.size()-1))   {
                paramtxt_1 = 1.0f;
                paramtxt_2 = 0.0f;
            }

            if (nearest_1<0) {txtNum1.setAlpha(0.0f);}
            else {
                txtNum1.setText(Integer.toString(nearest_1+1));
                txtNum1.setAlpha(paramtxt_1);
                int codImg = DecodeAuthor.getIdImgPost(getActivity(), nearest_1+1);
                mListener.setbackImages(1,codImg,paramtxt_1);
            }
            if (nearest_2<0) {txtNum2.setAlpha(0.0f);}
            else {
                txtNum2.setText(Integer.toString(nearest_2+1));
                txtNum2.setAlpha(paramtxt_2);
                int codImg = DecodeAuthor.getIdImgPost(getActivity(), nearest_2+1);
                mListener.setbackImages(2,codImg,paramtxt_2);
            }
        }
    }


    // Hard Code data
    private void createDataMainRecycler() {
        Activity activity = getActivity();
        SubComment subComment;

        listSubComment = new ArrayList<SubComment>();
        // 1
        subComment = new SubComment();
        subComment.setAuthor_code(activity,1);
        subComment.setSubCommenttxt("Bruges is small and beautiful city, you can enjoy walking here.");
        subComment.setComment_date("AUG 17,2014");
        listSubComment.add(subComment);
        // 2
        subComment = new SubComment();
        subComment.setAuthor_code(activity,2);
        subComment.setSubCommenttxt("Just a word of caution, please don't think you can possibly stary on");
        subComment.setComment_date("SEP 14,2014");
        listSubComment.add(subComment);
        // 3
        subComment = new SubComment();
        subComment.setAuthor_code(activity,3);
        subComment.setSubCommenttxt("the canal tours could be considered a 'tourist trap' however, I did enjoy. ");
        subComment.setComment_date("NOV 11,2016");
        listSubComment.add(subComment);
        // 4
        subComment = new SubComment();
        subComment.setAuthor_code(activity,4);
        subComment.setSubCommenttxt("Yes I wanna comment , How cook that one ? ");
        subComment.setComment_date("GEN 1,2018");
        listSubComment.add(subComment);
        // 5
        subComment = new SubComment();
        subComment.setAuthor_code(activity,1);
        subComment.setSubCommenttxt("Nothing to comment for the moment ");
        subComment.setComment_date("FEB 6,2018");
        listSubComment.add(subComment);

        listPost = new ArrayList<Obj_post>();
        Obj_post obj_post;

        obj_post = new Obj_post();
        obj_post.setAuthor_code(activity,1);
        obj_post.setMainComment("Woow so beautiful plate");
        obj_post.setNum_watch(2345);
        obj_post.setNum_subcomment(34);
        obj_post.setNum_love(185);
        obj_post.setPost_idImg(DecodeAuthor.getIdImgPost(activity,1));
        obj_post.addItemListComments(listSubComment.get(0));
        obj_post.addItemListComments(listSubComment.get(1));
        obj_post.addItemListComments(listSubComment.get(3));
        obj_post.addItemListComments(listSubComment.get(2));
        obj_post.addItemListComments(listSubComment.get(4));
        listPost.add(obj_post);

        obj_post = new Obj_post();
        obj_post.setAuthor_code(activity,2);
        obj_post.setMainComment("Yes today Lasagne and Fettuccine");
        obj_post.setNum_watch(835);
        obj_post.setNum_subcomment(12);
        obj_post.setNum_love(85);
        obj_post.setPost_idImg(DecodeAuthor.getIdImgPost(activity,2));
        obj_post.addItemListComments(listSubComment.get(4));
        obj_post.addItemListComments(listSubComment.get(2));
        obj_post.addItemListComments(listSubComment.get(0));
        listPost.add(obj_post);

        obj_post = new Obj_post();
        obj_post.setAuthor_code(activity,3);
        obj_post.setMainComment("Pizza remain the best");
        obj_post.setNum_watch(7832);
        obj_post.setNum_subcomment(92);
        obj_post.setNum_love(1085);
        obj_post.setPost_idImg(DecodeAuthor.getIdImgPost(activity,3));
        obj_post.addItemListComments(listSubComment.get(1));
        obj_post.addItemListComments(listSubComment.get(3));
        obj_post.addItemListComments(listSubComment.get(2));
        obj_post.addItemListComments(listSubComment.get(4));
        obj_post.addItemListComments(listSubComment.get(2));
        obj_post.addItemListComments(listSubComment.get(0));
        listPost.add(obj_post);

        obj_post = new Obj_post();
        obj_post.setAuthor_code(activity, 4);
        obj_post.setMainComment("Today I cook this plate");
        obj_post.setNum_watch(1000);
        obj_post.setNum_subcomment(100);
        obj_post.setNum_love(10);
        obj_post.setPost_idImg(DecodeAuthor.getIdImgPost(activity,4));
        obj_post.addItemListComments(listSubComment.get(3));
        obj_post.addItemListComments(listSubComment.get(2));
        obj_post.addItemListComments(listSubComment.get(4));
        obj_post.addItemListComments(listSubComment.get(2));
        obj_post.addItemListComments(listSubComment.get(0));
        obj_post.addItemListComments(listSubComment.get(3));
        obj_post.addItemListComments(listSubComment.get(2));
        obj_post.addItemListComments(listSubComment.get(4));
        obj_post.addItemListComments(listSubComment.get(2));
        obj_post.addItemListComments(listSubComment.get(0));
        obj_post.addItemListComments(listSubComment.get(3));
        obj_post.addItemListComments(listSubComment.get(2));
        obj_post.addItemListComments(listSubComment.get(4));
        obj_post.addItemListComments(listSubComment.get(2));
        obj_post.addItemListComments(listSubComment.get(0));
        listPost.add(obj_post);

        obj_post = new Obj_post();
        obj_post.setAuthor_code(activity,2);
        obj_post.setMainComment("Swite plate");
        obj_post.setNum_watch(7712);
        obj_post.setNum_subcomment(3456);
        obj_post.setNum_love(982);
        obj_post.setPost_idImg(DecodeAuthor.getIdImgPost(activity,5));
        obj_post.addItemListComments(listSubComment.get(0));
        listPost.add(obj_post);

        mainAdapter.updateListData(listPost);
    }
}
