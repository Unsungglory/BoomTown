package com.example.boomtown.display

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.boomtown.network.Character
import com.example.boomtown.network.swapi
import kotlinx.coroutines.launch

class DisplayViewModel : ViewModel() {

    //    To display errors and status checks
    private val _status = MutableLiveData<String>()

    private val _characters = mutableListOf<Character>()
    var countCharacters = 0
    val status: LiveData<String> = _status
    //start process of gathering and parsing chars on init
    init {
        //start process of gathering and parsing chars on init
        getStarwarsPeople()
    }

    /**
     * getStarwarsPeople starts a coroutine to start retrofit and collect the list of people
     * this routine takes a while, the screen is blank for some time before a display is presented
     */
    private fun getStarwarsPeople(){
        viewModelScope.launch{
            _status.value = "Character Loading"
            try{
                val count = swapi.retrofitService.getPeopleList().count.toInt()

                for (i in 1..count){
                    try {
                        _characters.add(swapi.retrofitService.getPerson(i))
                    }catch(e:Exception){
                        Log.d("Index  of list is ", "$i")
                    }
                }
                val index = 16
                _status.value = "Name: ${_characters[index].name} \n " +
                        "Number of Films: ${_characters[index].numFilms.toString()}\n" +
                        "height: ${_characters[index].height} \n" +
                        "weight: ${_characters[index].mass} \n"

//                Log.d("Size of list is ", "${listResult}")
            } catch(e:Exception){
                _status.value = "Failure: ${e.message}"
            }
            countCharacters = _characters.size
        }
    }
}