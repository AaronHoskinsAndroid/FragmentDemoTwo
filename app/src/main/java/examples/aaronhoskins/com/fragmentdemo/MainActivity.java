package examples.aaronhoskins.com.fragmentdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity
        implements BlueFragment.OnFragmentInteractionListener,
        GreenFragment.OnFragmentInteractionListener{
    RedFragment redFragment;
    FragmentManager fragmentManager;
    BlueFragment blueFragment;
    GreenFragment greenFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        redFragment = (RedFragment)fragmentManager.findFragmentById(R.id.fgRedFragment);
        blueFragment = BlueFragment.newInstance("Some info on init");
        greenFragment = GreenFragment.newInstance();

        //To dynamically add fragment, use the fragment manager to begin a fragment
        //     transaction, then commit the transaction
        fragmentManager
                .beginTransaction()
                .add(R.id.frmPlaceOne, blueFragment)
                .addToBackStack("Blue")
                .commit();
        fragmentManager
                .beginTransaction()
                .add(R.id.frmPlaceTwo, greenFragment)
                .addToBackStack("Green")
                .commit();

        //Add a fragment and remove the fragment from the stack in the removed fragments place
        //This does a add and remove at the same time
//        fragmentManager
//                .beginTransaction()
//                .replace(R.id.frmPlaceTwo, greenFragment)
//                .addToBackStack("Green")
//                .commit();
    }

    @Override
    public void onSendData(String dataToSendToActivity) {
        Log.d("TAG", dataToSendToActivity);
        greenFragment.setMessageFromBlueFragment(dataToSendToActivity);
    }

    @Override
    public void onSendDataToBlueFragment(String message) {
        blueFragment.setTextViewValue(message);
    }

    public void onRemoveOneFragmentClicked(View view) {
        //To Remove the top fragment from the fragment backstack
        fragmentManager.popBackStack();
    }

    public void onRemoveMultiFragmentClicked(View view) {
        fragmentManager.popBackStack("Blue", FragmentManager.POP_BACK_STACK_INCLUSIVE);
        //Pop Fragment stack upto but not including
        //fragmentManager.popBackStack("Blue", 0);
    }

    public void onRemoveSingleInstanceClicked(View view) {
        fragmentManager.beginTransaction().remove(blueFragment).commit();
    }
}
