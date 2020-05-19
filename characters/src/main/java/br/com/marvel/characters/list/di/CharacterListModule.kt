package br.com.marvel.characters.list.di

import androidx.lifecycle.ViewModel
import br.com.marvel.base.BaseViewModel
import br.com.marvel.characters.list.data.CharacterListRepository
import br.com.marvel.characters.list.data.CharacterListRepositoryImp
import br.com.marvel.characters.list.data.CharacterListService
import br.com.marvel.characters.list.domain.CharacterListInteractor
import br.com.marvel.characters.list.domain.CharacterListInteractorImp
import br.com.marvel.characters.list.presentation.CharacterListFragment
import br.com.marvel.characters.list.presentation.CharacterListViewModel
import br.com.marvel.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import retrofit2.Retrofit

@Module
abstract class CharacterListModuleBuilder{
    @ContributesAndroidInjector(modules = [CharacterListModule::class])
    abstract fun bindsCharacterListFragment() : CharacterListFragment
}

@Module
abstract class CharacterListModule {

    @Binds
    abstract fun bindCharacterListRepository(repositoryImp: CharacterListRepositoryImp) : CharacterListRepository

    @Binds
    abstract fun bindCharacterListInteractor(interactor: CharacterListInteractorImp) : CharacterListInteractor

    @Binds
    @IntoMap
    @ViewModelKey(CharacterListViewModel::class)
    abstract fun bindsCharacterListViewModel(characterListViewModel: CharacterListViewModel) : ViewModel


    @Module
    companion object{
        @JvmStatic
        @Provides
        fun provideService(retrofit: Retrofit): CharacterListService =
            retrofit.create(CharacterListService::class.java)
    }
}