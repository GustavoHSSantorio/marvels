package br.com.marvel.characters.list.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.marvel.base.BaseFragment
import br.com.marvel.characters.R
import br.com.marvel.characters.databinding.FragmentCharacterListBinding
import br.com.marvel.charactersProfile.presentation.CharactersProfileActivity

class CharacterListFragment : BaseFragment() {

    private val vm by appViewModel<CharacterListViewModel>()
    private lateinit var binding: FragmentCharacterListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupObservers()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_character_list, container, false)
        binding.lifecycleOwner = this
        binding.vm = vm

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupObservers() {
        vm.stateLiveData.observe(this, Observer {
            when (it) {
                CharacterListState.ShowSuccessView -> {
                    binding.viewFlipper.displayedChild = 0
                }
                CharacterListState.ShowErrorView -> {
                    binding.viewFlipper.displayedChild = 1
                }
                CharacterListState.ShowLoading -> {
                    binding.viewFlipper.visibility = View.INVISIBLE
                    binding.progressBar.visibility = View.VISIBLE
                }
                CharacterListState.RemoveLoading -> {
                    binding.viewFlipper.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE
                }
            }
        })
        vm.charactersLiveData.observe(this, Observer {
            (binding.recyclerView.adapter as CharacterListAdapter).list = it
        })
    }

    private fun setupRecyclerView() {
        binding.recyclerView.adapter =
            CharacterListAdapter()

        (binding.recyclerView.adapter as CharacterListAdapter).clickListener = object : CharacterClickListener{
            override fun onCharacterClicked(characterId: Int?) {
                startActivity(CharactersProfileActivity.newInstance(requireContext(), characterId))
            }
        }

        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(
                @NonNull recyclerView: RecyclerView,
                dx: Int,
                dy: Int
            ) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager = recyclerView.layoutManager
                if (linearLayoutManager is LinearLayoutManager)
                    vm.onLastItemVisible(linearLayoutManager.findLastVisibleItemPosition())
            }
        })
    }
}
