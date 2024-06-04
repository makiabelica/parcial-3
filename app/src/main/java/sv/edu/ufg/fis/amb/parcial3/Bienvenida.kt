package sv.edu.ufg.fis.amb.parcial3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class Bienvenida : Fragment() {

    var usuario: String? = ""
    var contrasena: String? = ""
    lateinit var nombre: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val vista = inflater.inflate(R.layout.fragment_bienvenida, container, false)
        usuario = arguments?.getString("usuario")
        contrasena = arguments?.getString("contrasenia")

        nombre = vista.findViewById(R.id.txt_bienvenida)
        nombre.text = "Bienvenido(a) "+usuario


        return vista
    }
}