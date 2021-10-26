package com.example.proyectobootcamp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectobootcamp.R
import com.example.proyectobootcamp.databinding.FragmentHomeBinding
import org.imaginativeworld.whynotimagecarousel.ImageCarousel
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem


class HomeFragment : Fragment() {

    private lateinit var Binding: FragmentHomeBinding

    val listPublicidad = mutableListOf<CarouselItem>()

    private lateinit var linearLayoutManager: RecyclerView.LayoutManager
    private lateinit var libroRecomendadoAdapter: LibroRecomendadoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        //val view = inflater.inflate(R.layout.fragment_home, container, false)
        //view.recycler.adapter()

        //return view

        Binding = FragmentHomeBinding.inflate(inflater, container, false)
        return Binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Inicializo Recyclerview
        libroRecomendadoAdapter = LibroRecomendadoAdapter(getLibrosRecomendados())
        linearLayoutManager = LinearLayoutManager(requireContext())

        Binding.recyclerView.layoutManager = linearLayoutManager
        Binding.recyclerView.adapter = libroRecomendadoAdapter


        //Carousel
//        val imageCarousel: ImageCarousel = Binding.imgCarousel
//
//        listPublicidad.add(CarouselItem(R.drawable.aws))
//        listPublicidad.add(CarouselItem(R.drawable.delivery))
//        listPublicidad.add(CarouselItem(R.drawable.nttdata))
//
//        imageCarousel.addData(listPublicidad)
    }

    private fun getLibrosRecomendados(): List<LibroRecomendado> {
        val librosRecomendados = mutableListOf<LibroRecomendado>()

        val HarryPotter = LibroRecomendado("Harry Potter","J. K. Rowling", R.drawable.harry_potter)
        val senorAnillos = LibroRecomendado("Señor de los Anillos", "J. R. R. Tolkien", R.drawable.senor_anillos)

        librosRecomendados.add(HarryPotter)
        librosRecomendados.add(senorAnillos)
        librosRecomendados.add(HarryPotter)
        librosRecomendados.add(senorAnillos)
        librosRecomendados.add(HarryPotter)
        librosRecomendados.add(senorAnillos)

        return  librosRecomendados
    }
}