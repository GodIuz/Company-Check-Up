package com.droidgeniuslabs.companycheck_up.ui.register;

import static com.droidgeniuslabs.companycheck_up.Utilities.PasswordValidator.isPasswordValid;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import com.droidgeniuslabs.companycheck_up.R;

public class RegisterFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        Button back = view.findViewById(R.id.BackButton);
        Button register = view.findViewById(R.id.RegisterButton);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) EditText editText1 = view.findViewById(R.id.editTextPassword2);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) EditText editText2 = view.findViewById(R.id.editTextConfirmPassword3);
        String text1 = editText1.getText().toString();
        String text2 = editText2.getText().toString();
        CheckBox revealPasswordCheckBox = view.findViewById(R.id.revealPasswordCheckBox);

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
                if(isPasswordValid(text1))
                {
                    if(text2.equals(text1))
                    {
                        NavController navController = Navigation.findNavController(v);
                        navController.navigate(R.id.action_registerFragment_to_isologismosFirstFragment);
                    }else{
                        Toast.makeText(requireContext(),"Password didn't match. Try again,",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(requireContext(),"Password is invalid !",Toast.LENGTH_SHORT).show();
                }
            }
        });

        revealPasswordCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                revealPasswordCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            // Show the password in plain text.
                            editText1.setTransformationMethod(null);
                            editText2.setTransformationMethod(null);
                        } else {
                            // Hide the password (use the default password mask).
                            editText1.setTransformationMethod(new PasswordTransformationMethod());
                            editText2.setTransformationMethod(new PasswordTransformationMethod());
                        }
                    }
                });

            }
        });

        return view;
    }
}