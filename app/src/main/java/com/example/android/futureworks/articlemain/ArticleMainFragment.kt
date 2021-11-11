package com.example.android.futureworks.articlemain

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.futureworks.R
import com.example.android.futureworks.adapter.ArticleAdapter
import com.example.android.futureworks.authentication.Authentication
import com.example.android.futureworks.databinding.FragmentArticleMainBinding
import com.example.android.futureworks.models.Article
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_article_main.*

class ArticleMainFragment : Fragment(), ArticleAdapter.ArticlesListener {

    private lateinit var binding: FragmentArticleMainBinding
    private lateinit var user: FirebaseAuth

    private val viewModel: ArticleMainViewModel by lazy {
        val activity = requireNotNull(this.activity) {
        }
        ViewModelProvider(this, ArticleMainViewModel.Factory(activity.application)).get(
            ArticleMainViewModel::class.java
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_article_main, container,false)

        binding.lifecycleOwner = this

        with(binding) {
            viewmodel = viewModel
        }

        viewModel.articleListLiveData.observe(viewLifecycleOwner) {
            articlesList(it)
        }

        viewModel.navigateToSelectedArticle.observe(viewLifecycleOwner, Observer {
            it?.let {
                this.findNavController().navigate(
                    ArticleMainFragmentDirections
                        .actionArticleMainFragmentToArticleDetailFragment(it))
                viewModel.displayArticleDetailsComplete()
            }
        })

        user = FirebaseAuth.getInstance()
        setHasOptionsMenu(true)

        return binding.root
    }

    private fun articlesList(it: List<Article>) {
        val articleAdapter = ArticleAdapter(this)
        recycler_view.layoutManager = LinearLayoutManager(context)
        recycler_view.adapter = articleAdapter
        articleAdapter.setData(it)
    }

    override fun onClick(article: Article) {
        viewModel.onArticleClicked(article)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.overflow_menu, menu);

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.logout_button -> {
                FirebaseAuth.getInstance().signOut()
                val intent = Intent(activity, Authentication::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}