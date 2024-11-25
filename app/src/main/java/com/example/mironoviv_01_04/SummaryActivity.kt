package com.example.mironoviv_01_04

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SummaryActivity : AppCompatActivity() {
    private lateinit var summaryText: TextView  //поздняя инициализация элементов с экрана
    private lateinit var summaryText1: TextView
    private lateinit var summaryText2: TextView
    private lateinit var registerBut: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_summary)

        summaryText = findViewById(R.id.textViewSummary)  //ищем их по айди
        summaryText1 = findViewById(R.id.textViewSummary1)
        summaryText2 = findViewById(R.id.textViewSummary2)
        registerBut = findViewById(R.id.buttonRegister)

        val amount = intent.getIntExtra("amount", 0)  //получаем значения из прошлой активности
        val term = intent.getIntExtra("term", 0)
        val payment = intent.getDoubleExtra("payment", 0.0)

        summaryText.text = "Сумма кредита: $amount" //задаём текст нашим текствьюшкам
        summaryText1.text = "срок кредита: \n$term месяцев"
        summaryText2.text = "ежемесячный платеж: \n$payment"

        registerBut.setOnClickListener {//слушатель на кнопку регистрации
            val intent = Intent(this, BankActivity::class.java) //возвращаемся к активности с авторизацией
            startActivity(intent)
        }
    }
}