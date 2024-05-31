package com.gazi.ozan.messenger;


import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.gazi.ozan.messenger.Adapter.FragmentAdapter;
import com.gazi.ozan.messenger.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().setTitle("Gazi Chat");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.geceMavisi)));

        mAuth = FirebaseAuth.getInstance();

        binding.viewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager()));
        binding.tabLayout.setupWithViewPager(binding.viewPager);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.settings) {

            //Toast.makeText(this, "Settings is clicked.", Toast.LENGTH_SHORT).show();
            Intent intent2 = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent2);

        }

        if (item.getItemId() == R.id.groupChat) {

            //Toast.makeText(this, "Group Chat is started.", Toast.LENGTH_SHORT).show();
            Intent intent1 = new Intent(MainActivity.this, GroupChatActivity.class);
            startActivity(intent1);

        }

        if (item.getItemId() == R.id.logout) {

            mAuth.signOut();
            Intent intent = new Intent(MainActivity.this, SignInActivity.class);
            startActivity(intent);

        }

        return super.onOptionsItemSelected(item);
    }
}