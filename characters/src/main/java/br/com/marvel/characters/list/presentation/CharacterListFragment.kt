package br.com.marvel.characters.list.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import br.com.marvel.base.BaseFragment
import br.com.marvel.characters.R
import br.com.marvel.characters.databinding.FragmentCharacterListBinding

class CharacterListFragment : BaseFragment() {

    private val vm by appViewModel<CharacterListViewModel>()
    private lateinit var binding: FragmentCharacterListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_character_list, container, true)
        binding.lifecycleOwner = this
        binding.vm = vm

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
