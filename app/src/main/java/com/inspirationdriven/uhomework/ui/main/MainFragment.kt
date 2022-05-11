package com.inspirationdriven.uhomework.ui.main

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.uhomework.R
import com.example.uhomework.databinding.MainFragmentBinding
import com.google.android.material.snackbar.Snackbar
import com.inspirationdriven.uhomework.model.CatMeta
import com.inspirationdriven.uhomework.ui.main.adapter.CatPhotoAdapter

class MainFragment : Fragment(R.layout.main_fragment), CatPhotoAdapter.AdapterCallbacks {

    private val viewModel: MainViewModel by viewModels()
    private var binding: MainFragmentBinding? = null

    private lateinit var catAdapter: CatPhotoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        catAdapter = CatPhotoAdapter(LayoutInflater.from(requireContext()), this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = MainFragmentBinding.bind(view)

        viewModel.cats.observe(viewLifecycleOwner) {
            when (it) {
                is MainViewModel.Resource.Success -> catAdapter.submitList(it.data)
                is MainViewModel.Resource.Error -> showError(it.exception)
            }
        }

        with(binding!!.recyclerCats) {
            layoutManager =
                GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
            adapter = catAdapter
        }

        viewModel.startJob()
    }

    override fun onDestroyView() {
        binding = null
        viewModel.stopJob()
        super.onDestroyView()
    }

    override fun onThumbnailClick(cat: CatMeta) {
        CatPhotoDetailsDialogFragment.newInstance(cat).show(childFragmentManager, null)
    }

    private fun showError(e: Throwable) {
        Snackbar.make(binding!!.root, e.toString(), Toast.LENGTH_SHORT).apply {
            setBackgroundTint(Color.RED)
            show()
        }
    }
}