package com.cartenz.component_design;

import android.view.View;

public abstract class setOnClick {


    public setOnClick() {
    }

    public setOnClick(View view) {
        click(view);

    }

    public setOnClick(View[] views) {
        for (View view : views) {
            click(view);
        }
    }

    public void click(View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doClick(view);
            }
        });
    }

    public abstract void doClick(View view);

}
