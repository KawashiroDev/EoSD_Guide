package com.h9.user.EoSD_Guide;

/**import everything*/
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private FrameLayout frameLayout;
    private NavigationView navigationView;
    private SwitchCompat darkModeSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**Set default night mode state*/
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_main);

        initializeViews();
        toggleDrawer();
        initializeDefaultFragment(savedInstanceState,0);
        setDarkModeSwitchListener();

    }



    /**
     * Initialize all widgets
     */
    private void initializeViews() {
		/** Find toolbar id (the top part of the app)*/
        toolbar = findViewById(R.id.toolbar_id);
		/** Set the title to what the string "toolbar_title" is defined as*/
        toolbar.setTitle(R.string.toolbar_title);
        setSupportActionBar(toolbar);
		/**Find the drawer layout id*/
        drawerLayout = findViewById(R.id.drawer_layout_id);
        frameLayout = findViewById(R.id.framelayout_id);
        navigationView = findViewById(R.id.navigationview_id);
        navigationView.setNavigationItemSelectedListener(this);
		/*Define what the dark mode switch does*/
        darkModeSwitch = (SwitchCompat)navigationView.getMenu().findItem(R.id.nav_darkmode_id).getActionView();
    }

    /**
     * Checks if the savedInstanceState is null - onCreate() is ran
     * If so, display fragment of navigation drawer menu at position itemIndex and
     * set checked status as true
     * @param savedInstanceState
     * @param itemIndex
     */
    private void initializeDefaultFragment(Bundle savedInstanceState, int itemIndex){
        if (savedInstanceState == null){
            MenuItem menuItem = navigationView.getMenu().getItem(itemIndex).setChecked(true);
            onNavigationItemSelected(menuItem);
        }
    }

    /**
     * Creates an instance of the ActionBarDrawerToggle class:
     * 1) Handles opening and closing the navigation drawer
     * 2) Creates a hamburger icon in the toolbar
     * 3) Attaches listener to open/close drawer on icon clicked and rotates the icon
     */
    private void toggleDrawer() {
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }

    @Override
    //called when back button is pressed
    public void onBackPressed() {
        //Checks if the navigation drawer is open -- If so, close it
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        // If drawer is already close -- Do not override original functionality
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        //define all fragment id's from nav_drawer_menu.xml here for them to work
        switch (menuItem.getItemId()){
            case R.id.nav_message_id:
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_id, new MessageFragment())
                        .commit();
                closeDrawer();
                break;
            case R.id.nav_test_id:
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_id, new TestFragment())
                        .commit();
                closeDrawer();
                break;
            case R.id.nav_stage1_id:
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_id, new Stage1Fragment())
                        .commit();
                closeDrawer();
                break;
            case R.id.nav_stage2_id:
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_id, new Stage2Fragment())
                        .commit();
                closeDrawer();
                break;
            case R.id.nav_stage3_id:
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_id, new Stage3Fragment())
                        .commit();
                closeDrawer();
                break;
            case R.id.nav_stage4_id:
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_id, new Stage4Fragment())
                        .commit();
                closeDrawer();
                break;
            case R.id.nav_stage5_id:
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_id, new Stage5Fragment())
                        .commit();
                closeDrawer();
                break;
            case R.id.nav_stage6_id:
                Toast.makeText(this, "Stage 6", Toast.LENGTH_SHORT).show();
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_id, new Stage6Fragment())
                        .commit();
                closeDrawer();
                break;
            case R.id.nav_about_id:
                Toast.makeText(this, "Application info", Toast.LENGTH_SHORT).show();
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_id, new AboutFragment())
                        .commit();
                closeDrawer();
                break;
        }
        return true;
    }

    /**
     * Attach setOnCheckedChangeListener to the dark mode switch
	 If the user toggles the switch then run the code in this void
     */
    private void setDarkModeSwitchListener(){
        darkModeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                /**Act depending on what state the switch is (on or off)
                 * If switch is checked then run code below
                 * */
                if (!isChecked){
                    /**Display a toast notification to the user*/
                    Toast.makeText(MainActivity.this, "Disabled dark mode", Toast.LENGTH_SHORT).show();

                    /**Disable androids night mode theme*/
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    /**Apply light mode without needing to restart*/
                    getDelegate().applyDayNight();
                }
                /**if the above isn't valid (switch not on) then run the code here*/
                else {
                    /**Display a different toast notification to the user*/
                    Toast.makeText(MainActivity.this, "Enabled dark mode", Toast.LENGTH_SHORT).show();
                    /**Enable androids night mode theme*/
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    /**Apply dark mode without needing to restart*/
                    getDelegate().applyDayNight();
                }
            }
        });
    }

    /**
     * Checks if the navigation drawer is open - if so, close it
     */
    private void closeDrawer(){
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }
    
    /**
     * Iterates through all the items in the navigation menu and deselects them:
     * removes the selection color
     */
    private void deSelectCheckedState(){
        int noOfItems = navigationView.getMenu().size();
        for (int i=0; i<noOfItems;i++){
            navigationView.getMenu().getItem(i).setChecked(false);
        }

    }

}
