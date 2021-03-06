package ru.yamblz.weather.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import ru.yamblz.weather.App;
import ru.yamblz.weather.di.component.ActivityComponent;
import ru.yamblz.weather.di.component.DaggerActivityComponent;
import ru.yamblz.weather.di.module.ActivityModule;


public class BaseActivity extends AppCompatActivity {

    private ActivityComponent activityComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .appComponent(((App) getApplication()).getAppComponent())
                .build();
    }

    protected void replaceFragment(int containerId, Fragment fragment, boolean addToBackStack) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (addToBackStack) {
            ft.replace(containerId, fragment).addToBackStack(null);
        } else {
            ft.replace(containerId, fragment);
        }
        ft.commit();
    }

    public ActivityComponent getActivityComponent() {
        return activityComponent;
    }
}
