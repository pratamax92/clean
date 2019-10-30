package com.cartenz.component_design;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class AlertDialogFragment extends DialogFragment {

    public static String TAG = "TAG";

    public Fragment fragment;
    public boolean isCancelable;

    public AlertDialogFragment() {

    }


    public static AlertDialogFragment newInstance(Fragment frag, boolean isCancelable) {
        Bundle args = new Bundle();
        AlertDialogFragment fragment = new AlertDialogFragment();
        fragment.fragment = frag;
        fragment.isCancelable = isCancelable;
        fragment.setArguments(args);

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_fragment, container);

        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0));
        getDialog().setCanceledOnTouchOutside(isCancelable);
        getDialog().setCancelable(isCancelable);
        setCancelable(isCancelable);

        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.frameAlert, this.fragment, TAG).commit();
        return view;
    }


    public void dismiss(Context context) {
        setDismiss((AppCompatActivity) context);
    }


    public void showAlert(AppCompatActivity activity, Fragment fragment, boolean isCancalable) {
        try {
            setDismiss(activity);
            FragmentManager fm = activity.getSupportFragmentManager();
            AlertDialogFragment dialog = AlertDialogFragment.newInstance(fragment, isCancalable);
            dialog.show(fm, AlertDialogFragment.TAG);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    public static void setDismiss(AppCompatActivity activity) {
        try {
            Fragment prev = activity.getSupportFragmentManager().findFragmentByTag(AlertDialogFragment.TAG);
            if (prev != null) {
                DialogFragment df = (DialogFragment) prev;
                df.dismiss();
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }


}
