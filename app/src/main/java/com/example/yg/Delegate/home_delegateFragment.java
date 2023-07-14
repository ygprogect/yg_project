package com.example.yg.Delegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.yg.Aqel.A_Quotas_Activity;
import com.example.yg.Aqel.a_delegates_list;
import com.example.yg.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link home_delegateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class home_delegateFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public home_delegateFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment homeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static home_delegateFragment newInstance(String param1, String param2) {
        home_delegateFragment fragment = new home_delegateFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_homedelegate, container, false);
        CardView myCardView;
        myCardView= v.findViewById(R.id.d_citizens);
        myCardView.setOnClickListener(view -> {
                    Intent intent = new Intent(getActivity(), Citizens_Activity.class);
                    startActivity(intent);
                }
        );
        CardView qoutas_cv = v.findViewById(R.id.d_qoutas);
        qoutas_cv.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), Quotas_statements.class);
            startActivity(intent);
        });

        return v;
    }

}