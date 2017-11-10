package com.example.blooddonar;

import com.example.blooddonar.fragments.BloodBankSearchFragment;
import com.example.blooddonar.fragments.FeedbackFragment;
import com.example.blooddonar.fragments.HospitalSearchFragment;
import com.example.blooddonar.fragments.SearchFragment;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

/*
 * Class for drawer layout
 */

public class Home extends AppCompatActivity {

	private Toolbar mToolbar;
	private DrawerLayout mDrawerLayout;
	NavigationView mNavigationView;
	FrameLayout mContentFrame;
	ActionBarDrawerToggle mDrawerToggle;
	MenuItem mPreviousMenuItem = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		initialize();

		setUpToolbar();
		setUpNavDrawer();
		callToInitialFragment(savedInstanceState);

		/*
		 * Listener for Navigation item on click
		 */

		mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
			@Override
			public boolean onNavigationItemSelected(MenuItem menuItem) {
				mNavigationView.getMenu().getItem(0).setChecked(false);
				mNavigationView.getMenu().getItem(0).setCheckable(false);
				menuItem.setCheckable(true);
				menuItem.setChecked(true);
				if (mPreviousMenuItem != null) {
					mPreviousMenuItem.setChecked(false);
				}
				mPreviousMenuItem = menuItem;
				selectDrawerItem(menuItem);
				return true;
			}
		});


		ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,
				mDrawerLayout,
				mToolbar,
				R.string.app_name,
				R.string.app_name){

			@SuppressLint("NewApi")
			@Override
			public void onDrawerClosed(View drawerView) {

				super.onDrawerClosed(drawerView);
			}

			@SuppressLint("NewApi")
			@Override
			public void onDrawerOpened(View drawerView) {

				//To set statusBar color
				if(Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT)
				{
					Window window = Home.this.getWindow();
					window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
					window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
					window.setStatusBarColor(Home.this.getResources().getColor(R.color.transparent));
				}

				super.onDrawerOpened(drawerView);
			}
		};

		mDrawerLayout.setDrawerListener(actionBarDrawerToggle);
		actionBarDrawerToggle.syncState();

	}


	/**
	 * @param savedInstanceState 
	 * 
	 */
	private void callToInitialFragment(Bundle savedInstanceState) {
		if (savedInstanceState == null) {
			mNavigationView.getMenu().getItem(0).setChecked(true);
			mNavigationView.getMenu().getItem(0).setCheckable(true);
			getSupportFragmentManager().beginTransaction().replace(R.id.nav_contentframe, new SearchFragment() ).commit();
		}

	}

	/**
	 * Method to initialize views
	 */
	private void initialize() {
		mDrawerLayout = (DrawerLayout) findViewById(R.id.nav_drawer);
		mNavigationView = (NavigationView) findViewById(R.id.nav_view);
		mContentFrame = (FrameLayout) findViewById(R.id.nav_contentframe);

	}

	protected void selectDrawerItem(MenuItem menuItem) {
		Fragment fragment = null;
		@SuppressWarnings("rawtypes")
		Class fragmentClass = null;

		switch(menuItem.getItemId()) {

		case R.id.menu_search:
			fragmentClass = SearchFragment.class;
			break;

		case R.id.menu_feedback:
			fragmentClass = FeedbackFragment.class;
			break;
			
		case R.id.menu_hospital:
			fragmentClass = HospitalSearchFragment.class;
			break;
			
		case R.id.menu_bloodbank:
			fragmentClass = BloodBankSearchFragment.class;
			break;



		}

		try {
			fragment = (Fragment) fragmentClass.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}

		FragmentManager fragmentManager = this.getSupportFragmentManager();
		fragmentManager.beginTransaction().replace(R.id.nav_contentframe, fragment).commit();

		menuItem.setChecked(true);
		setTitle(menuItem.getTitle());
		mDrawerLayout.closeDrawers();

	}

	private void setUpToolbar() {
		mToolbar = (Toolbar) findViewById(R.id.toolbar);
		if (mToolbar != null) {
			setSupportActionBar(mToolbar);
		}
	}

	private void setUpNavDrawer() {

		if (mToolbar != null) {
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
			getSupportActionBar().setDisplayShowHomeEnabled(true);
			getSupportActionBar().setHomeButtonEnabled(true);
			getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
			mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					mDrawerLayout.openDrawer(GravityCompat.START);
				}
			});
		}

	}

}