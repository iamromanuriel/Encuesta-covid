package com.estadia.encuesta.ui.singIn;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.estadia.encuesta.databinding.FragmentSignInBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SignInFragment extends Fragment {
    private FragmentSignInBinding binding;
    private SignInViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSignInBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewModelProvider viewModelProvider = new ViewModelProvider(this);
        viewModel = viewModelProvider.get(SignInViewModel.class);

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.onStart();
        viewModel._imei.observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String imei) {
                if(!imei.isEmpty()){
                    binding.outputLayoutImai.setEnabled(false);
                    binding.imeiInput.setText(imei);
                }
            }
        });
        signIn();
    }

    private void signIn(){
        binding.actionSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = binding.userInput.getText().toString();
                if(user.isEmpty()){
                    binding.outlineInputUser.setError("Requerido");
                }
            }
        });
    }

}
