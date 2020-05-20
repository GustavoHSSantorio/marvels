package br.com.marvel.characters.main.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class TextSearchPublisher

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class SearchCanceledPublisher
