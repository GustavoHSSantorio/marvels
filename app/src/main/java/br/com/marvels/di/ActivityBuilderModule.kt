package br.com.marvels.di

import br.com.marvel.characters.list.di.CharacterListModuleBuilder
import br.com.marvel.comics.di.ComicListModuleBuilder
import br.com.marvel.main.di.MainCharactersModule
import br.com.marvel.main.presentation.MainCharactersActivity
import br.com.marvel.dayNight.di.DayNightModuleBuilder
import br.com.marvel.di.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [
        MainCharactersModule::class,
        DayNightModuleBuilder::class,
        CharacterListModuleBuilder::class,
        ComicListModuleBuilder::class])
    abstract fun bindMainCharacterActivity(): MainCharactersActivity
}
