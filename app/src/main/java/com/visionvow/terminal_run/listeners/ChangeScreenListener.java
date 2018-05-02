package com.visionvow.terminal_run.listeners;

import android.content.Intent;
import android.view.View;

public class ChangeScreenListener implements View.OnClickListener {
    private String screenToChange;

    /**
     * Creates a change screen listener to change to some screen
     *
     * @param screenToChange The xml name of the layout to change to
     */
    public ChangeScreenListener(String screenToChange) {
        this.screenToChange = screenToChange;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
    }
}
