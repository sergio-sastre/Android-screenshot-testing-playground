package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.mvvm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.R
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.DeleteMemoriseListener
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.LanguageFilterClickedListener
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.MemoriseClickedListener
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.TrainAllClickedListener
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.data.Language
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.data.Memorise
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.dataproviders.memorise.UserMemoriseProvider
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.dataproviders.setting.UserSettingsProvider
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.mvvm.RecyclerViewViewModelContract.ClickAction.ShowNotSupportedActionSnackbar
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.rows.RecyclerViewAsyncDiffAdapter
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.rows.RecyclerViewDiffUtilCallback
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.rows.RowBuilder
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.rows.header.HeaderDelegate
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.rows.memorisetext.MemoriseDelegate
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.rows.training.TrainingDelegate
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.utils.EventObserver
import com.google.android.material.snackbar.Snackbar.*

class RecyclerViewFragment : Fragment(),
    LanguageFilterClickedListener,
    DeleteMemoriseListener,
    MemoriseClickedListener,
    TrainAllClickedListener {

    companion object {
        fun newInstance() = RecyclerViewFragment()
    }

    private val viewModel: RecyclerViewViewModel by viewModels {
        RecyclerViewViewModelFactory(
            UserMemoriseProvider(requireContext()),
            UserSettingsProvider()
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
        val view = inflater.inflate(R.layout.recycler_view_fragment, container, false)
        view.findViewById<RecyclerView>(R.id.memoriseList).apply {
            adapter = rvAdapter
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.run {
            getMemoriseListViewState().observe(viewLifecycleOwner) {
                rvAdapter.items = RowBuilder(viewModel).fullScreenRows()
            }

            getTrainingViewState().observe(viewLifecycleOwner) {
                rvAdapter.items = RowBuilder(viewModel).fullScreenRows()
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
        viewModel.clickOnDeleteMemorise(memorise)
    }

    override fun onMemoriseClicked(memorise: Memorise) {
        viewModel.clickOnMemorise(memorise)
    }

    override fun onTrainAllClicked() {
        viewModel.clickOnTrainAll()
    }

    private fun handleNavigateAction(action: RecyclerViewViewModelContract.ClickAction): Unit =
        when (action) {
            is ShowNotSupportedActionSnackbar -> {
                showNonSupportedActionSnackbar()
            }
        }

    private fun showNonSupportedActionSnackbar(){
        make(requireView(), "Non Supported Action", LENGTH_SHORT).show()
    }

}