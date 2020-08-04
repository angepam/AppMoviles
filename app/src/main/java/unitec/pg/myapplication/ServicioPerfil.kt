package unitec.pg.myapplication


import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.http.*


//Esta interfaz es el extremo del front-end para comunicarte con el back-end
//Tu extremo de comunicacion en el back-end es el ControladorPerfil
//De tal manera que POR CADA SERVICIO EN EL CONTROLADOR DEBE HABER UN SERVICIO
//ESPEJO AQUI. Es decir tus 5 operaciones basicas CRUD de la entidad Perfil
interface ServicioPerfil {

    //Empezamos con el Post que es el de guardar
    @POST("api/perfil")
    fun guardar(@Body perfil:Perfil):Call<Estatus>

    //Sigue buscar todos
    @GET("api/perfil")
    fun buscarTodos(): Single<List<Perfil>>

    //Sigue el de buscar por id
    @GET("api/perfil/{id}")
    fun buscarTodos(@Path("id")id:String?): Call<Perfil>

    //Sigue actualizar
    @PUT("api/perfil")
    fun actualizar(@Body perfil:Perfil):Call<Estatus>

    //Ejercicio Vean la estructura del back-end y escriban solitos como quedaria el
    //Delete
    //Sigue borrar por id
    @DELETE("api/perfil/{id}")
    fun borrar(@Path("id")id:String?): Call<Estatus>


}