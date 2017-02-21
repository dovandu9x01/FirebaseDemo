package vn.com.giait.firebase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView tvmessenger, tvtype;
    Button btnSend;
    Firebase rootRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        firebasedata();
        btnSend.setOnClickListener(this);
    }

    private void firebasedata() {
        Firebase.setAndroidContext(this);
        rootRef = new Firebase("https://androidchatfirebase-2bd01.firebaseio.com/giavi");
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                tvmessenger.setText(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    private void init() {
        tvmessenger = (TextView)findViewById(R.id.tvmessenger);
        tvtype = (TextView)findViewById(R.id.edtype);
        btnSend = (Button) findViewById(R.id.btnsend);
    }

    @Override
    public void onClick(View v) {

        rootRef.setValue(tvtype.getText().toString());
    }
}
