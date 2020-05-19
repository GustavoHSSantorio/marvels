package br.com.marvel.characterData.di

import br.com.marvel.characterData.data.CharacterRepository
import br.com.marvel.characterData.data.CharacterRepositoryImp
import br.com.marvel.characterData.data.CharacterService
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
abstract class CharacterDataModule {

    @Binds
    abstract fun bindCharacterRepository(repositoryImp: CharacterRepositoryImp) : CharacterRepository

    @Module
    companion object {
        @JvmStatic
        @Provides
        fun provideService(retrofit: Retrofit): CharacterService =
            retrofit.create(CharacterService::class.java)
    }

}