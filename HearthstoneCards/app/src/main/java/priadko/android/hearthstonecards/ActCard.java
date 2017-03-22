package priadko.android.hearthstonecards;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import priadko.android.hearthstonecards.base.base_activity.ActivityBaseCustomResult;

/**
 * HearthstoneCards
 * Oleksandr Priadko
 */

public class ActCard extends ActivityBaseCustomResult {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
    }

    @Override
    public void onCustomActivityResult(Intent data, int requestCode, int resultCode) {

    }
}
