package dev.alexfranco.mtw.registro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.alexfranco.mtw.registro.adapters.UserAdapter
import dev.alexfranco.mtw.registro.db.User
import dev.alexfranco.mtw.registro.db.UserDB
import dev.alexfranco.mtw.registro.helpers.DoAsync
import kotlinx.android.synthetic.main.activity_users_list.*

class UsersList : AppCompatActivity() {

    private lateinit var viewAdapter: UserAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    val usersList : List<User> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users_list)
        viewManager = LinearLayoutManager(this)
        viewAdapter = UserAdapter(usersList)

        rvUsers.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
            addItemDecoration(DividerItemDecoration(this@UsersList, DividerItemDecoration.VERTICAL))
        }
    }

    override fun onResume() {
        super.onResume()
        DoAsync{
            val users = UserDB.getInstance(this@UsersList)?.userDao()?.getAllUsers()
            runOnUiThread{
                viewAdapter.setUsers(users!!)
            }
        }.execute()
    }
}
