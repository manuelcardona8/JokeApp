package com.example.jokeapp.ui.home

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.example.JokeData
import com.example.jokeapp.R
import com.example.jokeapp.databinding.FragmentHomeBinding
import com.example.jokeapp.network.NetworkResponse
import com.example.jokeapp.ui.home.adapter.HomeAdapter
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class HomeFragment : DaggerFragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var jokeAdapter: HomeAdapter? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val homeViewModel: HomeViewModel by lazy {
        ViewModelProvider(
            requireActivity(),
            viewModelFactory
        )[HomeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observing()
        setRecyclerView()
        setOnClickListener()
        jokeAdapter?.cleanItem()
        homeViewModel.fetchRandomJoke()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

    }

    private fun setOnClickListener() {
        binding.buttonRandomJoke.setOnClickListener {
            jokeAdapter?.cleanItem()
            homeViewModel.fetchRandomJoke()
        }
    }

    private fun setRecyclerView() {
        jokeAdapter = HomeAdapter()
        binding.recycleviewContainer.apply {
            adapter = jokeAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun observing() {
        homeViewModel.jokeReponse.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResponse.Error -> handleError()
                is NetworkResponse.Loading -> handleLoading()
                is NetworkResponse.Success -> handleSucces(response.data)
            }
        }
    }

    private fun handleSucces(data: List<JokeData>) {
        hideProgressBar()
        jokeAdapter?.setJoke(data)
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
    }

    private fun handleLoading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun handleError() {
        hideProgressBar()
        Toast.makeText(context, R.string.failureMessage, Toast.LENGTH_SHORT).show()
    }

}