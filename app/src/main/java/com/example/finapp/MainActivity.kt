package com.example.finapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.SimpleDateFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private var listaRecebida = mutableListOf<Transacao>()
    private lateinit var saldoTextView: TextView
    private lateinit var lastModTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        saldoTextView = findViewById(R.id.outputSaldoTextView)

        intent.getParcelableArrayListExtra<Transacao>("lista")?.let{
            listaRecebida = it.toMutableList()
        }

        atualizarTela()
    }
    private fun atualizarTela() {
        if (listaRecebida.isNotEmpty()) {

            val saldo = listaRecebida.sumOf { transacao ->
                when (transacao.Tipo.lowercase()) {
                    "crédito", "credito" -> transacao.valor
                    "débito", "debito" -> -transacao.valor
                    else -> 0.0
                }
            }

            saldoTextView.text = "R$ %.2f".format(saldo)

        } else {
            saldoTextView.text = "R$ 0,00"
        }
    }
    fun goToNovoLancamento(view: View){
        val intent = Intent(this, NovoLancamentoActivity::class.java)
        startActivity(intent)
    }

    fun goToExtrato(view: View){
        val intent = Intent(this, ExtratoActivity::class.java)
        intent.putParcelableArrayListExtra("lista", ArrayList(listaRecebida))
        startActivity(intent)
    }

    fun closeApp(view: View){
        finishAffinity()
    }
}