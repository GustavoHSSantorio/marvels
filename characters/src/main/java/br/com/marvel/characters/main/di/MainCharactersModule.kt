package br.com.marvel.characters.main.di

import androidx.lifecycle.ViewModel
import br.com.marvel.characters.main.presentation.MainCharactersViewModel
import br.com.marvel.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainCharactersModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainCharactersViewModel::class)
    abstract fun bindMainCharactersViewModel(viewModel : MainCharactersViewModel): ViewModel

}