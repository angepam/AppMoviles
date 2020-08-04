package unitec.pg.myapplication

import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.create

class ApiServicioPerfil {
    //Aqui declaramos la urlBase de nuestra api

    private val baseUrl="https://topo-unitec.heroku.com/"

    //La siguiente variable es el objeto retrofit para acceder al servicio
    private val api=Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(JacksonConverterFactory.create())
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .build()
        .create(ServicioPerfil::class.java)

    //Generamos una funcion para invocar a todos desde aqui
    //Si deseamos puedes mas adelante , invocar algo pero para el de get por id
    fun getPerfiles():Single<List<Perfil>>{
        return api.buscarTodos()
    }


}