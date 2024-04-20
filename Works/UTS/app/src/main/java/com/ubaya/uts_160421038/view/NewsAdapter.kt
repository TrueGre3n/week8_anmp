package com.ubaya.uts_160421038.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.ubaya.uts_160421038.databinding.NewsListItemBinding
import com.ubaya.uts_160421038.model.Game

class NewsAdapter(val newsList:ArrayList<Game>):RecyclerView.Adapter<NewsAdapter.GameViewHolder>() {
    class GameViewHolder(var binding: NewsListItemBinding):
            RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val binding = NewsListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return GameViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.binding.txtTitle.text = newsList[position].title
        holder.binding.txtAuthor.text = "by " + newsList[position].author
        holder.binding.txtDate.text = "created on " + newsList[position].date
        holder.binding.txtDesc.text = newsList[position].description

        holder.binding.btnRead.setOnClickListener {
            val gameId = newsList[position].id
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(gameId!!)
            Navigation.findNavController(it).navigate(action)
        }

        val picasso = Picasso.Builder(holder.itemView.context)
        picasso.listener { picasso, uri, exception ->
            exception.printStackTrace()
        }

        picasso.build().load(newsList[position].images)
            .into(holder.binding.imageView2, object: Callback {
                override fun onSuccess() {
                    holder.binding.progressBar2.visibility = View.INVISIBLE
                    holder.binding.imageView2.visibility = View.VISIBLE
                }
                override fun onError(e: Exception?) {
                    Log.e("picasso_error", e.toString())
                }
            })
    }

fun updateGameList(newGameList:ArrayList<Game>){
    newsList.clear()
    newsList.addAll(newGameList)
    notifyDataSetChanged()
}

}