package com.example.lab2;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements RecyclerViewInterface {
    private PhoneViewModel mPhoneViewModel;
    PhoneListAdapter adapter;
    private ActivityResultLauncher<Intent> mNewPhoneActivityResultLauncher = registerForActivityResult
            (new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent resultIntent = result.getData();
                        Bundle bundle = resultIntent.getExtras();
                        String producer = bundle.getString("producer");
                        String model = bundle.getString("model");
                        String version = bundle.getString("version");
                        String web_site = bundle.getString("web_site");
                        Phone phone = new Phone(producer, model, version, web_site);
                        mPhoneViewModel.insert(phone);
                    }
                }
            }
    );
    private ActivityResultLauncher<Intent> mUpdatePhoneActivityResultLauncher = registerForActivityResult
            (new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult result) {
                            if (result.getResultCode() == RESULT_OK) {
                                Intent resultIntent = result.getData();
                                Bundle bundle = resultIntent.getExtras();
                                int id = bundle.getInt("id");
                                String producer = bundle.getString("producer");
                                String model = bundle.getString("model");
                                String version = bundle.getString("version");
                                String web_site = bundle.getString("web_site");
                                Phone phone = new Phone(id,producer, model, version, web_site);
                                mPhoneViewModel.update(phone);
                            }
                        }
                    }
            );
    FloatingActionButton fab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        adapter = new PhoneListAdapter(new PhoneListAdapter.PhoneDiff(), this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mPhoneViewModel = new ViewModelProvider(this).get(PhoneViewModel.class);

        mPhoneViewModel.getAllPhones().observe(this, phones -> {
            adapter.submitList(phones);
        });

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                mPhoneViewModel.delete(adapter.getPhoneAtPosition(position).getID());
            }
        });

        itemTouchHelper.attachToRecyclerView(recyclerView);

        fab = findViewById(R.id.fab);
        fab.setOnClickListener( view -> {
            Intent intent = new Intent(MainActivity.this, NewPhoneActivity.class);
            mNewPhoneActivityResultLauncher.launch(intent);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.clear_data) {
            mPhoneViewModel.deleteAll();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClicked(int position) {
        Phone phone = adapter.getPhoneAtPosition(position);
        Intent intent = new Intent(MainActivity.this, UpdatePhoneActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("id", phone.getID());
        bundle.putString("producer", phone.getProducer());
        bundle.putString("model", phone.getModel());
        bundle.putString("version", phone.getAndroidVersion());
        bundle.putString("web_site", phone.getWebsite());
        intent.putExtras(bundle);
        mUpdatePhoneActivityResultLauncher.launch(intent);
    }


}