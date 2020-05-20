package br.com.marvel.characters.list.di

import androidx.lifecycle.ViewModel
import br.com.marvel.characterData.di.CharacterDataModule
import br.com.marvel.characters.list.domain.CharacterListInteractor
import br.com.marvel.characters.list.domain.CharacterListInteractorImp
import br.com.marvel.characters.list.presentation.CharacterListFragment
import br.com.marvel.characters.list.presentation.CharacterListViewModel
import br.com.marvel.di.FragmentScope
import br.com.marvel.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class CharacterListModuleBuilder {
    @FragmentScope
    @ContributesAndroidInjector(modules = [CharacterListModule::class, CharacterDataModule::class])
    abstract fun bindsCharacterListFragment(): CharacterListFragment
}

@Module
abstract class CharacterListModule {

    @Binds
    abstract fun bindCharacterListInteractor(interactor: CharacterListInteractorImp): CharacterListInteractor

    @Binds
    @IntoMap
    @ViewModelKey(CharacterListViewModel::class)
    abstract fun bindsCharacterListViewModel(characterListViewModel: CharacterListViewModel): ViewModel
}
