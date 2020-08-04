package unitec.pg.myapplication.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.support.v4.app.Fragment
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_gallery.*
import unitec.pg.myapplication.R
import java.lang.Error

class GalleryFragment : Fragment() {
      //Empezamos declarando dos atributos , el primero es de tipo viewmodel y el segundo
    //el de tipo adapter recien creado

    private lateinit var viewModel: GalleryViewModel
    private val perfilListAdapter=PerfilAdapter(arrayListOf())


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_gallery, container, false)
        return root
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Aqui hacemos el enlace de nuestros atributos
        viewModel=ViewModelProviders.of(this).get(GalleryViewModel::class.java)
        //Inmediatamente le vamos a decir que se conecte al back-end
        viewModel.refrescar()

        //Hasta aqui en ningun momento el layout perfil_item esta ligado al fragment_gallery
        //Primero va el enlace. ESTE ES EL ENLACE ENTRE LOS DOS LAYOUT EL QUE
        //CONTIENE EL RECYCLERVIEW Y EL DE LOS ITEMS Y SE HACE POR MEDIO DEL ADAPTER
        perfiles.apply {
            layoutManager=LinearLayoutManager(context)
            adapter=perfilListAdapter
        }

        //Aqui vamos a invocar un metodo que de manera automatica se lleva a cabo la adaptacion
        //cada que el usuario le de click al menu donde esra el fragmento de la gallery
        //este paso es equivalente de cuando se meten a la app de facebook y se refresca
        //y cambia los datos en automatico
        //Activamos el swipe
        swipe.setOnRefreshListener {
            perfiles.visibility=View.GONE
            error.visibility=View.GONE
            cargando.visibility=View.VISIBLE
            viewModel.refrescar()
            swipe.isRefreshing=false
        }
        observarViewModel()



    }

    //El ultimo y nos vamos este metodo implementa a su vez un patron de diseño reativo
    // que se llama Observer

    fun observarViewModel(){
        //Primero invocamos nuestro atributo viewmodel
        viewModel.lista_perfiles.observe(this, Observer {perfi ->
            perfi?.let {
                perfiles.visibility=View.VISIBLE
                perfilListAdapter.actualizarPerfilList(perfi)

            }

        })

        //Seguimos con el error_internet
        viewModel.errorInternet.observe(this, Observer {errorsito ->
            errorsito?.let {
                error.visibility=if(it)View.VISIBLE else View.GONE
            }

        })

        //Terminamos con cargando
        viewModel.cargando.observe(this, Observer {carga ->
            carga?.let {
                cargando.visibility=if(it)View.VISIBLE else View.GONE
            }

        })

    }

    }
