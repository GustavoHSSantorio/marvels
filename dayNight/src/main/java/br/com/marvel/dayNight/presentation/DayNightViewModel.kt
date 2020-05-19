package br.com.marvel.dayNight.presentation

import androidx.lifecycle.MutableLiveData
import br.com.marvel.base.BaseViewModel
import br.com.marvel.dayNight.R
import br.com.marvel.dayNight.domain.DayNightInteractor
import br.com.marvel.resources.ThemeHelper
import javax.inject.Inject

enum class SelectedCheck {
    SelectThemeLight,
    SelectThemeDark,
    SelectThemeForBattery,
    SelectThemeSystem
}

class DayNightViewModel @Inject constructor(private val interactor: DayNightInteractor) : BaseViewModel() {

    val selectCheck = MutableLiveData<SelectedCheck>()

    override fun onCreate() {
        super.onCreate()
        getSelectTheme()
    }

    private fun getSelectTheme() {
        when (interactor.getSelectedTheme()) {
            1 -> selectCheck.value = SelectedCheck.SelectThemeLight
            2 -> selectCheck.value = SelectedCheck.SelectThemeDark
            3 -> selectCheck.value = SelectedCheck.SelectThemeForBattery
            4 -> selectCheck.value = SelectedCheck.SelectThemeSystem
        }
    }

    fun onThemeSelected(checkedId: Int) {
        when (checkedId) {
            R.id.themeLight -> interactor.changeTheme(ThemeHelper.Theme.LightMode)
            R.id.themeDark -> interactor.changeTheme(ThemeHelper.Theme.DarkMode)
            R.id.themeBattery -> interactor.changeTheme(ThemeHelper.Theme.BatterySaverMode)
            R.id.themeSystem -> interactor.changeTheme(ThemeHelper.Theme.Default)
        }
    }
}
