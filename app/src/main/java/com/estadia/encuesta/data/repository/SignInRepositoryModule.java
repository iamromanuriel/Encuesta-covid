package com.estadia.encuesta.data.repository;

import android.content.Context;

import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class SignInRepositoryModule {
    public static SignInRepository providerSignInRepository(@ApplicationContext Context context){
        return new SignInRepository(context);
    }
}
