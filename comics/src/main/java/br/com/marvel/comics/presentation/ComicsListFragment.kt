package br.com.marvel.comics.presentation

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
import br.com.marvel.comics.R
import br.com.marvel.comics.databinding.FragmentComicListBinding

class ComicsListFragment : BaseFragment() {

    private val vm by appViewModel<ComicListViewModel>()

    private lateinit var binding : FragmentComicListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupObservers()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_comic_list, container, false)
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
                ComicListState.ShowSuccessView -> {
                    binding.viewFlipper.displayedChild = 0
                }
                ComicListState.ShowErrorView -> {
                    binding.viewFlipper.displayedChild = 1
                }
                ComicListState.ShowLoading -> {
                    binding.viewFlipper.visibility = View.INVISIBLE
                    binding.progressBar.visibility = View.VISIBLE
                }
                ComicListState.RemoveLoading -> {
                    binding.viewFlipper.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE
                }
            }
        })
        vm.comicsLiveData.observe(this, Observer {
            (binding.recyclerView.adapter as ComicListAdapter).list = it
        })
    }

    private fun setupRecyclerView() {
        binding.recyclerView.adapter =
            ComicListAdapter()

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