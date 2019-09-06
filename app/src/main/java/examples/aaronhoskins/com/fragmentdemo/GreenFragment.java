package examples.aaronhoskins.com.fragmentdemo;


import android.content.Context;
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

public class GreenFragment extends Fragment implements View.OnClickListener {
    OnFragmentInteractionListener mListener;
    TextView tvMessageFromBlueFragment;
    EditText etMessageToSendToBlueFrag;
    Button btnSendMessage;

    public GreenFragment() {
        // Required empty public constructor
    }

    public static GreenFragment newInstance() {
        GreenFragment fragment = new GreenFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_green, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvMessageFromBlueFragment = view.findViewById(R.id.tvMessageFromBlueFragment);
        etMessageToSendToBlueFrag = view.findViewById(R.id.etGreenFragInput);
        btnSendMessage = view.findViewById(R.id.btnSendDataToBlueFrag);
        btnSendMessage.setOnClickListener(this);
    }

    public void setMessageFromBlueFragment(final String message) {
        tvMessageFromBlueFragment.setText(message);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener)context;
        } else {
            throw new RuntimeException("Interface not implemented ");
        }
    }

    @Override
    public void onClick(View view) {
        final String input = etMessageToSendToBlueFrag.getText().toString();
        mListener.onSendDataToBlueFragment(input);
    }

    public interface OnFragmentInteractionListener{
        void onSendDataToBlueFragment(String message);

    }
}
