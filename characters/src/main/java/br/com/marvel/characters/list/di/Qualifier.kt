package br.com.marvel.characters.list.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class CharactersTextSearchPublisher

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class CharactersSearchCanceledPublisher
