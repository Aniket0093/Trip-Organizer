package com.github.aniketc.android.ui.trips;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.github.aniketc.android.R;
import com.github.aniketc.android.ui.TabFragmentsActivity;
import com.github.aniketc.android.ui.navigation.ViewPagerAdapter;

public class TripsActivity extends TabFragmentsActivity {

    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, TripsActivity.class);
        return intent;
    }

    @Override
    protected void onAuthStateSignIn() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected ViewPagerAdapter createViewPagerAdapter() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(UpcomingFragment.newInstance(), getResources().getString(R.string.tab_trips_upcoming));

        return adapter;
    }

    @Override
    public void onResume() {
        super.onResume();
        this.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }


    @Override
    public void onPause() {
        super.onPause();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
