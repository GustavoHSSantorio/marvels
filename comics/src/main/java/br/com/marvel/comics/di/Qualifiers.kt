package br.com.marvel.comics.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ComicTextSearchPublisher

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ComicSearchCanceledPublisher
