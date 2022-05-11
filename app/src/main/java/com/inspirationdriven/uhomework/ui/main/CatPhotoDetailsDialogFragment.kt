package com.inspirationdriven.uhomework.ui.main

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.example.uhomework.R
import com.example.uhomework.databinding.CatDetailsFragmentBinding
import com.inspirationdriven.uhomework.misc.resolveCatUrl
import com.inspirationdriven.uhomework.model.CatMeta
import java.util.*

class CatPhotoDetailsDialogFragment : DialogFragment(R.layout.cat_details_fragment) {

    companion object {
        const val ARG_CAT_DATA = "arg_cat_data"

        fun newInstance(cat: CatMeta) =
            CatPhotoDetailsDialogFragment().apply {
                arguments = bundleOf(ARG_CAT_DATA to cat)
            }
    }

    private lateinit var catMeta: CatMeta

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getParcelable<CatMeta>(ARG_CAT_DATA)?.also {
            catMeta = it
        } ?: dismiss()
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.also { window ->
            window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            window.setDimAmount(0.90f)
            val wlp = window.attributes
            wlp.width = MATCH_PARENT
            wlp.height = MATCH_PARENT
            window.attributes = wlp
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(CatDetailsFragmentBinding.bind(view)) {
            Glide.with(requireContext()).load(resolveCatUrl(catMeta.id)).fitCenter().into(imgPhoto)
            root.setOnClickListener {
                dismiss()
            }
            textTagList.text = catMeta.tags.joinToString(", ")
        }
    }
}