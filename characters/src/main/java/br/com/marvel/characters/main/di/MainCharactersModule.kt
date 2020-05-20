package br.com.marvel.characters.main.di

import androidx.lifecycle.ViewModel
import br.com.marvel.characters.main.presentation.MainCharactersViewModel
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
        @TextSearchPublisher
        fun providesTextSearchPublisher(): PublishSubject<String> = PublishSubject.create()

        @ActivityScope
        @Provides
        @JvmStatic
        @SearchCanceledPublisher
        fun providesSearchCanceledPublisher(): PublishSubject<Unit> = PublishSubject.create()
    }
}
