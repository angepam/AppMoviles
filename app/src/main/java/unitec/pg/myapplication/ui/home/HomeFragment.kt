package unitec.pg.myapplication.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.support.v4.app.Fragment
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.design.widget.TextInputEditText
import android.widget.Button
import unitec.pg.myapplication.R
import unitec.pg.myapplication.TareaGuardarPerfil

/*Un fragmento es un elemento de android que esta asociado a una Activity y solo una y tambien
a un layout
En el patron de diseño MVP un Fragmento viene a se la letra P es decir el Presenter
junto con todos los activities y el layout asociado es la View y el Modelo en este
caso esta simple es la TareaAsicronica
*/
class HomeFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_home, container, false)
      //Ese valor constante que aqui se genero como root nos apoyamos para
        //Invocar nuestras componentes virtuales e inyectarlas en el model
       var guardar= root.findViewById<Button>(R.id.guardar)

     //Previamente invocamos el evento (funcional ) del boton y en el ivocamos los valores

        guardar.setOnClickListener {
            var txtN= root.findViewById<TextInputEditText>(R.id.txtN)
            var txtP= root.findViewById<TextInputEditText>(R.id.txtP)
            var txtE=root.findViewById<TextInputEditText>(R.id.txtE)

            //lo siguiente es lo mas interesante !! Presentar las vistas al modelo
            //Es lo que les deje de investigacion
            //Creamos un objeto de la tarea asincronica e inicializamos sus argumentos
            TareaGuardarPerfil(txtN,txtP,txtE,root.context).execute()

        }
        return root
    }
}