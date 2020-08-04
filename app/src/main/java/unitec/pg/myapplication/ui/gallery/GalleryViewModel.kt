package unitec.pg.myapplication.ui.gallery




import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import unitec.pg.myapplication.ApiServicioPerfil
import unitec.pg.myapplication.Perfil

//El ViewModel se encarga de obtener la informacion del Back-end
//y prepararla para la vista. En el caso del RecyclerView vamos a tener
//que apoyarnos de las clases Adaptadoras. Su funcion de ellas es "adaptar" el modelo
//a la vista(perfil_item)
class GalleryViewModel : ViewModel() {

    val lista_perfiles= MutableLiveData<List<Perfil>>()
    val errorInternet=MutableLiveData<Boolean>()
    val cargando=MutableLiveData<Boolean>()
    //Este atributo es para el manejo de memoria en arquitectura reactiva
    private val dispose=CompositeDisposable()

    //Declaramos el atributo del apiServicioPerfil que ya es reactivo
    private val apiServicioPerfil=ApiServicioPerfil()


    private fun obtenerPerfiles(){
        //Aqui vamos a llamar nuestro servicio rest, por el momento vamos a
        //hacer un fake de perfiles para primero, verificar que funcione
    }

    fun refrescar(){
       //Inicialmente queremos que el error de internet sea falso
        errorInternet.value=false

    }
//Este nuevo metodo es el de la conexion asincronica reactiva
    fun obtenerListadoRemotamente(){
    cargando.value=true
    //Aqui viene el thread asincronico REACTIVO HUUUYY QUE MIEDO TODO MUUUUY NUEVO

    dispose.add(
        apiServicioPerfil.getPerfiles()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object:DisposableSingleObserver<List<Perfil>>(){
                override fun onSuccess(ListaPer: List<Perfil>?){
                    lista_perfiles.value=listaPer
                    cargando.value=false
                }
                override fun onError(e:Throwable?){
                    errorInternet.value=true
                    cargando.value=true
                }
            })


}
}