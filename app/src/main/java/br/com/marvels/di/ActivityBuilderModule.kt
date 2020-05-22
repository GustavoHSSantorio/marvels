package br.com.marvels.di

import br.com.marvel.characterData.di.CharacterDataModule
import br.com.marvel.characters.list.di.CharacterListModuleBuilder
import br.com.marvel.charactersProfile.di.CharactersProfileModule
import br.com.marvel.charactersProfile.presentation.CharactersProfileActivity
import br.com.marvel.comicData.di.ComicDataModule
import br.com.marvel.comics.di.ComicListModuleBuilder
import br.com.marvel.dayNight.di.DayNightModuleBuilder
import br.com.marvel.di.ActivityScope
import br.com.marvel.main.di.MainCharactersModule
import br.com.marvel.main.presentation.MainActivity
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
    abstract fun bindMainActivity(): MainActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [
        CharactersProfileModule::class,
        CharacterDataModule::class,
        ComicDataModule::class
    ])
    abstract fun bindCharactersProfileActivity(): CharactersProfileActivity
}
