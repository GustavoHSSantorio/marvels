package br.com.marvel.main.di

import androidx.lifecycle.ViewModel
import br.com.marvel.characters.list.di.CharactersSearchCanceledPublisher
import br.com.marvel.characters.list.di.CharactersTextSearchPublisher
import br.com.marvel.comics.di.ComicSearchCanceledPublisher
import br.com.marvel.comics.di.ComicTextSearchPublisher
import br.com.marvel.main.presentation.MainCharactersViewModel
import br.com.marvel.di.ActivityScope
import br.com.marvel.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import io.reactivex.subjects.PublishSubject

@Module
abstract class MainCharactersModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainCharactersViewModel::class)
    abstract fun bindMainCharactersViewModel(viewModel: MainCharactersViewModel): ViewModel

    @Module
    companion object {

        @ActivityScope
        @Provides
        @JvmStatic
        @CharactersTextSearchPublisher
        fun providesCharactersTextSearchPublisher(): PublishSubject<String> = PublishSubject.create()

        @ActivityScope
        @Provides
        @JvmStatic
        @CharactersSearchCanceledPublisher
        fun providesCharactersSearchCanceledPublisher(): PublishSubject<Unit> = PublishSubject.create()

        @ActivityScope
        @Provides
        @JvmStatic
        @ComicTextSearchPublisher
        fun providesComicsTextSearchPublisher(): PublishSubject<String> = PublishSubject.create()

        @ActivityScope
        @Provides
        @JvmStatic
        @ComicSearchCanceledPublisher
        fun providesComicsSearchCanceledPublisher(): PublishSubject<Unit> = PublishSubject.create()
    }
}
