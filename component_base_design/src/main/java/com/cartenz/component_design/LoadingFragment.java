package com.cartenz.component_design;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Collections;

public class LoadingFragment extends Fragment {

    private ImageView imgBack;
    private ImageView imgFront;

    private View view;

    private int count = 0;
    private boolean isBackVisible = false;
    private Handler mHandler = new Handler();
    private AnimatorSet setRightOut;
    private AnimatorSet setLeftIn;
    public static String DATA = "DATA";
    public static String BCG = "BCG";

    private ArrayList<Integer> imgDrawable = new ArrayList<>();

    private Handler handler = new Handler();
    private Runnable runnable = null;

    public static LoadingFragment newInstance() {
        Bundle args = new Bundle();
        LoadingFragment fragment = new LoadingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static LoadingFragment newInstance(String gson) {
        Bundle args = new Bundle();
        LoadingFragment fragment = new LoadingFragment();
        args.putString(DATA, gson);
        args.putInt(BCG, -1);
        fragment.setArguments(args);
        return fragment;
    }

    public static LoadingFragment newInstance(String gson, int backgroundResource) {
        Bundle args = new Bundle();
        LoadingFragment fragment = new LoadingFragment();
        args.putString(DATA, gson);
        args.putInt(BCG, backgroundResource);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.loading_fragment, container, false);

        imgBack = (ImageView) view.findViewById(R.id.imgBack);
        imgFront = (ImageView) view.findViewById(R.id.imgFront);
        RelativeLayout rlLoading = (RelativeLayout) view.findViewById(R.id.rlLoading);


        int backgroundResource = this.getArguments().getInt(BCG, -1);
        if (backgroundResource != -1) {
            rlLoading.setBackgroundResource(backgroundResource);
        }

        imgDrawable = new ArrayList<>();
//todo        images.add(com.cartenz.design.R.drawable.ic_charity);
//            images.add(com.cartenz.design.R.drawable.ic_heart);
//            images.add(com.cartenz.design.R.drawable.ic_maternity);
//            images.add(com.cartenz.design.R.drawable.ic_ribbon);
//            images.add(com.cartenz.design.R.drawable.ic_social_care);
        Collections.shuffle(imgDrawable);

        setRightOut = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(),
                R.animator.flip_right_out);

        setLeftIn = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(),
                R.animator.flip_left_in);

        imgFront.setImageDrawable(ContextCompat.getDrawable(getContext(), imgDrawable.get(count)));

        loop();

        return view;
    }

    private void loop() {
        runnable = () -> {
            count++;
            if (count >= imgDrawable.size() - 1) {
                count = 0;
            }
            flip();
            loop();
        };
        handler.postDelayed(runnable, 1000);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void flip() {
        try {
            if (!isBackVisible) {
                imgBack.setImageResource(imgDrawable.get(count));
                setRightOut.setTarget(imgFront);
                setLeftIn.setTarget(imgBack);
                setRightOut.start();
                setLeftIn.start();
                isBackVisible = true;
            } else {
                imgFront.setImageResource(imgDrawable.get(count));
                setRightOut.setTarget(imgBack);
                setLeftIn.setTarget(imgFront);
                setRightOut.start();
                setLeftIn.start();
                isBackVisible = false;
            }
        } catch (NullPointerException e) {

        }
    }

    public static void show(Context context) {
        showLoading(context, false, false);
        showLoading(context, true, false);
    }

    public static void show(Context context, boolean isCancelable) {
        showLoading(context, false, isCancelable);
        showLoading(context, true, isCancelable);
    }

    public static void dismiss(Context context) {
        showLoading(context, false, false);
    }

    public static void showLoading(Context context, boolean showAlert, boolean isCancelable) {
        AlertDialogFragment alertDialogFragment = new AlertDialogFragment();
        if (showAlert) {
            LoadingFragment loadingFragment = LoadingFragment.newInstance();
            alertDialogFragment.showAlert((AppCompatActivity) context, loadingFragment, isCancelable);
        } else { //dismiss alert
            alertDialogFragment.setDismiss((AppCompatActivity) context);
        }
    }

    public static void showLoading(Context context, ArrayList<Integer> arrays, boolean showAlert, boolean isCancelable) {
        AlertDialogFragment alertDialogFragment = new AlertDialogFragment();
        if (showAlert) {
            ArrayList<Integer> images = new ArrayList<>(arrays);

            LoadingFragment loadingFragment = LoadingFragment.newInstance();
            alertDialogFragment.showAlert((AppCompatActivity) context, loadingFragment, isCancelable);
        } else { //dismiss alert
            alertDialogFragment.setDismiss((AppCompatActivity) context);
        }
    }

}