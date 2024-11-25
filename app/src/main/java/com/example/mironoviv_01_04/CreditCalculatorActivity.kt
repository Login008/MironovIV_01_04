package com.example.mironoviv_01_04

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class CreditCalculatorActivity : AppCompatActivity() {

    private lateinit var viewAmount: TextView //поздняя инициализация элементов экрана
    private lateinit var seekBarAmount: SeekBar
    private lateinit var editTerm: EditText
    private lateinit var calculateBut: Button
    private lateinit var payment : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_credit_calculator)

        viewAmount = findViewById(R.id.textViewSelectedAmount) //поиск элементов с экрана
        seekBarAmount = findViewById(R.id.seekBarLoanAmount)
        editTerm = findViewById(R.id.editTextLoanTerm)
        calculateBut = findViewById(R.id.buttonCalculate)
        payment = findViewById(R.id.textViewPayment)

        // Меняем слушателя, чтобы текствью менялся вместе с изменением значения seekBar
        seekBarAmount.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                viewAmount.text = "Сумма кредита: $progress"
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        calculateBut.setOnClickListener {//ставим слушатель
            val amount = seekBarAmount.progress //присваивание значений переменным
            val term = editTerm.text.toString()

            if (term.isEmpty()) { //проверка на пустоту
                Toast.makeText(this, "Введите срок кредита", Toast.LENGTH_SHORT).show()
            } else {
                // Преобразование срока кредита в число
                try {
                    var termForMonths = term.toInt()
                    var sum = 0.0

                    var s1 = amount / termForMonths + amount * 0.059 //расчёт платежа
                    if (termForMonths <= 12 && termForMonths > 0) {
                        sum = s1
                    } else if (termForMonths > 12 && termForMonths <= 24) {
                        s1 = amount / 12 + amount * 0.059
                        sum = s1 + amount / termForMonths + (amount - s1 * 12) * 0.051
                    } else if (termForMonths > 24) {
                        s1 = amount / 12 + amount * 0.059
                        sum = s1 + amount / termForMonths + (amount - s1 * 24) * 0.042
                    } else {
                        Toast.makeText(this, "Введите положительное число", Toast.LENGTH_LONG).show()
                        return@setOnClickListener
                    }

                    // Ставим значение в текствью
                    payment.setText("ежемесячный платёж:\n$sum")
                    // Задержка перед переходом к итогу
                    Handler().postDelayed({
                        val intent = Intent(this, SummaryActivity::class.java) //переход к итогу
                        intent.putExtra("amount", amount)  // Передаем сумму кредита
                        intent.putExtra("term", termForMonths)  // Передаем срок кредита
                        intent.putExtra("payment", sum)  // Передаем ежемесячный платеж
                        startActivity(intent)
                        finish() // Закрываем текущую вкладку
                    }, 3000) // Задержка в 3 секунды
                }
                catch (e:Exception)
                {
                    Toast.makeText(this, "Введите только число", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    fun GoBack(view: View) {
        val intent = Intent(this, BankActivity::class.java) //переход к итогу
        startActivity(intent)
    }
}