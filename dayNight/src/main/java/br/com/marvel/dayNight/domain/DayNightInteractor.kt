package br.com.marvel.dayNight.domain

import android.content.SharedPreferences
import br.com.marvel.resources.ThemeHelper
import javax.inject.Inject

interface DayNightInteractor {
    fun changeTheme(mode: ThemeHelper.Theme)
    fun getSelectedTheme() : Int
}

class DayNightInteractorImp @Inject constructor(private val themeHelper: ThemeHelper, private val sharedPreferences: SharedPreferences): DayNightInteractor{

    override fun changeTheme(mode: ThemeHelper.Theme) {
        sharedPreferences.edit().putInt(THEME, mode.id).apply()
        themeHelper.applyTheme(mode)
    }

    override fun getSelectedTheme(): Int =
        sharedPreferences.getInt(THEME, 1)

    companion object {
        private const val THEME = "theme"
    }
}