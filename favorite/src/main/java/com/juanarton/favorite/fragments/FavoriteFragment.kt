package com.juanarton.favorite.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.juanarton.core.adapter.RecyclerAdapter
import com.juanarton.core.domain.DataParcel
import com.juanarton.favorite.databinding.FragmentFavoriteBinding
import com.juanarton.favorite.di.favoriteMovieModule
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

class FavoriteFragment : Fragment() {
    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private var bindingtmp: FragmentFavoriteBinding? = null
    private val binding get() = bindingtmp!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingtmp = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadKoinModules(favoriteMovieModule)

        binding.nodataText.visibility = View.INVISIBLE
        binding.loadanimate.visibility = View.INVISIBLE

        observeData()
    }

    override fun onResume() {
        super.onResume()
        binding.nodataText.visibility = View.VISIBLE
        binding.loadanimate.visibility = View.VISIBLE
        observeData()
    }

    override fun onDestroy() {
        super.onDestroy()
        unloadKoinModules(favoriteMovieModule)
    }

    private fun observeData(){
        favoriteViewModel.listFavorite.observe(viewLifecycleOwner, { data ->
            setUI(data)
        })
    }

    private fun setUI(data: List<DataParcel>){
        showRecyclerList(data)
        if(data.isEmpty()){
            binding.nodataText.visibility = View.VISIBLE
            binding.loadanimate.visibility = View.VISIBLE
        }
    }

    private fun showRecyclerList(movieList: List<DataParcel>) {
        binding.favMovieRecyclerContainer.layoutManager = GridLayoutManager(activity, 3)
        val dataAdapter = RecyclerAdapter(movieList)
        binding.favMovieRecyclerContainer.adapter = dataAdapter
        binding.nodataText.visibility = View.INVISIBLE
        binding.loadanimate.visibility = View.INVISIBLE
        dataAdapter.setOnItemClickCallback(object : RecyclerAdapter.OnItemClickCallback {
            override fun onItemClicked(data: DataParcel) {
                val intent = Intent(context, Class.forName("com.juanarton.made_sub2.detail.DetailInfoActivity"))
                intent.putExtra("ID", data.id)
                startActivity(intent)
            }
        })
    }
}