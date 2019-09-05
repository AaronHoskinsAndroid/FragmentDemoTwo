package examples.aaronhoskins.com.fragmentdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity
        implements BlueFragment.OnFragmentInteractionListener {
    RedFragment redFragment;
    FragmentManager fragmentManager;
    BlueFragment blueFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        redFragment = (RedFragment)fragmentManager.findFragmentById(R.id.fgRedFragment);
        blueFragment = BlueFragment.newInstance("Some info on init");

        //To dynamically add fragment, use the fragment manager to begin a fragment
        //     transaction, then commit the transaction
        fragmentManager
                .beginTransaction()
                .add(R.id.frmPlaceOne, blueFragment)
                .commit();
    }

    @Override
    public void onSendData(String dataToSendToActivity) {
        Log.d("TAG", dataToSendToActivity);
    }
}
