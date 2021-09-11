package gst.training.practicefirebase.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import gst.training.practicefirebase.R
import gst.training.practicefirebase.data.User
import kotlinx.android.synthetic.main.item_user.view.*

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<User>() {

        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }
    }
    private var onItemClick: (User) -> Unit = {}
    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder =
        UserViewHolder(
            onItemClick,
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_user, parent, false
            )
        )

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = differ.currentList[position]
        holder.bindItemCategory(user)
    }

    override fun getItemCount(): Int = differ.currentList.size


    inner class UserViewHolder(onClickItem: (User) -> Unit, itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener {
                onClickItem(differ.currentList[adapterPosition])
            }
        }

        fun bindItemCategory(user: User) {
            itemView.tvName.text = user.name

        }
    }
}