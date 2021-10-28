package com.example.proyectobootcamp.fragments

import android.renderscript.ScriptGroup
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectobootcamp.R
import com.example.proyectobootcamp.databinding.ItemRecomendacionesBinding

class LibroRecomendadoAdapter(
    val librosRecomendados: List<LibroRecomendado>
    ): RecyclerView.Adapter<LibroRecomendadoAdapter.ViewHolder>() {


    class ViewHolder(val binding: ItemRecomendacionesBinding): RecyclerView.ViewHolder(binding.root) {
        //val binding = ItemRecomendacionesBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibroRecomendadoAdapter.ViewHolder {
        val binding = ItemRecomendacionesBinding.inflate(LayoutInflater.from(parent.context),parent, false)
            //LayoutInflater.from(parent.context).inflate(R.layout.item_recomendaciones, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LibroRecomendadoAdapter.ViewHolder, position: Int) {
        val libroRecomendado = librosRecomendados.get(position)

        holder.binding.name.text = libroRecomendado.name
        holder.binding.autor.text = libroRecomendado.autor
        holder.binding.image.setImageResource(libroRecomendado.image)
    }

    override fun getItemCount(): Int = librosRecomendados.size

}