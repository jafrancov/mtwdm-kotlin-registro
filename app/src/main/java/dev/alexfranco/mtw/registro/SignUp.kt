package dev.alexfranco.mtw.registro

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.DatePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import dev.alexfranco.mtw.registro.db.User
import dev.alexfranco.mtw.registro.db.UserDB
import dev.alexfranco.mtw.registro.helpers.DoAsync
import kotlinx.android.synthetic.main.activity_sign_up.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class SignUp : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        btnFecha.setOnClickListener {
            val dpd = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view: DatePicker, mYear: Int, mMonth: Int, mDay: Int ->
                    txtFecha.setText("" + mDay + "/" + (mMonth + 1) + "/" + mYear)
                },
                year,
                month,
                day
            )
            dpd.show()
        }

        btnCrearCuenta.setOnClickListener {
            val name = etName.text.toString()
            val phone = etPhone.text.toString()
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()
            val fecha = SimpleDateFormat("dd/MM/yyyy").parse(txtFecha.text.toString())
            if (name == "" || phone == "" || email == "" || password == "") {
                Toast.makeText(this, "Por favor llena todos los campos", Toast.LENGTH_LONG).show()
            } else {
                val objUser = User(
                    name = name,
                    phone = phone,
                    email = email,
                    password = password,
                    updated = fecha
                )
                DoAsync {
                    UserDB.getInstance(this)!!.userDao().insertUser(objUser)
                }.execute()
                Toast.makeText(this, "Se registró al usuario $name", Toast.LENGTH_LONG).show()
            }
        }

        btnIniciarSesion.setOnClickListener {
            val intent = Intent(this@SignUp, Login::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_signup, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.itmUsers -> {
                val intUsers = Intent(this, UsersList::class.java)
                startActivity(intUsers)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
