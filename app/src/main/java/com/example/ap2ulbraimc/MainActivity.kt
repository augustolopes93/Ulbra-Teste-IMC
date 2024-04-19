package com.example.ap2ulbraimc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.accessibility.AccessibilityEvent
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout
import java.text.MessageFormat



class MainActivity : AppCompatActivity() {

    private lateinit var btnCalc : Button
    private lateinit var editNome: EditText
    private lateinit var editPeso: EditText
    private lateinit var editAltura: EditText
    private lateinit var layoutEditNome: TextInputLayout
    private lateinit var layoutEditPeso: TextInputLayout
    private lateinit var layoutEditAltura: TextInputLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnCalc = findViewById(R.id.btnCalc)
        editNome = findViewById(R.id.editNome)
        editPeso = findViewById(R.id.editPeso)
        editAltura =findViewById(R.id.editAltura)
        layoutEditNome = findViewById(R.id.layoutEditNome)
        layoutEditPeso = findViewById(R.id.layoutEditPeso)
        layoutEditAltura = findViewById(R.id.layoutEditAltura)

        btnCalc.setOnClickListener(View.OnClickListener {

            if( validarCamposBasicos()){
                val name= editNome.text.toString()
                val peso= editPeso.text.toString().toFloat()
                val altura= editAltura.text.toString().toFloat()
                val imc = IMC(name,peso,altura)
                val intent = Intent(this,ResultadoActivity::class.java)
                intent.putExtra("value", imc)
                startActivity(intent)
            }

        })
        editNome.addTextChangedListener(clearErrorMessage(layoutEditNome))
        editAltura.addTextChangedListener(clearErrorMessage(layoutEditAltura))
        editPeso.addTextChangedListener(clearErrorMessage(layoutEditPeso))

        btnCalc.setOnLongClickListener(){
            Toast.makeText(this,"Clique longo no botão",Toast.LENGTH_LONG).show()
            true
        }
    }


    private fun exibirMensagemErro(editText: EditText, textViewMessage: TextInputLayout, mensagem: String ) {
        textViewMessage.error = mensagem
        editText.sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_FOCUSED)
        editText.requestFocus();
    }

    fun exibirMensagemErroNome(){
        val mensagem = formatarMensagem("Campo")
        exibirMensagemErro(editNome, layoutEditNome, mensagem)
    }

    fun exibirMensagemErroPeso(){
        val mensagem = formatarMensagem("Campo")
        exibirMensagemErro(editPeso, layoutEditPeso, mensagem)
    }

    fun exibirMensagemErroAltura(){
        val mensagem = formatarMensagem("Campo")
        exibirMensagemErro(editAltura, layoutEditAltura, mensagem)
    }

    private fun validarCamposBasicos(): Boolean {
        Log.e("ERRO", editNome.toString()+"dsbhfkjsdhf")
        clearErrorMessage(layoutEditNome)
        val tam =editNome.text.toString().length
        if (isEmpty(editNome.text.toString()) || tam < 3) {
            exibirMensagemErroNome()
            return false
        }
        clearErrorMessage(layoutEditPeso)

        if (isEmpty(editPeso.text.toString())) {
            exibirMensagemErroPeso()
            return false
        }
        clearErrorMessage(layoutEditAltura)

        if (isEmpty(editAltura.text.toString())) {
            exibirMensagemErroAltura()
            return false
        }
        return true
    }

    private fun isEmpty(valor: String) = valor == ""

    private fun formatarMensagem(campo:String) : String{
        val message = "Informe o campo"
        return  MessageFormat.format(message, campo)
    }

    private fun clearErrorMessage(text:TextInputLayout): TextWatcher {
        return object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int ) { }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int ) {
                text.error = ""
            }
        }
    }
}


