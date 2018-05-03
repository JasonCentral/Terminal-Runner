package com.visionvow.terminal_run.listeners;

import android.content.Intent;
import android.view.View;

import com.visionvow.terminal_run.GameActivity;
import com.visionvow.terminal_run.MainActivity;

public class ChangeScreenListener implements View.OnClickListener {
    private int screenToChange;

    /**
     * Creates a change screen listener to change to some screen
     *
     * @param screenToChange  1 for main screen, 2 for game screen, ... to be cont.
     */
    public ChangeScreenListener(int screenToChange) {
        this.screenToChange = screenToChange;
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (screenToChange) {
            case 1:
                intent = new Intent(v.getContext(), MainActivity.class);
                break;
            case 2:
                intent = new Intent(v.getContext(), GameActivity.class);
                break;
            default:
                intent = new Intent();
        }
        v.getContext().startActivity(intent);
    }
}
