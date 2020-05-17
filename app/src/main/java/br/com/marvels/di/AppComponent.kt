package br.com.marvels.di

import android.app.Application
import br.com.marvel.di.ViewModelFactoryModule
import br.com.marvel.network.NetworkModule
import br.com.marvel.resources.di.ResourcesModule
import br.com.marvel.resources.di.ThemeHelperModule
import br.com.marvels.MarvelsApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ActivityBuilderModule::class,
    ViewModelFactoryModule::class,
    ContextModule::class,
    ResourcesModule::class,
    ThemeHelperModule::class,
    NetworkModule::class])
interface AppComponent : AndroidInjector<DaggerApplication> {

    fun inject(application: MarvelsApplication)

    override fun inject(instance: DaggerApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}
