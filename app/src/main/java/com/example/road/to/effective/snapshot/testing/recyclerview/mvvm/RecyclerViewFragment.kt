package com.example.road.to.effective.snapshot.testing.recyclerview.mvvm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.road.to.effective.snapshot.testing.R
import com.example.road.to.effective.snapshot.testing.recyclerview.DeleteMemoriseListener
import com.example.road.to.effective.snapshot.testing.recyclerview.LanguageFilterClickedListener
import com.example.road.to.effective.snapshot.testing.recyclerview.MemoriseClickedListener
import com.example.road.to.effective.snapshot.testing.recyclerview.data.Language
import com.example.road.to.effective.snapshot.testing.recyclerview.data.Memorise
import com.example.road.to.effective.snapshot.testing.recyclerview.dataproviders.NonLoggedUserMemoriseProvider
import com.example.road.to.effective.snapshot.testing.recyclerview.dataproviders.NonLoggedUserSettingsProvider
import com.example.road.to.effective.snapshot.testing.recyclerview.rows.RecyclerViewAsyncDiffAdapter
import com.example.road.to.effective.snapshot.testing.recyclerview.rows.RecyclerViewDiffUtilCallback
import com.example.road.to.effective.snapshot.testing.recyclerview.rows.RowBuilder
import com.example.road.to.effective.snapshot.testing.recyclerview.rows.header.HeaderDelegate
import com.example.road.to.effective.snapshot.testing.recyclerview.rows.memorisetext.MemoriseDelegate
import com.example.road.to.effective.snapshot.testing.recyclerview.rows.training.TrainingDelegate
import com.example.road.to.effective.snapshot.testing.recyclerview.utils.EventObserver
import com.google.android.material.snackbar.Snackbar.*

class RecyclerViewFragment : Fragment(), LanguageFilterClickedListener, DeleteMemoriseListener,
    MemoriseClickedListener {

    companion object {
        fun newInstance() = RecyclerViewFragment()
    }

    private val viewModel: RecyclerViewViewModel by viewModels {
        RecyclerViewViewModelFactory(
            NonLoggedUserMemoriseProvider(requireContext()),
            NonLoggedUserSettingsProvider()
        )
    }

    private val rvAdapter: RecyclerViewAsyncDiffAdapter by lazy {
        RecyclerViewAsyncDiffAdapter(
            RecyclerViewDiffUtilCallback(),
            HeaderDelegate(),
            TrainingDelegate(this),
            MemoriseDelegate(this)
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.main_fragment, container, false)
        view.findViewById<RecyclerView>(R.id.memoriseList).apply {
            adapter = rvAdapter
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.run {
            getMemoriseListViewState().observe(viewLifecycleOwner) { _ ->
                rvAdapter.items =
                    RowBuilder(viewModel).trainingRows() + RowBuilder(viewModel).memoriseRows()
            }

            getTrainingViewState().observe(viewLifecycleOwner) { _ ->
                rvAdapter.items =
                    RowBuilder(viewModel).trainingRows() + RowBuilder(viewModel).memoriseRows()
            }

            getClickableActionData().observe(
                viewLifecycleOwner,
                EventObserver { action -> handleNavigateAction(action) })
        }

        viewModel.fetchMemorises()
    }

    override fun onLanguageFilterClicked(lang: Language) {
        viewModel.clickOnFilter(lang)
    }

    override fun onMemoriseDeleted(memorise: Memorise) {
        viewModel.clickOnConfirmDeleteMemorise(memorise)
    }

    override fun onMemoriseClicked(memorise: Memorise) {
        viewModel.clickOnMemorise(memorise)
    }

    private fun handleNavigateAction(action: RecyclerViewViewModelContract.ClickAction): Unit =
        when (action) {
            is RecyclerViewViewModelContract.ClickAction.ShowNotSupportedActionSnackbar -> {
                make(this.requireView(), "Non Supported Action", LENGTH_SHORT).show()
            }
        }

}