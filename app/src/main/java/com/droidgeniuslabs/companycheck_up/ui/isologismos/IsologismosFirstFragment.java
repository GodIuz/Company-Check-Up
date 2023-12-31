package com.droidgeniuslabs.companycheck_up.ui.isologismos;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.droidgeniuslabs.companycheck_up.R;

public class IsologismosFirstFragment extends Fragment {
    public IsologismosFirstFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_isologismos_first, container, false);

        Button loadButton = view.findViewById(R.id.LoadButton);
        Button newButton = view.findViewById(R.id.NewButton);

        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(requireContext(),"Coming Soon !",Toast.LENGTH_SHORT).show();
            }
        });

        newButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController= Navigation.findNavController(v);
                navController.navigate(R.id.action_isologismosFirstFragment_to_activeIsologismsoFragment);
            }
        });
        return view;
    }
}