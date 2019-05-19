package johannt.carpool_2.Login_Phase;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.SimpleDateFormat;
import java.util.Date;

import johannt.carpool_2.Profile_Features.ProfileActivity;
import johannt.carpool_2.R;
import johannt.carpool_2.Rides_And_Validator.ResultActivity;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private String src, dst;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MenuItem Useritem = findViewById(R.id.NameAccount);
        //initializing firebase authentication object
        firebaseAuth = FirebaseAuth.getInstance();
        //getting current user
        final FirebaseUser user = firebaseAuth.getCurrentUser();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (user == null) {
                    //closing this activity and opening signin activity.
                    startActivity(new Intent(MainActivity.this, SignInActivity.class));
                } else {

                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    Date date = new Date();
                    String currDate = formatter.format(date);
                    if(currDate.charAt(0) == '0' && currDate.charAt(3) == '0'){
                        formatter.applyPattern("d/M/yyyy");
                        currDate = formatter.format(date);
                    }
                    else if(currDate.charAt(0) == '0'){
                        formatter.applyPattern("d/MM/yyyy");
                        currDate = formatter.format(date);
                    }
                    else{
                        formatter.applyPattern("dd/M/yyyy");
                        currDate = formatter.format(date);

                    }

                    Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                    intent.putExtra("date", currDate);
                    intent.putExtra("endTime", "");
                    intent.putExtra("startTime", "");
                    intent.putExtra("price", "");
                    intent.putExtra("src", ProfileActivity.university);
                    intent.putExtra("dst", ProfileActivity.city);
                    startActivity(intent);
                    startActivity(new Intent(MainActivity.this, ResultActivity.class));
                }
                MainActivity.this.finish();
            }
        }, 1500);
    }
}



//    @SuppressWarnings("StatementWithEmptyBody")
//    @Override
//    public boolean onNavigationItemSelected(MenuItem item) {
//        // Handle navigation view item clicks here.
//        int id = item.getItemId();
//
//        if (id == R.id.find_carpool) {
//            startActivity(new Intent(this, FindRideActivity.class));
//
//        } else if (id == R.id.post_carpool) {
//            startActivity(new Intent(this, PublishActivity.class));
//
//        } else if (id == R.id.results) {
//        //    startActivity(new Intent(this, SignInActivity.class));
//
//        } else if (id == R.id.posts) {
//           startActivity(new Intent(this, PublishActivity.class));
//
//        } else if (id == R.id.profile) {
//            startActivity(new Intent(MainActivity.this, ProfileActivity.class));
//
//        } else if (id == R.id.signOut) {
//            //logging out the user
//            firebaseAuth.signOut();
//        Intent signin = new Intent(MainActivity.this, SignInActivity.class);
//        startActivity(signin);
//    }
//
//        DrawerLayout drawer = findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
//        return true;
//    }
