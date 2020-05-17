package br.com.marvel.characters.main.presentation

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import br.com.marvel.base.BaseActivity
import br.com.marvel.characters.R
import br.com.marvel.characters.databinding.ActivityMainCharactersBinding

class MainCharactersActivity : BaseActivity() {

    private val vm by appViewModel<MainCharactersViewModel>()

    private lateinit var binding: ActivityMainCharactersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main_characters)
        binding.lifecycleOwner = this
        binding.vm = vm

        setupNavigation()
    }

    private fun setupNavigation() {
        val navHostFragment = (supportFragmentManager.findFragmentById(R.id.navHosFragment) as NavHostFragment)
        NavigationUI.setupWithNavController(binding.bottomNavigation, navHostFragment.navController)
    }
}
