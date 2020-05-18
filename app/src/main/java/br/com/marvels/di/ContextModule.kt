package br.com.marvels.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class ContextModule {
    @Binds
    abstract fun provideContext(application: Application): Context
}
