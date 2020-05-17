package br.com.marvel.characters.main.presentation

import br.com.marvel.base.BaseViewModel
import br.com.marvel.network.MainScheduler
import io.reactivex.Scheduler
import javax.inject.Inject

class MainCharactersViewModel @Inject constructor(
    @MainScheduler private val mainScheduler: Scheduler,
    @MainScheduler private val ioScheduler: Scheduler
) : BaseViewModel()
