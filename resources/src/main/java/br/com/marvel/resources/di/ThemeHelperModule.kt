package br.com.marvel.resources.di

import br.com.marvel.resources.ThemeHelper
import br.com.marvel.resources.ThemeHelperImp
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class ThemeHelperModule {

    @Binds
    @Singleton
    abstract fun bindThemeHelper(helper: ThemeHelperImp): ThemeHelper
}
