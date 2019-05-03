package br.com.fiap.placarapp

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class PlacarViewModel : ViewModel() {
    var goalHome: MutableLiveData<Int> = MutableLiveData()
    var goalAway: MutableLiveData<Int> = MutableLiveData()

    init {
        goalHome.value = 0
        goalAway.value = 0
    }

    fun goalHome() {
        goalHome.value = goalHome.value?.plus(+1)
    }

    fun goalAway() {
        goalAway.value = goalHome.value?.plus(+1)
    }

    private fun restartGame() {
        goalHome.value = 0
        goalAway.value = 0
    }

    fun zerarPlacar(){
        restartGame()
    }

}