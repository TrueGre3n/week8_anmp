package com.ubaya.studentapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.studentapp.databinding.SongListItemBinding
import com.ubaya.studentapp.databinding.StudentListItemBinding
import com.ubaya.studentapp.model.Song
import com.ubaya.studentapp.model.Student
import com.ubaya.studentapp.util.loadImage

class SongListAdapter(val songList: ArrayList<Song>): RecyclerView.Adapter<SongListAdapter.SongViewHolder>() {
    class SongViewHolder(var binding: SongListItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        var binding = SongListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SongViewHolder(binding)

    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        holder.binding.txtID.text = "ID: " + songList[position].id
        holder.binding.txtTitle.text = "Title: " +songList[position].title
        holder.binding.txtArtist.text = "Artist: " +songList[position].artist
        holder.binding.txtAlbum.text = "Album Name: " +songList[position].album.name
        holder.binding.txtYear.text = "Year: " +songList[position].album.year.toString()
        var listGenre= "Genre: "
        songList[position].genre?.forEach{
            listGenre +=  "\n" + it
        }
        holder.binding.txtGenre.text = listGenre

        var imageView = holder.binding.imageViewSong
        var progressBar = holder.binding.progressBar
        imageView.loadImage(songList[position].images, progressBar)
    }

    override fun getItemCount(): Int {
        return songList.size
    }

    fun updateSongList(newSongList:ArrayList<Song>){
        songList.clear()
        songList.addAll(newSongList)
        notifyDataSetChanged()

    }
}