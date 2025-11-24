package com.example.finapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.finapp.NovoLancamentoActivity.Companion.listaTransacoes
import java.text.SimpleDateFormat
import java.util.Locale

class ExtratoActivity : AppCompatActivity() {
    private  lateinit var listView: ListView
    private var listaRecebida = mutableListOf<Transacao>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_extrato)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        listView = findViewById<ListView>(R.id.listView)

        intent.getParcelableArrayListExtra<Transacao>("lista")?.let{
            listaRecebida = it.toMutableList()
        }
        val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val listaFormatada = listaRecebida.map { transacao ->
            "R$ ${"%.2f".format(transacao.valor)}\n" +
                    "${transacao.Tipo} • ${transacao.Descricao} • ${formatter.format(transacao.Data)}"
        }

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            listaFormatada
        )

        listView.adapter = adapter
    }
    fun voltarButton(view: View) {

        finish()
    }
}