package br.com.cotemig.calculasalarioliquido

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.blackcat.currencyedittext.CurrencyEditText
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var editTextSalario = findViewById<CurrencyEditText>(R.id.editTextSalario)
        var buttonCalcula = findViewById<Button>(R.id.buttonCalcula)

        buttonCalcula.setOnClickListener {
            Log.i("Salário", editTextSalario.text.toString())
            Log.i("Double", formatToDouble(editTextSalario.text.toString()).toString())
            calculaImpostoRenda(formatToDouble(editTextSalario.text.toString()))
        }
    }

    fun formatToDouble(value: String) : Double {
        val f = NumberFormat.getCurrencyInstance()
        return f.parse(value).toDouble()
    }

    fun calculaImpostoRenda(salario: Double) {

        var imposto = 0.0
        var liquido = salario

        if (salario > 4664.68) {
            imposto = salario * 0.27
            liquido = (salario - imposto) + 869.36
        } else if (salario >= 3751.06) {
            imposto = salario * 0.225
            liquido = (salario - imposto) + 636.13
        } else if (salario >= 2826.66) {
            imposto = salario * 0.15
            liquido = (salario - imposto) + 354.80
        } else if (salario >= 1903.99) {
            imposto = salario * 0.075
            liquido = (salario - imposto) + 142.80
        }

        var textViewSalarioLiquido = findViewById<TextView>(R.id.textViewSalarioLiquido)
        var textViewImposto = findViewById<TextView>(R.id.textViewImposto)

        val f = NumberFormat.getCurrencyInstance()

         textViewSalarioLiquido.text = resources.getString(R.string.salario_liquido, f.format(liquido))
         textViewImposto.text = resources.getString(R.string.imposto, f.format(imposto))
    }
}