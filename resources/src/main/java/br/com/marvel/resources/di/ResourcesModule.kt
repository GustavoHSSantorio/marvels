package br.com.marvel.resources.di

import android.app.Application
import android.content.res.Resources
import dagger.Module
import dagger.Provides

@Module
class ResourcesModule {
    @Provides
    fun provideAppResources(application: Application): Resources = application.resources
}
