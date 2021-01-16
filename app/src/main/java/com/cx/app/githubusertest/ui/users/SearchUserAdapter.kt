package com.cx.app.githubusertest.ui.users

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cx.app.githubusertest.R
import com.cx.app.githubusertest.model.Items
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.users_item.view.*

/**
 * @author kasmadi
 * @date 1/15/21
 */
class SearchUserAdapter(private val data: List<Items>) :
    RecyclerView.Adapter<SearchUserAdapter.UsersViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UsersViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.users_item, parent, false)
        return UsersViewHolder(view)
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size


    inner class UsersViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(data: Items) {
            itemView.tvUsername.text = data.login
            Picasso.get().load(data.avatar_url).into(itemView.imgAva)
        }
    }
}