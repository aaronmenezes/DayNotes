package com.kyser.daynotes.ui;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.kyser.daynotes.R;
import com.kyser.daynotes.databinding.ActivityMainBinding;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.View;

import java.util.function.LongFunction;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
        mainBinding.fab.setOnClickListener(view ->   getNavController().navigate(R.id.action_noteGrid_to_addNote));
    }


    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        getNavController().addOnDestinationChangedListener((controller, destination, arguments) ->updateUiElements(controller, destination, arguments));
    }

    private void updateUiElements(NavController controller, NavDestination destination, Bundle arguments) {
        if(destination.getId() == R.id.noteView){
            mainBinding.fab.setVisibility(View.GONE);
        }else if(destination.getId() == R.id.noteGrid){
            mainBinding.fab.setVisibility(View.VISIBLE);
        }else if(destination.getId() == R.id.addNote){
            mainBinding.fab.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainBinding = null;
    }

    public NavController getNavController() {
        return Navigation.findNavController(mainBinding.navHostFragment);
    }

}