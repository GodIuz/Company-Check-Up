package com.droidgeniuslabs.companycheck_up.ui.isologismos;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.droidgeniuslabs.companycheck_up.R;
import com.droidgeniuslabs.companycheck_up.data.PassiveIsologismosData;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class PassiveIsologismosFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        float kefalaio;
        float provlepseis;
        float m_ypo;
        float b_ypo;
        float passive;

        View view = inflater.inflate(R.layout.fragment_passive_isologismos, container, false);
        Button back = view.findViewById(R.id.BackButton3);
        Button next = view.findViewById(R.id.NextButton2);
        EditText editTextKefalaio = view.findViewById(R.id.editTextKefalaio);
        EditText editTextProvlepseis = view.findViewById(R.id.editTextProvlepseis);
        EditText editTextM_YPO = view.findViewById(R.id.editTextM_Ypoxrewseis);
        EditText editTextB_Ypo = view.findViewById(R.id.editTextB_Ypoxrewseis);

        kefalaio= Float.parseFloat(editTextKefalaio.getText().toString());
        provlepseis =Float.parseFloat(editTextProvlepseis.getText().toString());
        m_ypo= Float.parseFloat(editTextM_YPO.getText().toString());
        b_ypo= Float.parseFloat(editTextB_Ypo.getText().toString());
        passive= kefalaio+provlepseis+m_ypo+b_ypo;

        FirebaseApp.initializeApp(requireContext());
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        PassiveIsologismosData passiveIsologismosData = new PassiveIsologismosData();

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
                // Get a reference to the Firestore collection
                CollectionReference collection = db.collection("/Users/UserData/Active Isologismos/Active Isologismos/Passive Isologismos/Passive Isologismos");

                collection.add(passiveIsologismosData)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                PassiveIsologismosData passiveIsologismosData = new PassiveIsologismosData();
                                passiveIsologismosData.setKefalaio(kefalaio);
                                passiveIsologismosData.setProvlepseis(provlepseis);
                                passiveIsologismosData.setM_ypo(m_ypo);
                                passiveIsologismosData.setB_ypo(b_ypo);
                                passiveIsologismosData.setPassive(passive);

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