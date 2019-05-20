package johannt.carpool_2.Login_Phase;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

import johannt.carpool_2.Profile_Features.ProfileActivity;
import johannt.carpool_2.R;
import johannt.carpool_2.Rides_And_Validator.ResultActivity;
import johannt.carpool_2.Users.User;

public class MainActivity extends AppCompatActivity  {

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    private FirebaseAuth firebaseAuth;
    private String src, dst;
    protected LocationManager locationManager;
    protected LocationListener listener;
    protected Context context;
    protected String latitude,longitude, loc;
    protected boolean gps_enabled,network_enabled;
    private FirebaseUser user;
    public static User secondUser;
    private DatabaseReference firebaseDatabaseUsers;
    private FirebaseDatabase databaseCarPool;
    private String city,university;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MenuItem Useritem = findViewById(R.id.NameAccount);
        //initializing firebase authentication object

        firebaseAuth = FirebaseAuth.getInstance();
        //getting current user
        user = firebaseAuth.getCurrentUser();
    }

    @Override
    protected void onStart(){
        super.onStart();



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

                    city = "Netanya";
                    university = "Ariel U";

                    Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                    intent.putExtra("date", currDate);
                    intent.putExtra("endTime", "");
                    intent.putExtra("startTime", "");
                    intent.putExtra("price", "");
                    intent.putExtra("src", city);
                    intent.putExtra("dst", university);
                    startActivity(intent);

                }
                MainActivity.this.finish();
            }
        }, 1500);

    }

//    public void SetCity_University(final MyCallback myCallback) {
//        // /getting firebase auth object
//
//        databaseCarPool = FirebaseDatabase.getInstance();
//        firebaseDatabaseUsers = databaseCarPool.getReference("Users");
//
//        secondUser = new User();
//        firebaseDatabaseUsers.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
//                    secondUser = userSnapshot.getValue(User.class);
//                    if (user.getUid().equals(secondUser.getUID())) {
//                        //displaying logged in user name
//                        myCallback.onCallback(secondUser);
//                        break;
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                // Failed to read value
//                Log.w("Failed to read value.", databaseError.toException());
//
//            }
//        });
//    }
//
//    public interface MyCallback {
//        void onCallback(User value);
//    }


}


