package br.com.marvel.characterData.data

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
class CharacterRepositoryTest {
    private lateinit var repository: CharacterRepository

    @MockK
    private lateinit var service: CharacterService

    @Before
    fun setUp(){
        MockKAnnotations.init(this)

        repository = CharacterRepositoryImp(service)
    }

    @Test
    fun `should get character by id`(){
        every { service.getCharacter(characterId)} returns Single.just(characterDataMarvelWrapper)

        repository.getCharacter(characterId)
            .test()
            .assertComplete()
            .assertValue(characterDataMarvelWrapper.data)

        verify { service.getCharacter(characterId) }
    }

    @Test
    fun `should get character list by name`(){
        every { service.getCharactersBeginLetter(limit, offset, name)} returns Single.just(characterDataMarvelWrapper)

        repository.getCharacterNameStartsWith(limit, offset, name)
            .test()
            .assertComplete()
            .assertValue(characterDataMarvelWrapper.data)

        verify { service.getCharactersBeginLetter(limit, offset, name) }
    }

    @Test
    fun `should get character list`(){
        every { service.getCharacters(limit, offset)} returns Single.just(characterDataMarvelWrapper)

        repository.getCharacterList(limit, offset)
            .test()
            .assertComplete()
            .assertValue(characterDataMarvelWrapper.data)

        verify { service.getCharacters(limit, offset) }
    }
}