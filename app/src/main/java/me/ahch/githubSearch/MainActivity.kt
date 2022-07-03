package me.ahch.githubSearch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import me.ahch.githubsearch.GithubSearchApp

@OptIn(InternalCoroutinesApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GithubSearchApp()
        }

        val array = listOf("mohsen", "hossein", "mehdi")
        var array2= mutableListOf<String>()



        lifecycleScope.launch {
            array2.addAll(array.map { data ->
                async {
                    delay(5000)
                    data+"1"
                }
            }.map {
                it.await()
            })

            println("ahchq "+array2)


            /*
                array.forEach {
                   val result = async {
                       delay(3000)
                        it+"1 "
                    }
                    array2.add(result.await())
                }*/
        }


    }
}
