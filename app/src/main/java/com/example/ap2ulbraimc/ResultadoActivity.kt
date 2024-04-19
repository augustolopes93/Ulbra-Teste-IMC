package com.example.ap2ulbraimc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView


class ResultadoActivity : AppCompatActivity() {

    private lateinit var titleDensidadeCorporal: TextView
    private lateinit var textViewHeaderDensidadeCorporal: TextView
    private lateinit var textViewPeso: TextView
    private lateinit var textViewResultadoDensidadeCorporal: TextView
    private lateinit var textViewAltura: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado)

        titleDensidadeCorporal = findViewById(R.id.titleDensidadeCorporal)
        textViewHeaderDensidadeCorporal = findViewById(R.id.textViewHeaderDensidadeCorporal)
        textViewPeso = findViewById(R.id.textViewPeso)
        textViewResultadoDensidadeCorporal= findViewById(R.id.textViewResultadoDensidadeCorporal)
        textViewAltura = findViewById(R.id.textViewAltura)

        val imc = intent.getParcelableExtra<IMC>("value")
        titleDensidadeCorporal.text = imc!!.nome.toString()
        textViewHeaderDensidadeCorporal.text = imc.calcular()
        textViewResultadoDensidadeCorporal.text = "Seu IMC: " + imc.imc.toString().substring(0, 5)
        textViewPeso.text = "Seu peso: " +  imc.peso.toString() + " kg"
        textViewAltura.text = "Sua altura: " + imc.altura.toString().substring(0, 3) + " cm"
    }
}
