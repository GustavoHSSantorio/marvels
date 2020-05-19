package br.com.marvel.comicData.di

import br.com.marvel.comicData.data.ComicRepository
import br.com.marvel.comicData.data.ComicRepositoryImp
import br.com.marvel.comicData.data.ComicService
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
abstract class ComicDataModule {
    @Binds
    abstract fun bindCharacterRepository(repositoryImp: ComicRepositoryImp): ComicRepository

    @Module
    companion object {
        @JvmStatic
        @Provides
        fun provideService(retrofit: Retrofit): ComicService =
            retrofit.create(ComicService::class.java)
    }
}
