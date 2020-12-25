package com.udacity.shoestore.screens.shoelist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.marginBottom
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.models.ShoeViewModel
import kotlinx.android.synthetic.main.fragment_detail.view.*
import kotlinx.android.synthetic.main.shoe_item.view.*
import timber.log.Timber


class ShoeListFragment : Fragment() {
    private lateinit var binding: FragmentShoeListBinding
    private lateinit var viewModel: ShoeViewModel
    private var itemListLinearLayout: LinearLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(ShoeViewModel::class.java)
        binding.shoeViewModel = viewModel

        itemListLinearLayout = binding.itemListLayout

        viewModel.shoeList.observe(viewLifecycleOwner, Observer { shoeList ->
            shoeList.forEach { shoe ->
                val rowInflater = LayoutInflater.from(activity)
                val rowView = rowInflater.inflate(R.layout.shoe_item, null)
                rowView.product_name_text.text = shoe.name
                rowView.product_company_text.text = shoe.company
                rowView.product_description_text.text = shoe.description
                itemListLinearLayout!!.addView(rowView, itemListLinearLayout!!.childCount - 1)
            }
        })

        binding.detailBtn.setOnClickListener {
            findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToDetailFragment())
        }

        // Inflate the layout for this fragment
        return binding.root
    }
}