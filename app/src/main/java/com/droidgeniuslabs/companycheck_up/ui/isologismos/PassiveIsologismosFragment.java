package com.droidgeniuslabs.companycheck_up.ui.isologismos;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.droidgeniuslabs.companycheck_up.R;

public class PassiveIsologismosFragment extends Fragment {
    public PassiveIsologismosFragment()
    {
        float kefalaio = 0;
        float provlepseis = 0;
        float m_ypo = 0;
        float b_ypo = 0;
        float passive = 0;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_passive_isologismos, container, false);

        Button back = view.findViewById(R.id.BackButton3);
        Button next = view.findViewById(R.id.NextButton2);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_passiveIsologismosFragment_to_activeIsologismsoFragment);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(v);

            }
        });
        return view;
    }
}