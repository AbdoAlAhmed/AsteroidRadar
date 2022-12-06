package com.udacity.asteroidradar.main

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private  val viewModel: MainViewModel by lazy {
        val application = requireNotNull(activity)
        val viewModelFactory = MainViewModelFactory(application.application)
        ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
    }
    private  val adapter: MainAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        val binding = FragmentMainBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.asteroidRecycler.adapter = MainAdapter(MainAdapter.Onclick{
            findNavController().navigate(MainFragmentDirections.actionShowDetail(it))
        })


        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.show_all_menu->{
                viewModel.filterUpdate(MainViewModel.AsteroidFilter.WEEK)
            }
            R.id.show_rent_menu ->{
                viewModel.filterUpdate(MainViewModel.AsteroidFilter.TODAY)
            }
            R.id.show_buy_menu ->{
                viewModel.filterUpdate(MainViewModel.AsteroidFilter.SAVED)
            }
        }
        return true
    }
}
