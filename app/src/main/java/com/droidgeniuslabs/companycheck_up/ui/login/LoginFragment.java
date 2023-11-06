package com.droidgeniuslabs.companycheck_up.ui.login;

import static com.droidgeniuslabs.companycheck_up.Utilities.NetworkUtils.isNetworkAvailable;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import com.droidgeniuslabs.companycheck_up.R;

public class LoginFragment extends Fragment {


    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_login, container, false);
        Button register = view.findViewById(R.id.buttonRegister);
        Button login = view.findViewById(R.id.buttonLogin);
        EditText editTextEmail = view.findViewById(R.id.editTextEmailAddress);
        EditText passwordEditText = view.findViewById(R.id.editTextPassword);
        CheckBox revealPasswordCheckbox = view.findViewById(R.id.revealPasswordCheckbox);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_loginFragment_to_registerFragment);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        if(isNetworkAvailable(requireContext()))
        {
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.action_loginFragment_to_isologismosFirstFragment);
            Toast.makeText(requireContext(),"Welcome !",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(requireContext(),"Not Connected",Toast.LENGTH_SHORT).show();
        }
            }
        });

        revealPasswordCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // Show the password in plain text.
                    passwordEditText.setTransformationMethod(null);
                } else {
                    // Hide the password (use the default password mask).
                    passwordEditText.setTransformationMethod(new PasswordTransformationMethod());
                }
            }
        });

        return view;
    }

}