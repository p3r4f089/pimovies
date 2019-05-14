package com.redpill.perafo.pimovies.ui.main;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.redpill.perafo.pimovies.R;
import com.redpill.perafo.pimovies.ui.FavoritesFrag.FavoritesFrag;
import com.redpill.perafo.pimovies.ui.search.SearchFrag;

public class MainAct extends AppCompatActivity implements
        BottomNavigationView.OnNavigationItemSelectedListener,
        MainView.HomeView {

    private static final String TAG = "MainAct";

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_act);

        bottomNavigationView = findViewById(R.id.navigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        goToMain();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.navigation_home:
                goToMain();
                break;
            case R.id.navigation_search:
                goToSearch();
                break;
            case R.id.navigation_favorites:
                goToFavorites();
                break;
        }
        return false;
    }

    @Override
    public void goToMain() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, MainFrag.newInstance(), "MainFragment")
                .commit();
    }

    @Override
    public void goToSearch() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, SearchFrag.newInstance(), "SearchFragment")
                .commit();
    }

    @Override
    public void goToFavorites() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, FavoritesFrag.newInstance(), "FavoritesFragment")
                .commit();
    }
}
