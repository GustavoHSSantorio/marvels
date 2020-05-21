package br.com.marvel.charactersProfile.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import br.com.marvel.base.BaseActivity
import br.com.marvel.characters.profile.R
import br.com.marvel.characters.profile.databinding.ActivityCharactersProfileBinding

class CharactersProfileActivity : BaseActivity() {

    private val vm by appViewModel<CharactersProfileViewModel>()

    private lateinit var binding : ActivityCharactersProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_characters_profile)
        binding.lifecycleOwner = this
        binding.vm = vm

        setupObservers()
    }

    private fun setupObservers() {
        vm.comicsLiveData.observe(this, Observer {

        })

        vm.seriesLiveData.observe(this, Observer {

        })
    }

    companion object{
        const val CHARACTER_ID_BUNDLE = "character_id"

        fun newInstance(context: Context, characterId : Int?) = Intent(context, CharactersProfileActivity::class.java).apply {
            putExtra(CHARACTER_ID_BUNDLE, characterId)
        }
    }
}