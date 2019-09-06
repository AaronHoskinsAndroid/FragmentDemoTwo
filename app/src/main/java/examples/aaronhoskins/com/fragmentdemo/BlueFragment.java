package examples.aaronhoskins.com.fragmentdemo;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class BlueFragment extends Fragment implements View.OnClickListener {
    //Declare Views
    EditText etUserInput;
    TextView tvPassedOnInit;
    Button btnSendDataToActivity;

    private static final String ARG_PASSED_DATA = "param1";
    private String passedData;
    private OnFragmentInteractionListener mListener;

    public BlueFragment() {
        // Required empty public constructor
    }

    public static BlueFragment newInstance(String data) {
        BlueFragment fragment = new BlueFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PASSED_DATA, data);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        if (getArguments() != null) {
            passedData = getArguments().getString(ARG_PASSED_DATA);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blue, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //bind the views
        etUserInput = view.findViewById(R.id.etUserInput);
        tvPassedOnInit = view.findViewById(R.id.tvPassedOnInit);
        btnSendDataToActivity = view.findViewById(R.id.btnSendData);
        btnSendDataToActivity.setOnClickListener(this);
        if(!passedData.isEmpty()) {
            tvPassedOnInit.setText(passedData);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    public void setTextViewValue(final String message) {
        tvPassedOnInit.setText(message);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View view) {
        final String input = etUserInput.getText().toString();
        mListener.onSendData(input);
    }

    public interface OnFragmentInteractionListener {
        void onSendData(String dataToSendToActivity);
    }
}
