package com.abhimanyusharma.admobapplication;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class InterstitialActivity extends AppCompatActivity {

    private Button showInterstitialAds;
    private InterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interstitial);

        // Create the next level button, which tries to show an interstitial when clicked.
        showInterstitialAds = ((Button) findViewById(R.id.showInterstitialAds));

        showInterstitialAds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "BUTTON CLICKED", Toast.LENGTH_SHORT).show();
                showInterstitial();
            }
        });

        //SETTING UP INTERSTITIAL AD

        // Create the InterstitialAd and set the adUnitId (defined in values/strings.xml).
        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                Toast.makeText(getApplicationContext(), "AUTO: AD LOADED", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {

                Toast.makeText(getApplicationContext(), "AUTO: AD FAILED LOAD", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdClosed() {
                // Proceed to the next level.
                Toast.makeText(getApplicationContext(), "AUTO: AD CLOSED", Toast.LENGTH_SHORT).show();
                //startActivity(new Intent(getApplicationContext(), BannerActivity.class));
                finish();
            }
        });

        //LOADING INTERSTITIAL AD
        //loadInterstitial();{}

        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        interstitialAd.loadAd(adRequest);

        //SHOWING INTERSTITIAL AD

        if (interstitialAd != null && interstitialAd.isLoaded()) {
            interstitialAd.show();
        } else {
            Toast.makeText(this, "Create SHOW:Ad did not load", Toast.LENGTH_SHORT).show();
        }



        // Toasts the test ad message on the screen. Remove this after defining your own ad unit ID.
        //Toast.makeText(this, TOAST_TEXT, Toast.LENGTH_LONG).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showInterstitial() {
        // Show the ad if it's ready. Otherwise toast and reload the ad.
        if (interstitialAd != null && interstitialAd.isLoaded()) {
            interstitialAd.show();
        } else {
            Toast.makeText(getApplicationContext(), "CLICKED: AD NOT LOADED SENDING TO DESTINATION", Toast.LENGTH_SHORT).show();
            //startActivity(new Intent(getApplicationContext(), BannerActivity.class));
            finish();
        }
    }
}