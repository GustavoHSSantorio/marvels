package br.com.marvels.di

import br.com.marvel.characters.main.di.MainCharactersModule
import br.com.marvel.characters.main.presentation.MainCharactersActivity
import br.com.marvel.di.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainCharactersModule::class])
    abstract fun bindMainCharacterActivity(): MainCharactersActivity
}
