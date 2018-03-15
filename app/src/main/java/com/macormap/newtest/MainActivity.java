package com.macormap.newtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.macormap.newtest.data.Obj_post;



public class MainActivity extends AppCompatActivity  implements
         DetailFragment.OnDetailFragmentListener
        ,OverFragment.OnOverFragmentListener {

    private OverFragment overFragment;
    private Obj_post postSelected;
    private View viewfragDetail;


    private ImageView   imgBack1;
    private ImageView   imgBack2;

    private AnimationSet animationFragOn;
    private AnimationSet animationFragOff;
    private float xfragSxMargin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbarSetup();

        overFragment  =  OverFragment.newInstance();
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .add(R.id.idFragmentContainer, overFragment)
                .commit();

        getBackRefImage();
    }

    // setup of the toolBar
    private void toolbarSetup() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.idToolBar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_search);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Toolbar left Button clicked",Toast.LENGTH_SHORT).show();
            }
        });
    }

    // get reference of the two background images
    private void getBackRefImage() {
        imgBack1 = (ImageView) findViewById(R.id.idMainBack_1);
        imgBack2 = (ImageView) findViewById(R.id.idMainBack_2);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // not usefull just prepared and have an icon on right of Toolbar
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // not usefull just prepared
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    // Implementation of  OnDetailFragmentListener [START]
    @Override
    public void onCloseFragmentInteraction() {
        LinearLayout linearSubComment = (LinearLayout) findViewById(R.id.idSubComment);
        Animation animSub = AnimationUtils.loadAnimation(this, R.anim.exit_trasl_animation);
        animSub.setFillAfter(true);
        linearSubComment.startAnimation(animSub);

        View viewfrag = findViewById(R.id.idViewFragDetail);
        viewfrag.setPivotX(xfragSxMargin);
        viewfrag.setPivotY(0);
        viewfrag.startAnimation(animationFragOff);

        Log.d("APP22","close Frag");
    }

    @Override
    public Obj_post onGetPostSelected() {
        return postSelected;
    }
    // Implementation of  OnDetailFragmentListener [END]


    // Implementation of  OnOverFragmentListener [START]
    @Override
    public void onPostSelectFragmentInteraction(View view, int index) {
        // here remeamber the Obj with data of the post selected
        // gived to the other fragment using  onGetPostSelected()
        postSelected = overFragment.getPostSelected(index);

        DetailFragment detailFragment = DetailFragment.newInstance();

        createAnimationFrag(view);

            android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction()
                    .replace(R.id.idFragmentContainer, detailFragment)
//                    .add(R.id.idFragmentContainer, detailFragment)
//                    .addToBackStack(null)
                    .commit();
            fm.executePendingTransactions();



        LinearLayout linearSubComment = (LinearLayout) findViewById(R.id.idSubComment);
        Animation animSub = AnimationUtils.loadAnimation(this, R.anim.enter_trasl_animation);
        animSub.setFillAfter(true);
        linearSubComment.startAnimation(animSub);

        viewfragDetail = findViewById(R.id.idViewFragDetail);
        viewfragDetail.setPivotX(xfragSxMargin);
        viewfragDetail.setPivotY(0);
        viewfragDetail.startAnimation(animationFragOn);
 //       viewfragDetail.setAlpha(0.6f);
    }


    private void createAnimationFrag(View view) {

        // get the recyler parent of the card selected to know the y position of parent
        RecyclerView rec = (RecyclerView) view.getParent();
        float xrec = rec.getX();
        float yrec = rec.getY();

        // x , y of card not scaled
        float x1 = view.getX();
        float y1 = view.getY();

        // to get dimension of schede
        float xdim = view.getWidth();
        float ydim = view.getHeight();

        Log.d("APP22","dimx scheda "+Float.toString(xdim));
        Log.d("APP22","dimy scheda "+Float.toString(ydim));

        // need the scale to know exactly the vertex point to align the fragment
        float xscal = view.getScaleX();
        float yscal = view.getScaleY();

        float dimxScaled = xdim * xscal;
        float dimyScaled = ydim * yscal;
        Log.d("APP22","dimxScaled scheda "+Float.toString(dimxScaled));
        Log.d("APP22","dimyScaled scheda "+Float.toString(dimyScaled));


        xfragSxMargin = xdim/11f;

        float dimXback = imgBack1.getWidth();
        float dimYback = imgBack1.getHeight();


        dimXback = (dimXback*10f)/11f;

        float rapXImages = (xdim*xscal) /(dimXback*1.0f);

        float scaleYToApply = (ydim*yscal) / (dimYback/2.0f);

        // the coordinate of upper left coner of the image of selected card
        // the y of recycler more the y of the card but ( update for current scale of card )
        float y1v = yrec+ y1+ ( ydim*(1-yscal)/2f );

        float x1v = xrec+ x1+ ( xdim*(1-xscal)/2f ) -xfragSxMargin;  //  - xfragSxMargin


        Animation traslOnFrag = new TranslateAnimation(
                Animation.ABSOLUTE,x1v,
                Animation.ABSOLUTE,0f,
                Animation.ABSOLUTE,y1v,
                Animation.ABSOLUTE,0f);
        traslOnFrag.setDuration(300);


        Animation scalerOn = new ScaleAnimation(
                rapXImages,1.0f,
                scaleYToApply,1.0f,
                Animation.ABSOLUTE,x1v+xfragSxMargin/rapXImages,
                Animation.ABSOLUTE,y1v );
        scalerOn.setDuration(300);

        animationFragOn = new AnimationSet(true);
        animationFragOn.addAnimation(traslOnFrag);
        animationFragOn.addAnimation(scalerOn);


        Animation traslOFFFrag = new TranslateAnimation(
                Animation.ABSOLUTE,0f,
                Animation.ABSOLUTE,x1v,
                Animation.ABSOLUTE,0f,
                Animation.ABSOLUTE,y1v);
        traslOFFFrag.setDuration(300);

        Animation scalerOFF = new ScaleAnimation(
                1.0f,rapXImages,
                1.0f,scaleYToApply,
                Animation.ABSOLUTE,x1v+xfragSxMargin/rapXImages,
                Animation.ABSOLUTE,y1v );
        scalerOFF.setDuration(300);


        animationFragOff = new AnimationSet(true);
        animationFragOff.addAnimation(traslOFFFrag);
        animationFragOff.addAnimation(scalerOFF);
        animationFragOff.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                viewfragDetail.setVisibility(View.INVISIBLE);
                android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
                fm.beginTransaction()
                        .replace(R.id.idFragmentContainer, overFragment)
                        .commit();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });



    }


    @Override
    public void setbackImages(int ind, int codRes, float traspPar) {
        switch (ind) {
            case 1 :
                imgBack1.setImageResource(codRes);
                imgBack1.setAlpha(traspPar);
            break;
            case 2 :
                imgBack2.setImageResource(codRes);
                imgBack2.setAlpha(traspPar);
                break;
        }
    }
    // Implementation of  OnOverFragmentListener [END]


}
