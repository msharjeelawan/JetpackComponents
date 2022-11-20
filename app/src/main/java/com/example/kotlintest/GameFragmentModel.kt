package com.example.kotlintest

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameFragmentModel: ViewModel() {
    private var secrotWordList = arrayOf("Muhammad","Sharjeel","Noor")
    private var secrotWord = secrotWordList.random().uppercase()
    var secrotWordDisplay = MutableLiveData<String>("")
    var character = ""
    private var correctLetters = ""
    var incorrectLetters = MutableLiveData<String>("")
    var life = MutableLiveData(8)
    var message = MutableLiveData("")

    init {
        secrotWordDisplay.value = makeSecrotWordDisplay()
    }

    fun finishGame(){
        if (character.isEmpty()){
           return
        }
        guessLetter(character)
    }

   private fun guessLetter(letter:String){
        var isContain = secrotWord.contains(letter)
        if (isContain){
            correctLetters += letter
            secrotWordDisplay.value = makeSecrotWordDisplay()
        }else{
            if (incorrectLetters.value!=null)
                incorrectLetters.value += letter
            life.value = life.value?.minus(1)
        }

        if (isWon() || isLoss()){

        }
    }

    private fun makeSecrotWordDisplay():String{
        var display=""
        secrotWord.forEach {
            display += checkLetter(it.toString())
        }
        return  display
    }

    private fun checkLetter(str:String):String{
        return when(correctLetters.contains(str)){
            true -> str
            false -> "_"
        }
    }


   private fun isWon():Boolean{
        if (secrotWord.equals(secrotWordDisplay.value,true)){
            message.value = "You Won, Guess Word is $secrotWord"
            return true
        }
        return false
    }

   private fun isLoss():Boolean{
       var life = life.value ?: 0 <= 0
       Log.v("loss",life.toString())
        if (life){
            message.value = "You Loss, Guess Word is $secrotWord"
            return true
        }
        return  false
    }



}