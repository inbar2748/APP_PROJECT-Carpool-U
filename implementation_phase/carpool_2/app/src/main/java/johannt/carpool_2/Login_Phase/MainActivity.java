package johannt.carpool_2.Login_Phase;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import johannt.carpool_2.Profile_Features.FindRideActivity;
import johannt.carpool_2.Profile_Features.ProfileActivity;
import johannt.carpool_2.Profile_Features.PublishActivity;
import johannt.carpool_2.R;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        MenuItem Useritem =  findViewById(R.id.NameAccount);
        //initializing firebase authentication object
        firebaseAuth = FirebaseAuth.getInstance();
        //getting current user
        FirebaseUser user = firebaseAuth.getCurrentUser();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //closing this activity and opening signin activity.

        finish();
        startActivity(new Intent(this, ProfileActivity.class));

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.find_carpool) {
            startActivity(new Intent(this, FindRideActivity.class));

        } else if (id == R.id.post_carpool) {
            startActivity(new Intent(this, PublishActivity.class));

        } else if (id == R.id.results) {
        //    startActivity(new Intent(this, SignInActivity.class));

        } else if (id == R.id.posts) {
           startActivity(new Intent(this, PublishActivity.class));

        } else if (id == R.id.profile) {
            startActivity(new Intent(MainActivity.this, ProfileActivity.class));

        } else if (id == R.id.signOut) {
            //logging out the user
            firebaseAuth.signOut();
        Intent signin = new Intent(MainActivity.this, SignInActivity.class);
        startActivity(signin);
    }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
