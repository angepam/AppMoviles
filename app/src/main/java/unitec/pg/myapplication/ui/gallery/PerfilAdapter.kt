package unitec.pg.myapplication.ui.gallery

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import kotlinx.android.synthetic.main.perfil_item.view.*
import unitec.pg.myapplication.Perfil
import unitec.pg.myapplication.R

//La clase PerfilViewHolder es realmente la clase que sirve de enlace entre el modelo y la vista
class PerfilAdapter(val perfillist:ArrayList<Perfil>): RecyclerView.Adapter<PerfilAdapter.PerfilViewHolder>() {


    //Generamos esa clase , que en este patro MVVM debe ser como clase interna.
    class PerfilViewHolder(var view: View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent:  ViewGroup, ViewType: Int): PerfilViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val view =inflater.inflate(R.layout.perfil_item,parent,false)
        return PerfilViewHolder(view)
    }

    //Aquie ponemos el perfil adapter de la actualizacion
    fun actualizarPerfilList(nuevaListaPerfil:List<Perfil>){
        //Aqui viene la actualizacion automatica
        perfillist.clear()
        perfillist.addAll(nuevaListaPerfil)
        //este metodo final notifica al Observer que esta en la otra clase la notificacion
        //de que hay un actualizacion
        notifyDataSetChanged()

    }

    override fun getItemCount()= perfillist.size



    override fun onBindViewHolder(holder: PerfilViewHolder, position: Int) {

        //Bind en ingles es ENLAZAR, este metodo NOS ENLAZA CADA VISTA A TU MODEL
        //Aqui iran el nombre , el paterno,email,edad del perfil
        //Solamente pondremos por el momento en nombre, porque en el list fake que
        //pusimos pues solamente pusimos
        holder.view.nombre_item.text=perfillist[position].nombre
        //Para cada uno de tus id´s de tu perfil_item tendrias que agregar algo similar



    }


}