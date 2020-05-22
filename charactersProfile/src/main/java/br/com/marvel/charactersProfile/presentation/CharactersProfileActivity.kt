package br.com.marvel.charactersProfile.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

        setupSeriesRecyclerView()
        setupObservers()
    }

    private fun setupObservers() {
        vm.comicsLiveData.observe(this, Observer {

        })

        vm.seriesLiveData.observe(this, Observer {
            (binding.recyclerViewSeries.adapter as SeriesListAdapter).list = it
        })
    }

    private fun setupSeriesRecyclerView(){
        binding.recyclerViewSeries.adapter = SeriesListAdapter()
        binding.recyclerViewSeries.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(
                @NonNull recyclerView: RecyclerView,
                dx: Int,
                dy: Int
            ) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager = recyclerView.layoutManager
                if (linearLayoutManager is LinearLayoutManager)
                    vm.onSeriesLastItemVisible(linearLayoutManager.findLastVisibleItemPosition())
            }
        })
    }

    companion object{
        const val CHARACTER_ID_BUNDLE = "character_id"

        fun newInstance(context: Context, characterId : Int?) = Intent(context, CharactersProfileActivity::class.java).apply {
            putExtra(CHARACTER_ID_BUNDLE, characterId)
        }
    }
}