package com.sialitski.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.sialitski.R
import com.sialitski.databinding.FragmentFavoritesBinding
import com.sialitski.domain.OnNewsClickListener
import com.sialitski.presentation.recyclerView.NewsAdapter
import com.sialitski.presentation.viewModels.NewsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentFavorites : Fragment(R.layout.fragment_favorites) {

    private val binding: FragmentFavoritesBinding
            by viewBinding(FragmentFavoritesBinding::bind)
    private val viewModel: NewsViewModel by viewModel()
    private val adapter by lazy { NewsAdapter(newsClickListener) }

    private val newsClickListener: OnNewsClickListener = object :
        OnNewsClickListener {
        override fun onIconClickListener(position: Int) {
            viewModel.onNewsItemClicked(position)
            viewModel.news.value?.get(position)?.let { viewModel.deleteDataNews(it) }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

        initObservers()

        initRecycler()

        viewModel.loadDataNews()
    }

    private fun initObservers() {
        viewModel.news.observe(viewLifecycleOwner) { news ->
            adapter.submitList(news)
        }
    }

    private fun initRecycler() {
        binding.run {
            recyclerFavorites.adapter = adapter
            recyclerFavorites.layoutManager = LinearLayoutManager(context)
        }
    }
}