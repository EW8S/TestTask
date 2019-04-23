package ew8s.com.myapplication;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import ew8s.com.myapplication.adapters.RecyclerAdapter;
import ew8s.com.myapplication.models.ModelCar;
import ew8s.com.myapplication.viewmodels.MainActivityViewModel;

/**
 * Created by Aliaksandr Kisel on 02.02.2019.
 */

public class MainActivity extends AppCompatActivity  implements Observer<String>{

    private FloatingActionButton mFab;
    private RecyclerView mRecyclerView;
    private RecyclerAdapter mAdapter;
    private MainActivityViewModel mMainActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mFab = findViewById(R.id.fab);
        mRecyclerView = findViewById(R.id.recycler_view);

        mMainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        mMainActivityViewModel.init();

        mMainActivityViewModel.getModelCar().observe(this, new Observer<List<ModelCar>>() {
            @Override
            public void onChanged(@Nullable List<ModelCar> cars) {
                mAdapter.notifyDataSetChanged();
            }
        });

        mMainActivityViewModel.getUpdate().observe(this, new Observer<Boolean>() {
                    @Override
                    public void onChanged(@Nullable Boolean aBoolean) {
                        mRecyclerView.smoothScrollToPosition(mMainActivityViewModel.getModelCar().getValue().size()-1);
                    }
                }
        );

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMainActivityViewModel.addNewValue(MainActivity.this, "");
            }
        });

        setUpItemTouchHelper();
        initRecyclerView();
    }

    private void setUpItemTouchHelper() {
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                int swipedPosition = viewHolder.getAdapterPosition();
                mMainActivityViewModel.remove(swipedPosition);
            }

        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    private void initRecyclerView(){
        mAdapter = new RecyclerAdapter(this, mMainActivityViewModel.getModelCar().getValue());
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setObserver(this);
    }

    @Override
    public void onChanged(@Nullable String s) {
        mMainActivityViewModel.addNewValue(MainActivity.this, s);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings_price) {
            mMainActivityViewModel.sort(0);
            return true;
        }

        if (id == R.id.action_settings_manufacture) {
            mMainActivityViewModel.sort(1);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
