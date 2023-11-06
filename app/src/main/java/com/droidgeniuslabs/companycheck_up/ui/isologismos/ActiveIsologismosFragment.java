package com.droidgeniuslabs.companycheck_up.ui.isologismos;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.droidgeniuslabs.companycheck_up.R;
import com.droidgeniuslabs.companycheck_up.data.ActiveIsologismosmosData;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class ActiveIsologismosFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        float pagia = 0;
        float apothema=0;
        float apaitiseis=0;
        float diathesima=0;
        float active=0;

        FirebaseApp.initializeApp(requireContext());
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        ActiveIsologismosmosData activeIsologismosmosData = new ActiveIsologismosmosData();
        activeIsologismosmosData.setActive(active);
        activeIsologismosmosData.setPagia(pagia);
        activeIsologismosmosData.setApothema(apothema);
        activeIsologismosmosData.setApaitiseis(apaitiseis);
        activeIsologismosmosData.setDiathesima(diathesima);

        View view = inflater.inflate(R.layout.fragment_active_isologismso, container, false);

        Button next = view.findViewById(R.id.NextButton);
        Button back = view.findViewById(R.id.BackButton2);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_activeIsologismsoFragment_to_isologismosFirstFragment);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Get a reference to the Firestore collection
                CollectionReference collection = db.collection("Active Isologismos");

                    // Add a document with the data
                collection.add(activeIsologismosmosData)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Snackbar.make(view,"Data Saved",Snackbar.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Snackbar.make(view,"Data not Saved",Snackbar.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        return view;
    }

}