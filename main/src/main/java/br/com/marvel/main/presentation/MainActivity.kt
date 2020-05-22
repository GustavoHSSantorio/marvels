package br.com.marvel.main.presentation

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import br.com.marvel.base.BaseActivity
import br.com.marvel.dayNight.presentation.DayNightBottomSheetDialog
import br.com.marvel.main.R
import br.com.marvel.main.databinding.ActivityMainCharactersBinding

class MainActivity : BaseActivity() {

    private val vm by appViewModel<MainCharactersViewModel>()

    private lateinit var binding: ActivityMainCharactersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main_characters)
        binding.lifecycleOwner = this
        binding.vm = vm

        setSupportActionBar(binding.toolbar)
        binding.toolbar.title = "Marvel"

        setupNavigation()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        setupSearchView(menu?.findItem(R.id.action_search)?.actionView as? SearchView)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> {
                DayNightBottomSheetDialog().show(supportFragmentManager, "")
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupNavigation() {
        val navHostFragment = (supportFragmentManager.findFragmentById(R.id.navHosFragment) as NavHostFragment)
        NavigationUI.setupWithNavController(binding.bottomNavigation, navHostFragment.navController)
    }

    private fun setupSearchView(searchView: SearchView?) {
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                vm.onTextSubmit(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        searchView?.setOnCloseListener {
            vm.onSearchCanceled()
            false
        }
    }
}
