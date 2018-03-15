package com.macormap.newtest.data;

import android.app.Activity;


/**
 * Created by carlo on 11/03/2018.
 */

public class DecodeAuthor {

    static String nameAuthor(int cod) {
      String res = "";
        switch (cod) {
            case 1 :
                res = "Zmrzlina";
                break;

            case 2 :
                res = "Waalewiener";
                break;

            case 3 :
                res = "William";
                break;

            case 4 :
                res = "Peter Robinson";
                break;

            default:
                res = "Unknow";
                break;
        }
      return res;
    }

    public static int getIdResourceImgAuthor(Activity activity, int codAuthor) {
        int res = 0;
        switch (codAuthor) {
            case 1 :
             res = activity.getResources().getIdentifier("pic1", "drawable", activity.getPackageName());
             break;

            case 2 :
                res = activity.getResources().getIdentifier("pic2", "drawable", activity.getPackageName());
                break;

            case 3 :
                res = activity.getResources().getIdentifier("pic3", "drawable", activity.getPackageName());
                break;

            case 4 :
                res = activity.getResources().getIdentifier("pic4", "drawable", activity.getPackageName());
                break;
        }
        return res;
    }

    public static int getIdImgPost(Activity activity, int codPost) {
        int res = 0;
        switch (codPost) {
            case 1 :
                res = activity.getResources().getIdentifier("food_1", "drawable", activity.getPackageName());
                break;

            case 2 :
                res = activity.getResources().getIdentifier("food_2", "drawable", activity.getPackageName());
                break;

            case 3 :
                res = activity.getResources().getIdentifier("food_3", "drawable", activity.getPackageName());
                break;

            case 4 :
                res = activity.getResources().getIdentifier("food_4", "drawable", activity.getPackageName());
                break;

            case 5 :
                res = activity.getResources().getIdentifier("food_5", "drawable", activity.getPackageName());
                break;
        }
        return res;
    }


}
