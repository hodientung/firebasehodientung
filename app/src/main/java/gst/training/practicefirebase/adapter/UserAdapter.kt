package gst.training.practicefirebase.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import gst.training.practicefirebase.R
import gst.training.practicefirebase.data.User
import gst.training.practicefirebase.ui.RecyclerViewClickListener
import kotlinx.android.synthetic.main.item_user.view.*

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private var users = mutableListOf<User>()
    var listener: RecyclerViewClickListener? = null

    private val differCallback = object : DiffUtil.ItemCallback<User>() {

        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder =
        UserViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_user, parent, false
            )
        )

    fun setUsers(users: List<User>) {
        this.users = users as MutableList<User>
        notifyDataSetChanged()
    }

    fun addUser(user: User) {
        if (!users.contains(user)) {
            users.add(user)
        } else {
            val index = users.indexOf(user)
            if (user.isDeleted) {
                users.removeAt(index)
            } else {
                users[index] = user
            }
        }
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bindItemCategory(users[position])
    }

    override fun getItemCount(): Int = users.size


    inner class UserViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        init {
            itemView.ivUpdate.setOnClickListener {
                listener?.onRecyclerViewItemClicked(it, users[adapterPosition])
            }
            itemView.ivDelete.setOnClickListener {
                listener?.onRecyclerViewItemClicked(it, users[adapterPosition])
            }
        }

        fun bindItemCategory(user: User) {
            itemView.tvName.text = user.name

        }
    }
}