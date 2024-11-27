package com.example.exam_02_behm

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.exam_02_behm.entities.UserEntity

class UserAdapter(
    private val userList: List<UserEntity>,
    private val onClick: (UserEntity) -> Unit, // Evento para clic corto
    private val onLongClick: (UserEntity) -> Unit // Evento para clic largo
) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.tvUsernameRecyclerView)
        val username: TextView = itemView.findViewById(R.id.tvLastMessageRecyclerView)
        val email: TextView = itemView.findViewById(R.id.tvemailRecyclerView)
        val phone: TextView = itemView.findViewById(R.id.tvphoneRecyclerView)
        val website: TextView = itemView.findViewById(R.id.tvwebsiteRecyclerView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.custom_item_list_recycler, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]
        holder.name.text = user.name
        holder.username.text = user.username
        holder.email.text = user.email
        holder.phone.text = user.phone
        holder.website.text = user.website

        // Manejo de clic corto
        holder.itemView.setOnClickListener {
            onClick(user)
        }

        // Manejo de clic largo
        holder.itemView.setOnLongClickListener {
            onLongClick(user)
            true
        }
    }

    override fun getItemCount() = userList.size
}
