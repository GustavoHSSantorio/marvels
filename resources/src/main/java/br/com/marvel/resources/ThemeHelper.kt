package br.com.marvel.resources

import androidx.appcompat.app.AppCompatDelegate
import javax.inject.Inject

interface ThemeHelper {
    fun applyTheme(theme: Theme)

    enum class Theme(val id: Int) {
        LightMode(1),
        DarkMode(2),
        BatterySaverMode(3),
        Default(4)
    }
}

class ThemeHelperImp @Inject constructor() : ThemeHelper {
    override fun applyTheme(theme: ThemeHelper.Theme) {
        when (theme) {
            ThemeHelper.Theme.LightMode -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            ThemeHelper.Theme.DarkMode -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            ThemeHelper.Theme.BatterySaverMode -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY)
            ThemeHelper.Theme.Default -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        }
    }
}
