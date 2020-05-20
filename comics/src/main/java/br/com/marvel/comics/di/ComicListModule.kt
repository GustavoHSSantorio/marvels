package br.com.marvel.comics.di

import androidx.lifecycle.ViewModel
import br.com.marvel.comicData.di.ComicDataModule
import br.com.marvel.comics.domain.ComicListInteractor
import br.com.marvel.comics.domain.ComicListInteractorImp
import br.com.marvel.comics.presentation.ComicListViewModel
import br.com.marvel.comics.presentation.ComicsListFragment
import br.com.marvel.di.FragmentScope
import br.com.marvel.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap


@Module
abstract class ComicListModuleBuilder{
    @FragmentScope
    @ContributesAndroidInjector(modules = [ComicListModule::class, ComicDataModule::class])
    abstract fun bindComicListFragment() : ComicsListFragment
}

@Module
abstract class ComicListModule {
    @Binds
    abstract fun bindComicListInteractor(interactor: ComicListInteractorImp): ComicListInteractor

    @Binds
    @IntoMap
    @ViewModelKey(ComicListViewModel::class)
    abstract fun bindsCharacterListViewModel(characterListViewModel: ComicListViewModel): ViewModel
}