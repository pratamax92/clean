package com.cartenz.component_design;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;


public class ViewHelper {


    //start edittext
    public static boolean editTextIsEmpty(EditText editText) {
        return !editTextIsNotEmpty(editText, null);
    }

    public static boolean editTextIsEmpty(EditText editText, String error) {
        return !editTextIsNotEmpty(editText, error);
    }

    public static boolean editTextIsNotEmpty(EditText editText) {
        return editTextIsNotEmpty(editText, null);
    }

    public static boolean editTextIsNotEmpty(EditText editText, String error) {
        if (editText != null) {
            editText.setError(null);
            if (TextUtils.isEmpty(editText.getText())) {
                if (!TextUtils.isEmpty(error)) {
                    editText.setError(error);
                }
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    //end edittext

    //start spinner
    public static boolean spinnerIsEmpty(Spinner spinner, int notSelectedPosition) {
        return !spinnerIsNotEmpty(spinner, notSelectedPosition, null, null);
    }

    public static boolean spinnerIsEmpty(Spinner spinner, int notSelectedPosition, TextView textError, String error) {
        return !spinnerIsNotEmpty(spinner, notSelectedPosition, textError, error);
    }

    public static boolean spinnerIsNotEmpty(Spinner spinner, int notSelectedPosition) {
        return spinnerIsNotEmpty(spinner, notSelectedPosition, null, null);
    }

    public static boolean spinnerIsNotEmpty(Spinner spinner, int notSelectedPosition, TextView textError, String error) {
        if (spinner != null) {
            if (textError != null) {
                textError.setError(null);
            }
            if (spinner.getSelectedItemPosition() == notSelectedPosition) {
                if (textError != null) {
                    if (!TextUtils.isEmpty(error)) {
                        textError.setError(error);
                    }
                }
                return false;
            } else {
                return true;
            }
        }
        return false;
    }
//end spinner

    public static void disableAllView(ViewGroup viewGroup) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View view = viewGroup.getChildAt(i);
            if (view instanceof TextView || view instanceof Spinner) {
                ViewHelper.disableView(view);
            } else if (view instanceof LinearLayout || view instanceof RelativeLayout) {
                disableAllView((ViewGroup) view);
            }
        }
    }

    public static void enableAllView(ViewGroup viewGroup) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View view = viewGroup.getChildAt(i);
            if (view instanceof TextView || view instanceof Spinner) {
                ViewHelper.enableView(view);
            } else if (view instanceof LinearLayout || view instanceof RelativeLayout) {
                enableAllView((ViewGroup) view);
            }
        }
    }

    public static void disableView(View view) {
        if (view instanceof TextView) {
            TextView text = (TextView) view;
            text.setFocusable(false);
            text.setFocusableInTouchMode(false);
//                text.setEnabled(false);
        } else if (view instanceof Spinner) {
            Spinner spinner = (Spinner) view;
            if (spinner.getAdapter() != null) {
                spinner.setEnabled(false);
                spinner.setClickable(false);
            }

        }
    }

    public static void enableView(View view) {
        if (view instanceof TextView) {
            TextView text = (TextView) view;
            text.setFocusable(true);
            text.setFocusableInTouchMode(true);
            text.setEnabled(true);
        } else if (view instanceof Spinner) {

            Spinner spinner = (Spinner) view;
            if (spinner.getAdapter() != null) {
                spinner.setEnabled(true);
                spinner.setClickable(true);
            }

        }
    }

}
