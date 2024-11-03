package com.coolkosta.effectivemobiletestapp

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.coolkosta.effectivemobiletestapp.kotlin.AnyList
import com.coolkosta.effectivemobiletestapp.kotlin.ShakerSortAlgorithm
import com.coolkosta.effectivemobiletestapp.kotlin.SomeListForShakerAlgorithm
import com.coolkosta.effectivemobiletestapp.kotlin.getIntList

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val list = AnyList.list.getIntList()
        Log.d("ActivityListInt", "$list")

        val firstExampleShakerSort =
            ShakerSortAlgorithm.sort(SomeListForShakerAlgorithm.firstExample)
        val secondExampleShakerSort =
            ShakerSortAlgorithm.sort(SomeListForShakerAlgorithm.secondExample)
        val thirdExampleShakerSort =
            ShakerSortAlgorithm.sort(SomeListForShakerAlgorithm.thirdExample)

        Log.d("ShakerSort", "this is first example: $firstExampleShakerSort")
        Log.d("ShakerSort", "this is second example: $secondExampleShakerSort")
        Log.d("ShakerSort", "this is third example: $thirdExampleShakerSort")

        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}