package com.droidgeniuslabs.companycheck_up.ui.register;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;
import static com.droidgeniuslabs.companycheck_up.Utilities.PasswordValidator.isPasswordValid;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import com.droidgeniuslabs.companycheck_up.R;
import com.droidgeniuslabs.companycheck_up.data.UserData;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

public class RegisterFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String username,email,password,dob,confirm;
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        Button back = view.findViewById(R.id.BackButton1);
        Button register = view.findViewById(R.id.RegisterButton);
        EditText editTextPassword = view.findViewById(R.id.editTextPassword2);
        EditText editTextConfirmPassword = view.findViewById(R.id.editTextConfirmPassword3);
        EditText editTextUsername= view.findViewById(R.id.editTextUsername);
        EditText editTextEmail = view.findViewById(R.id.editTextEmail);
        EditText editTextDoB = view.findViewById(R.id.editTextDate);
        CheckBox revealPasswordCheckBox = view.findViewById(R.id.revealPasswordCheckBox);

        username= editTextUsername.getText().toString();
        email = editTextEmail.getText().toString();
        password = editTextPassword.getText().toString();
        confirm = editTextConfirmPassword.getText().toString();
        dob = editTextDoB.getText().toString();



        FirebaseApp.initializeApp(requireContext());
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        UserData userData = new UserData();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_registerFragment_to_loginFragment);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CollectionReference usersCollection = db.collection("/Users/UserData");
                Query query = usersCollection.whereEqualTo("Email", "email");
                query.get().addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        QuerySnapshot querySnapshot = task.getResult();
                        if (querySnapshot.isEmpty())
                        {
                            if(isPasswordValid(confirm)&&isPasswordValid(password))
                            {
                                if(confirm.equals(password))
                                {
                                    CollectionReference collection = db.collection("/Users/UserData");
                                    collection.add(userData)
                                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                                @Override
                                                public void onSuccess(DocumentReference documentReference) {
                                                    userData.setUsername(username);
                                                    userData.setEmail(email);
                                                    userData.setPassword(password);
                                                    userData.setDob(dob);
                                                    NavController navController = Navigation.findNavController(v);
                                                    navController.navigate(R.id.action_registerFragment_to_isologismosFirstFragment);
                                                    Snackbar.make(view,"New user registered",Snackbar.LENGTH_SHORT).show();
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Snackbar.make(view,"Error while user registering ",Snackbar.LENGTH_SHORT).show();
                                                }
                                            });
                                }else{
                                    Toast.makeText(requireContext(),"Password didn't match. Try again,",Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                Toast.makeText(requireContext(),"Password is invalid !",Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            // The email already exists in the Firestore database.
                            // You can access the data using querySnapshot.
                        }
                    } else {
                        // Handle the error.
                        Exception e = task.getException();
                        Log.e(TAG, "Error checking email existence: " + e.getMessage());
                    }
                });
            }
        });

        revealPasswordCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                revealPasswordCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            // Show password
                            editTextPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                            editTextConfirmPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                        } else {
                            // Hide password
                            editTextPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                            editTextConfirmPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                        }
                        // Move the cursor to the end of the text
                        editTextPassword.setSelection(editTextPassword.getText().length());
                        editTextConfirmPassword.setSelection(editTextConfirmPassword.getText().length());
                    }
                });

            }
        });

        return view;
    }
}