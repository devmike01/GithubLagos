package com.example.features.features.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.common.extensions.hide
import com.example.common.extensions.noNull
import com.example.common.extensions.show
import com.example.common.states.Status
import com.example.core.repository.models.user.DetailItemResponse
import com.example.features.R
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private val detailsFragmentViewModel: DetailsFragmentViewModel by viewModels<DetailsFragmentViewModel>()

    private lateinit var nameTv : TextView
    private lateinit var locationTv : TextView
    private lateinit var companyTv: TextView
    private lateinit var bioTv : TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var avatarImg : ImageView


    companion object{

        private const val ARG_LOGIN ="com.example.features.features.DetailsFragment.details.ARG_LOGIN"

        fun newInstance(login: String) = DetailsFragment().apply {
            val bundle = Bundle()
            bundle.putString(ARG_LOGIN, login)
            arguments = bundle
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews(view)

        // Load the user details
        val login = arguments?.getString(ARG_LOGIN)
        login?.run {   detailsFragmentViewModel.fetchDetails(this) }
        populateViews()
    }



    private fun bindViews(fragView: View){
        fragView.run{
            nameTv = findViewById(R.id.tv_name)
            locationTv = findViewById(R.id.tv_location)
            companyTv = findViewById(R.id.tv_company)
            bioTv = findViewById(R.id.tv_bio)
            progressBar = findViewById(R.id.progress_bar)
            avatarImg = findViewById(R.id.iv_avatar)
        }
    }

    private fun hideProgress(){
        activity?.hide(progressBar)
    }

    private fun showProgress(){
        activity?.show(progressBar)
    }

    private fun populateViews(){
        detailsFragmentViewModel.detailUser.observe(viewLifecycleOwner){
            when(it.status){
                Status.Failed ->{
                    hideProgress()
                }
                Status.Success ->{
                    hideProgress()
                    populateViews(it.data)

                }
                Status.Loading ->{
                    showProgress()
                }
            }
        }
    }

    private fun populateViews(details: DetailItemResponse?)
    {
        details?.run {
            nameTv.text = details.name.noNull()
            locationTv.text = details.location.noNull()
            companyTv.text = details.company.noNull()
            bioTv.text = details.bio.noNull()
            Glide.with(requireActivity()).load(avatarUrl).into(avatarImg)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.details_layout_activity, container,false)
    }
}