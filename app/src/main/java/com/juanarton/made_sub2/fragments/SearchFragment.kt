package com.juanarton.made_sub2.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.juanarton.core.adapter.RecyclerAdapter
import com.juanarton.core.domain.DataParcel
import com.juanarton.made_sub2.databinding.FragmentSearchBinding
import com.juanarton.made_sub2.detail.DetailInfoActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.regex.Pattern


class SearchFragment : Fragment() {
    private val searchViewModel: SearchViewModel by viewModel()
    private var bindingtmp: FragmentSearchBinding? = null
    private val binding get() = bindingtmp!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingtmp = FragmentSearchBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSearchView()
    }

    private fun setSearchView() {

        binding.searchbar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {
                searchViewModel.listMovie.observe(viewLifecycleOwner) { data ->
                    searchData(newText, data)
                }
                return false
            }
        })
    }

    private fun searchData(newText: String, data: List<DataParcel>){
        CoroutineScope(Dispatchers.IO).launch{
            val searchResult: MutableList<DataParcel> = ArrayList()
            if(newText.isEmpty()){
                searchResult.clear()
            }else{
                val pattern: Pattern = Pattern.compile(
                    newText,
                    Pattern.CASE_INSENSITIVE
                )
                for (i in data.indices) {
                    if (pattern.matcher(data[i].title).find()) {
                        searchResult.add(data[i])
                    }
                }
            }
            activity?.runOnUiThread {
                showRecyclerList(searchResult)
            }
        }
    }

    private fun showRecyclerList(movieList: List<DataParcel>){
        binding.movieRecyclerContainer.layoutManager = GridLayoutManager(activity, 3)
        val dataAdapter = RecyclerAdapter(movieList)
        binding.movieRecyclerContainer.adapter = dataAdapter
        dataAdapter.setOnItemClickCallback(object : RecyclerAdapter.OnItemClickCallback{
            override fun onItemClicked(data: DataParcel){
                val intent = Intent(context, DetailInfoActivity::class.java)
                intent.putExtra("ID", data.id)
                startActivity(intent)
            }
        })
    }

}