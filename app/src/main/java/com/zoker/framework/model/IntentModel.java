package com.zoker.framework.model;

import android.content.Intent;

/**
 * Created by Administrator on 2017/4/21.
 */

public class IntentModel {
    String title;
    Intent intent;

    public IntentModel(String title, Intent intent) {
        this.title = title;
        this.intent = intent;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Intent getIntent() {
        return intent;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }
}
