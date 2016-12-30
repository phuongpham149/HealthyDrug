package com.example.phuong.healthydrug.activities;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.phuong.healthydrug.R;
import com.example.phuong.healthydrug.adapters.DrawerAdapter;
import com.example.phuong.healthydrug.fragments.ProvicesFragment;
import com.example.phuong.healthydrug.fragments.ProvicesFragment_;
import com.example.phuong.healthydrug.models.DrawerItem;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity {
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
        mDrawerAdapter = new DrawerAdapter(mDrawerItems, this);
        mRecyclerView.setAdapter(mDrawerAdapter);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("        Hospital");
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
    }

    public void dataMenu() {
        mDrawerItems = new ArrayList<>();
        mDrawerItems.add(new DrawerItem("Hospital", R.drawable.ic_hospital));
        mDrawerItems.add(new DrawerItem("Medicine", R.drawable.ic_pill));
        mDrawerItems.add(new DrawerItem("Feedback", R.drawable.ic_feedback));
        mDrawerItems.add(new DrawerItem("Setting", R.drawable.ic_setting));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.menu, menu);
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

    public void SelectItem(int possition) {
        switch (possition) {
            case 0:
                ProvicesFragment provicesFragment = ProvicesFragment_.builder().build();
                getSupportFragmentManager().beginTransaction().replace(R.id.rootView, provicesFragment).commit();
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            default:
                break;
        }
        mDrawerLayout.closeDrawer(mRecyclerView);

    }
}
