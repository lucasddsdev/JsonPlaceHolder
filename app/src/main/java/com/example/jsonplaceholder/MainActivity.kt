package com.example.jsonplaceholder

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.jsonplaceholder.databinding.ActivityMainBinding
import com.example.jsonplaceholder.model.PostagemApi
import com.example.jsonplaceholder.services.Mensagem
import com.example.jsonplaceholder.services.Postagem
import com.example.jsonplaceholder.services.RetrofitHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.create

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val retrofit by lazy {
        RetrofitHelper.retrofit
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)


        binding.btnIniciar.setOnClickListener(){

            CoroutineScope(Dispatchers.IO).launch {
                recuperarPostagens()
            }
        }

    }

    private suspend fun recuperarMensagem(){

        var retorno: Response<List<Mensagem>>? = null

        val postagemSelecionada = 2

        try {

            val postagemApi = retrofit.create(PostagemApi::class.java)
            retorno = postagemApi.recuperarMensagem(postagemSelecionada)

        }catch (e: Exception){
            e.printStackTrace()
            Log.i("info_jsonplace", "erro ao recuperar postagem")
        }


        if (retorno != null){
            if (retorno.isSuccessful){
                val listaDeMensagens = retorno.body()


               listaDeMensagens?.forEach{ postagem ->
                    val id = postagem.id
                    val msg = postagem.name
                    Log.i("info_jsonplace_mensagem", "$id - $msg")
               }



            }
        }
    }

    private suspend fun recuperarPostagens(){

        var retorno: Response<Postagem>? = null

        val postagemSelecionada = 2

        try {

            val postagemApi = retrofit.create(PostagemApi::class.java)
            retorno = postagemApi.recuperarPostagens(postagemSelecionada)

        }catch (e: Exception){
            e.printStackTrace()
            Log.i("info_jsonplace", "erro ao recuperar postagem")
        }


        if (retorno != null){
            if (retorno.isSuccessful){
                val listaDePostagens = retorno.body()
                val id = listaDePostagens?.id
                val title = listaDePostagens?.title
                Log.i("info_jsonplace", "$id - $title")

//                listaDePostagens?.forEach{ postagem ->
//                    val id = postagem.id
//                    val title = postagem.title
//                    Log.i("info_jsonplace", "$id - $title")
//                }

            }
        }
    }

}