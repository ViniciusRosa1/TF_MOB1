package com.example.finapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class   NovoLancamentoActivity : AppCompatActivity() {
    private lateinit var inputValorText: EditText
    private lateinit var inputTextDate: EditText
    private lateinit var inputDescText: EditText
    private lateinit var radioGroup2: RadioGroup

    companion object {
        var listaTransacoes = mutableListOf<Transacao>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_novo_lancamento)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        inputValorText = findViewById<EditText>(R.id.inputValorText)
        inputTextDate = findViewById<EditText>(R.id.inputTextDate)
        inputDescText = findViewById<EditText>(R.id.inputDescText)
        radioGroup2 = findViewById<RadioGroup>(R.id.radioGroup2)
    }

    fun saveTransacao(view: View){

        val selectedRadioButton = radioGroup2.checkedRadioButtonId
        if(selectedRadioButton == -1){
            Toast.makeText(this, "Selecione o tipo da transação!", Toast.LENGTH_SHORT).show()
            return
        }

        val radioButton = findViewById<RadioButton>(selectedRadioButton)
        val tipo = radioButton.text.toString()

        val valor = inputValorText.text.toString().toDoubleOrNull()
        if(valor == null){
            Toast.makeText(this, "Informe um valor válido!", Toast.LENGTH_SHORT).show()
            return
        }

        val descricao = inputDescText.text.toString()
        if( descricao.isEmpty()){
            Toast.makeText(this, "Informe uma descrição!", Toast.LENGTH_SHORT).show()
            return
        }

        if(descricao.length > 20){
            Toast.makeText(this, "Descrição deve ter 20 caracteres!", Toast.LENGTH_SHORT).show()
            return
        }

        val dateStr = inputTextDate.text.toString()
        val formatarData = SimpleDateFormat("dd/MM/yyy", Locale.getDefault())
        val data: Date = formatarData.parse(dateStr) ?: Date()

        val novaTransacao = Transacao(valor, tipo, descricao, data)

        listaTransacoes.add(novaTransacao)

        val intent = Intent(this, MainActivity::class.java)
        intent.putParcelableArrayListExtra("lista", ArrayList(listaTransacoes))
        startActivity(intent)
        finish()
    }

    fun voltarButton(view: View){

        finish()
    }
}