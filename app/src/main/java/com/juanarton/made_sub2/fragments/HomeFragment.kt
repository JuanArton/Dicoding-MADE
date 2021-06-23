package com.juanarton.made_sub2.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.juanarton.core.BuildConfig
import com.juanarton.core.adapter.RecyclerAdapter
import com.juanarton.core.domain.DataParcel
import com.juanarton.made_sub2.databinding.FragmentHomeBinding
import com.juanarton.made_sub2.detail.DetailInfoActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.imaginativeworld.whynotimagecarousel.ImageCarousel
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    private val homeViewModel: HomeViewModel by viewModel()
    private var bindingtmp: FragmentHomeBinding? = null
    private val binding get() = bindingtmp!!

    private var carouseltmp: ImageCarousel? = null
    private val carousel get() = carouseltmp!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingtmp = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        binding.nodataText.visibility = View.INVISIBLE
        binding.loadanimate.visibility = View.INVISIBLE

        homeViewModel.listMovie.observe(viewLifecycleOwner) { data ->
            setUI(data)
        }
    }

    private fun setUI(data: List<DataParcel>){
        if(data.isEmpty()){
            binding.nodataText.visibility = View.VISIBLE
            binding.loadanimate.visibility = View.VISIBLE
        }else{
            setCarousel(data)
        }
    }

    private fun setCarousel(data: List<DataParcel>){
        carouseltmp = binding.imgcarousel
        carousel.registerLifecycle(lifecycle)
        CoroutineScope(Dispatchers.IO).launch{
            val carouselList = mutableListOf<CarouselItem>()
            for(i in 0..5){
                val imageUrl = buildString{
                    append(BuildConfig.BASE_IMAGE_URL)
                    append(data[i].backdrop)
                }
                carouselList.add(
                    CarouselItem(
                        imageUrl,
                        data[i].title
                    )
                )
            }
            activity?.runOnUiThread {
                carousel.setData(carouselList.shuffled())
                showRecyclerList(data)
            }
        }
    }

    private fun showRecyclerList(movieList: List<DataParcel>){
        binding.movieRecyclerContainer.layoutManager = GridLayoutManager(activity, 3)
        val dataAdapter = RecyclerAdapter(movieList)
        binding.movieRecyclerContainer.adapter = dataAdapter
        binding.nodataText.visibility = View.INVISIBLE
        binding.loadanimate.visibility = View.INVISIBLE
        dataAdapter.setOnItemClickCallback(object : RecyclerAdapter.OnItemClickCallback{
            override fun onItemClicked(data: DataParcel){
                val intent = Intent(context, DetailInfoActivity::class.java)
                intent.putExtra("ID", data.id)
                startActivity(intent)
            }
        })
    }
}