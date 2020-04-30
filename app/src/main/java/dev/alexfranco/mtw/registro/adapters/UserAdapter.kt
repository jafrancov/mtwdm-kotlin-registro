package dev.alexfranco.mtw.registro.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.alexfranco.mtw.registro.R
import dev.alexfranco.mtw.registro.db.User
import kotlinx.android.synthetic.main.user_card.view.*
import java.text.SimpleDateFormat
import java.util.*


class UserAdapter(var usersList : List<User>) : RecyclerView.Adapter<UserAdapter.UserViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.UserViewHolder {
        val layout = LayoutInflater.from(parent.context)
        return UserViewHolder(layout.inflate(R.layout.user_card,parent,false))

    }

    override fun getItemCount(): Int = usersList.size

    override fun onBindViewHolder(holder: UserAdapter.UserViewHolder, position: Int) {
        holder.bindItem(usersList[position])
    }

    fun setUsers(lista : List<User>){
        usersList = lista
        notifyDataSetChanged()
    }

    fun getUsers():List<User> = usersList

    class UserViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(user:User){
            itemView.userName.text = user.name
            itemView.userDate.text = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(user.updated).toString()
        }
    }

}