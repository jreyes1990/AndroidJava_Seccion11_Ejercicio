package com.example.seccion11_ejercicio.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.example.seccion11_ejercicio.Fragments.AlertFragment;
import com.example.seccion11_ejercicio.Fragments.EmailFragment;
import com.example.seccion11_ejercicio.Fragments.InfoFragment;
import com.example.seccion11_ejercicio.R;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, CompoundButton.OnCheckedChangeListener {
  private DrawerLayout drawerLayout;
  private NavigationView navigationView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    setToolbar();

    drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
    navigationView = (NavigationView) findViewById(R.id.navView);

    setFragmentByDefault();

    navigationView.setNavigationItemSelectedListener(this);

    Switch switchBtn = (Switch) navigationView.getMenu().findItem(R.id.switch_in_nav_options).getActionView();
    switchBtn.setOnCheckedChangeListener(this);
  }

  private void setToolbar() {
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_home);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
  }

  private void changeFragment(Fragment fragment, MenuItem item) {
    getSupportFragmentManager()
            .beginTransaction()
            .replace(R.id.content_frame, fragment)
            .commit();
    item.setChecked(true);
    getSupportActionBar().setTitle(item.getTitle());
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {

    switch (item.getItemId()) {
      case android.R.id.home:
        drawerLayout.openDrawer(GravityCompat.START);
        return true;
    }
    return super.onOptionsItemSelected(item);
  }

  private void setFragmentByDefault() {
    changeFragment(new EmailFragment(), navigationView.getMenu().getItem(0));
  }

  @Override
  public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
    if (checked) {
      Toast.makeText(MainActivity.this, "The option is checked", Toast.LENGTH_SHORT).show();
    } else {
      Toast.makeText(MainActivity.this, "The option is unchecked", Toast.LENGTH_SHORT).show();
    }
  }

  @Override
  public boolean onNavigationItemSelected(@NonNull MenuItem item) {
    boolean fragmentTransaction = false;
    Fragment fragment = null;


    switch (item.getItemId()) {

      case R.id.menu_mail:
        fragment = new EmailFragment();
        fragmentTransaction = true;
        break;
      case R.id.menu_alert:
        fragment = new AlertFragment();
        fragmentTransaction = true;
        break;
      case R.id.menu_info:
        fragment = new InfoFragment();
        fragmentTransaction = true;
        break;
    }


    if (fragmentTransaction) {
      changeFragment(fragment, item);
      drawerLayout.closeDrawers();
    }
    return true;
  }
}