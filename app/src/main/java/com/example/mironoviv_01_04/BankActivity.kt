package com.example.mironoviv_01_04

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class BankActivity : AppCompatActivity() {

    private lateinit var Login: EditText //поздняя инициализация для элементов экрана
    private lateinit var Password: EditText
    private lateinit var LoginBut: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bank)

        Login = findViewById(R.id.editTextLogin) //находим элементы по id
        Password = findViewById(R.id.editTextPassword)
        LoginBut = findViewById(R.id.buttonLogin)

        LoginBut.setOnClickListener {//прописываем код слушателя кнопки по нажатию
            try { //На всякий случай, лишним не бывает
                val log = Login.text.toString() //берём текст из edittext и присваиваем переменным
                val pass = Password.text.toString()

                if (!log.isEmpty() && !pass.isEmpty()) { //проверка на пустые поля
                    if (log == "ects" && pass == "ects2024") {
                        val intent = Intent(this, CreditCalculatorActivity::class.java) //переход к другому экрану
                        startActivity(intent)
                    }
                    else
                    {
                        Toast.makeText(this, "Логин или пароль неверный", Toast.LENGTH_SHORT).show()
                    }
                }
                else
                {
                    Toast.makeText(this, "Введите логин и пароль", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this, "Ошибка", Toast.LENGTH_SHORT).show() //на случай ошибки
            }
        }
    }
}