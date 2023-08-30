package com.estadia.encuesta.ui.singIn;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.estadia.encuesta.data.repository.SignInRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class SignInViewModel extends ViewModel {
    @Inject
    SignInRepository signInRepository;

    @Inject
    public SignInViewModel() {
    }

    public MutableLiveData<String> _imei = new MutableLiveData<>();

    public void onStart(){
        _imei.postValue(signInRepository.getImei());
    }
}
