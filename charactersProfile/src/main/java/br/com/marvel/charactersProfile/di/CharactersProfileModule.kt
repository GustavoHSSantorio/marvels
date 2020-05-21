package br.com.marvel.charactersProfile.di

import androidx.lifecycle.ViewModel
import br.com.marvel.charactersProfile.domain.CharactersProfileInteractor
import br.com.marvel.charactersProfile.domain.CharactersProfileInteractorImp
import br.com.marvel.charactersProfile.presentation.CharactersProfileActivity
import br.com.marvel.charactersProfile.presentation.CharactersProfileActivity.Companion.CHARACTER_ID_BUNDLE
import br.com.marvel.charactersProfile.presentation.CharactersProfileViewModel
import br.com.marvel.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class CharactersProfileModule {

    @Binds
    abstract fun bindCharacterListInteractor(interactor: CharactersProfileInteractorImp): CharactersProfileInteractor

    @Binds
    @IntoMap
    @ViewModelKey(CharactersProfileViewModel::class)
    abstract fun bindsCharacterListViewModel(characterListViewModel: CharactersProfileViewModel): ViewModel


    @Module
    companion object{
        @Provides
        @JvmStatic
        @CharacterId
        fun providesCharacterId(activity : CharactersProfileActivity) = activity.intent.extras?.getInt(CHARACTER_ID_BUNDLE)
    }
}