package com.example.phuong.healthydrug.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.phuong.healthydrug.R;
import com.example.phuong.healthydrug.adapters.DrawerAdapter;
import com.example.phuong.healthydrug.fragments.FeedbackFragment;
import com.example.phuong.healthydrug.fragments.FeedbackFragment_;
import com.example.phuong.healthydrug.fragments.ProvicesFragment;
import com.example.phuong.healthydrug.fragments.ProvicesFragment_;
import com.example.phuong.healthydrug.fragments.SettingFragment;
import com.example.phuong.healthydrug.fragments.SettingFragment_;
import com.example.phuong.healthydrug.listeners.OnClickItemMenuListener;
import com.example.phuong.healthydrug.models.DrawerItem;
import com.example.phuong.healthydrug.services.RemindService;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity implements OnClickItemMenuListener {
    List<DrawerItem> mDrawerItems;
    @ViewById(R.id.recyclerViewMenuDrawer)
    RecyclerView mRecyclerView;
    @ViewById(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @ViewById(R.id.toolbar)
    Toolbar mToolbar;

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerAdapter mDrawerAdapter;


    @Override
    public void inits() {
        dataMenu();
        mDrawerLayout.setDrawerShadow(R.drawable.ic_menu,
                GravityCompat.START);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mDrawerAdapter = new DrawerAdapter(mDrawerItems, this, this);
        mRecyclerView.setAdapter(mDrawerAdapter);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(R.string.title_action_bar_hospital));
        getSupportActionBar().setIcon(R.drawable.ic_hospital);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close) {
            public void onDrawerClosed(View view) {
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        SelectItem(0);

        Intent intent = new Intent(this, RemindService.class);
        startService(intent);

    }

    public void dataMenu() {
        mDrawerItems = new ArrayList<>();
        String[] items = getResources().getStringArray(R.array.drawer_array);
        mDrawerItems.add(new DrawerItem(items[0], R.drawable.ic_hospital));
        mDrawerItems.add(new DrawerItem(items[1], R.drawable.ic_pill));
        mDrawerItems.add(new DrawerItem(items[2], R.drawable.ic_feedback));
        mDrawerItems.add(new DrawerItem(items[3], R.drawable.ic_setting));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /*switch (item.getItemId()) {
            case R.id.action_search:
                Toast.makeText(getApplication(), "abc", Toast.LENGTH_SHORT).show();
                break;
            case android.R.id.home:
                 mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }*/
        return true;
    }

    public void SelectItem(int position) {
        switch (position) {
            case 0:
                ProvicesFragment provicesFragment = ProvicesFragment_.builder().build();
                getSupportFragmentManager().beginTransaction().replace(R.id.rootView, provicesFragment).commit();
                mDrawerLayout.closeDrawer(Gravity.LEFT);
                break;
            case 1:
                break;
            case 2:
                FeedbackFragment feedbackFragment = FeedbackFragment_.builder().build();
                getSupportFragmentManager().beginTransaction().replace(R.id.rootView, feedbackFragment).commit();
                mDrawerLayout.closeDrawer(Gravity.LEFT);
                break;
            case 3:
                SettingFragment settingFragment = SettingFragment_.builder().build();
                getSupportFragmentManager().beginTransaction().replace(R.id.rootView, settingFragment).commit();
                mDrawerLayout.closeDrawer(Gravity.LEFT);
                break;
            default:
                break;
        }
    }

    @Override
    public void clickItemMenuListener(int position) {
        SelectItem(position);
    }
}
