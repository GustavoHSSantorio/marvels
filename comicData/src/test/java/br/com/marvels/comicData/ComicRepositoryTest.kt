package br.com.marvels.comicData

import br.com.marvel.comicData.data.ComicRepository
import br.com.marvel.comicData.data.ComicRepositoryImp
import br.com.marvel.comicData.data.ComicService
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ComicRepositoryTest {

    private lateinit var repository: ComicRepository

    @MockK
    private lateinit var service: ComicService


    @Before
    fun setUp(){
        MockKAnnotations.init(this)

        repository = ComicRepositoryImp(service)
    }

    @Test
    fun `should get comics list`(){
        every { service.getComics(limit, offset) } returns Single.just(comicDataMarvelWrapper)

        repository.getComicsList(limit, offset)
            .test()
            .assertComplete()
            .assertValue(comicDataMarvelWrapper.data)

        verify { service.getComics(limit, offset) }
    }

    @Test
    fun `should get comics title begin letter`(){
        every { service.getComicsBeginLetter(limit, offset, name) } returns Single.just(comicDataMarvelWrapper)

        repository.getComicsTitleStartsWith(limit, offset, name)
            .test()
            .assertComplete()
            .assertValue(comicDataMarvelWrapper.data)

        verify { service.getComicsBeginLetter(limit, offset, name) }
    }

    @Test
    fun `should get character comics `(){
        every { service.getCharacterComics(characterId, limit, offset) } returns Single.just(comicDataMarvelWrapper)

        repository.getCharacterComics(characterId, limit, offset)
            .test()
            .assertComplete()
            .assertValue(comicDataMarvelWrapper.data)

        verify { service.getCharacterComics(characterId, limit, offset) }
    }

    @Test
    fun `should get character series`(){
        every { service.getCharacterSeries(characterId, limit, offset) } returns Single.just(seriesDataMarvelWrapper)

        repository.getCharacterSeries(characterId, limit, offset)
            .test()
            .assertComplete()
            .assertValue(seriesDataMarvelWrapper.data)

        verify { service.getCharacterSeries(characterId, limit, offset) }
    }
}