package com.example.android.futureworks.articlemain.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.android.futureworks.R
import com.example.android.futureworks.databinding.FragmentArticleDetailBinding

class ArticleDetailFragment : Fragment() {

    private lateinit var binding: FragmentArticleDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_article_detail, container, false)
        binding.lifecycleOwner = this

        val arguments = ArticleDetailFragmentArgs.fromBundle(requireArguments()).selectedArticle
        binding.article = arguments

        return binding.root
    }

}