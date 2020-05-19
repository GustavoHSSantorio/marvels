package br.com.marvel.dayNight.di

import androidx.lifecycle.ViewModel
import br.com.marvel.dayNight.domain.DayNightInteractor
import br.com.marvel.dayNight.domain.DayNightInteractorImp
import br.com.marvel.dayNight.presentation.DayNightBottomSheetDialog
import br.com.marvel.dayNight.presentation.DayNightViewModel
import br.com.marvel.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class DayNightModuleBuilder {
    @ContributesAndroidInjector(modules = [DayNightModule::class])
    abstract fun bindsDayNightDialog(): DayNightBottomSheetDialog
}

@Module
abstract class DayNightModule {

    @Binds
    abstract fun bindDayNightInteractor(interactor: DayNightInteractorImp): DayNightInteractor

    @Binds
    @IntoMap
    @ViewModelKey(DayNightViewModel::class)
    abstract fun bindMainCharactersViewModel(viewModel: DayNightViewModel): ViewModel
}
