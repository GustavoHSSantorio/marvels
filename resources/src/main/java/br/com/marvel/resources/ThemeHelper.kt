package br.com.marvel.resources

import androidx.appcompat.app.AppCompatDelegate
import javax.inject.Inject

interface ThemeHelper {
    fun applyTheme(theme: ThemeHelperImp.Theme)
}

class ThemeHelperImp @Inject constructor() : ThemeHelper{

    enum class Theme{
        LightMode,
        DarkMode,
        BatterySaverMode,
        Default
    }

    override fun applyTheme(theme: Theme) {
        when (theme) {
            Theme.LightMode -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            Theme.DarkMode -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            Theme.BatterySaverMode -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY)
            Theme.Default -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        }
    }
}