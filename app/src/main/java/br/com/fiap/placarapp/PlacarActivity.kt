package br.com.fiap.placarapp

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.ActivityNotFoundException
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_placar.*

class PlacarActivity : AppCompatActivity() {


    private lateinit var placarViewModel: PlacarViewModel

       override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_placar)

        placarViewModel = ViewModelProviders.of(this)
                .get(PlacarViewModel::class.java)

         placarViewModel.goalHome.observe(this, Observer {
             tvPlacarCasa.text = "$it"
         })

           placarViewModel.goalAway.observe(this, Observer {
               tvPlacarVisitante.text = "$it"
           })

           tvTimeCasa.text = intent.getStringExtra("TIME_CASA")
        tvTimeVisitante.text = intent.getStringExtra("TIME_VISITANTE")

        tvPlacarCasa.text = "${placarViewModel.goalHome}"
        tvPlacarVisitante.text = "${placarViewModel.goalAway}"

        btGolCasa.setOnClickListener {
            placarViewModel.goalHome()
        }

        btGolVisitante.setOnClickListener {
            placarViewModel.goalAway()
        }

        btZerarPlacar.setOnClickListener{
            placarViewModel.zerarPlacar()
        }

        btShare.setOnClickListener{
            placarViewModel.shareWhatsApp()
        }
           fun shareWhatsApp() {
            try{
                val whatsaAppIntent = Intent(Intent.ACTION_SEND)
                whatsaAppIntent.type = "text/plain"

                val message = "Oresultado do jogo entre ${tvTimeCasa.text} X " +
                        "${tvPlacarVisitante.text} foi ${placarViewModel.goalHome}" +
                        "a ${placarViewModel.goalAway}"

                whatsaAppIntent.putExtra(Intent.EXTRA_TEXT, message)
                startActivity(whatsaAppIntent)
            }catch (e: ActivityNotFoundException) {
                Toast.makeText(this,"Whats App não instalado", Toast.LENGTH_LONG).show()
            }
        }

    }
}
