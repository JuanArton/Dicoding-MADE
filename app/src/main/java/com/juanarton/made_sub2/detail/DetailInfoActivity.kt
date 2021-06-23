package com.juanarton.made_sub2.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.juanarton.core.BuildConfig
import com.juanarton.core.domain.DataParcel
import com.juanarton.made_sub2.R
import com.juanarton.made_sub2.databinding.ActivityDetailInfoBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailInfoActivity : AppCompatActivity(){

    private lateinit var binding: ActivityDetailInfoBinding
    private val detailViewModel: DetailViewModel by viewModel()
    private var buttonStat: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mID = intent.getStringExtra("ID")


        mID?.let {
            detailViewModel.getDetailMovieByID(mID).observe(this) { data ->
                setUI(data)
                binding.favButton.setOnClickListener {
                    buttonStat = if (!buttonStat) {
                        detailViewModel.insertToFav(data)
                        true
                    } else {
                        detailViewModel.deleteFromFav(data)
                        false
                    }
                    setButton()
                }

                detailViewModel.getFavDetail(mID).observe(this) {
                    buttonStat = true
                    setButton()
                }
            }
        }
    }

    private fun setButton() {
        if(!buttonStat){
            binding.favButton.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        }else{
            binding.favButton.setImageResource(R.drawable.ic_baseline_favorite_24)
        }
    }

    private fun setUI(data: DataParcel){
        supportActionBar?.title = getString(R.string.info)
        val imgBackdropLink = buildString{
            append(BuildConfig.BASE_IMAGE_URL)
            append(data.backdrop)
        }
        val imgPosterLink = buildString{
            append(BuildConfig.BASE_IMAGE_URL)
            append(data.imageLink)
        }
        binding.apply {
            Glide.with(applicationContext)
                .load(imgBackdropLink)
                .fitCenter()
                .into(movieBackdrop)
            Glide.with(applicationContext)
                .load(imgPosterLink)
                .fitCenter()
                .into(moviePoster)

            movieTitle.text = data.title
            releaseDate.text = data.releaseYear
            movieDesc.text = data.description
        }
    }
}