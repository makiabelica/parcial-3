package sv.edu.ufg.fis.amb.parcial3


import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.findViewTreeViewModelStoreOwner


class MainActivity : AppCompatActivity() {
    lateinit var boton: Button
    lateinit var usuario: EditText
    lateinit var pass: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //SE EJECUTA EL FRAGMENTO PARA QUE SEA VISIBLE AL USUARIO
        supportFragmentManager?.commit {
            setReorderingAllowed(true)
            replace<Login>(R.id.fragment_container_view)
            addToBackStack("replacement")
        }

        //SE ASIGNA A LA CLASE BUTTON EL OBJETO CREADO EN EL XML
        boton = findViewById(R.id.btn_iniciar_sesion)



        //SE MANTIENE LA ESCUCHA DEL BOTÓN
        boton.setOnClickListener(View.OnClickListener {

            //SE ASIGNA LOS OBJETOS A LA CLASE CORRESPONDIENTE
            usuario = findViewById(R.id.txt_usuario)
            pass = findViewById(R.id.txt_password)

            if(pass.getText().toString() != "Pa55w0rd"){

                Toast.makeText(this, "NO SE RECONOCEN LAS CREDENCIALES", Toast.LENGTH_SHORT).show();

            }else {

                //SE CREA UN OBJETO PARA MANIPULACIÓN DE DATOS
                val bundle = Bundle()

                //SE ASIGNAN LOS VALORES
                bundle.putString("usuario", usuario.getText().toString())
                bundle.putString("contrasenia", pass.getText().toString())

                //SE INICIA LA TRANSACCIÓN
                val transaccion = this.supportFragmentManager.beginTransaction()
                //SE CREA UN NUEVO OBJETO DE FRAGMENT
                val segundoFragmento = Bienvenida()

                //SE ASIGNAN VALORES A LA VARIABLE GLOBAL ARGUMENTS
                segundoFragmento.arguments = bundle

                //SE REALIZAN LAS ACCIONES CORRESPONDIENTES
                transaccion.replace(R.id.fragment_container_view, segundoFragmento)
                transaccion.addToBackStack(null)
                transaccion.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                transaccion.commit()

                boton.text = "Botón deshabilitado"
                boton.isEnabled = false
                boton.isClickable = false
                boton.setBackgroundColor(boton.getContext().getResources().getColor(R.color.deshabilitado))
            }
        })

    }

}