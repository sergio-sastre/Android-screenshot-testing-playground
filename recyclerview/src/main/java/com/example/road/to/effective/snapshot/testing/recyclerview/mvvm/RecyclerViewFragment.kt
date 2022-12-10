package com.example.road.to.effective.snapshot.testing.recyclerview.mvvm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.road.to.effective.snapshot.testing.recyclerview.R
import com.example.road.to.effective.snapshot.testing.recyclerview.DeleteMemoriseListener
import com.example.road.to.effective.snapshot.testing.recyclerview.LanguageFilterClickedListener
import com.example.road.to.effective.snapshot.testing.recyclerview.MemoriseClickedListener
import com.example.road.to.effective.snapshot.testing.recyclerview.TrainAllClickedListener
import com.example.road.to.effective.snapshot.testing.recyclerview.data.Language
import com.example.road.to.effective.snapshot.testing.recyclerview.data.Memorise
import com.example.road.to.effective.snapshot.testing.recyclerview.dataproviders.memorise.UserMemoriseProvider
import com.example.road.to.effective.snapshot.testing.recyclerview.dataproviders.setting.UserSettingsProvider
import com.example.road.to.effective.snapshot.testing.recyclerview.mvvm.RecyclerViewViewModelContract.ClickAction.ShowNotSupportedActionSnackbar
import com.example.road.to.effective.snapshot.testing.recyclerview.ui.rows.RecyclerViewAsyncDiffAdapter
import com.example.road.to.effective.snapshot.testing.recyclerview.ui.rows.RecyclerViewDiffUtilCallback
import com.example.road.to.effective.snapshot.testing.recyclerview.ui.rows.RowBuilder
import com.example.road.to.effective.snapshot.testing.recyclerview.ui.rows.header.HeaderDelegate
import com.example.road.to.effective.snapshot.testing.recyclerview.ui.rows.memorisetext.MemoriseDelegate
import com.example.road.to.effective.snapshot.testing.recyclerview.ui.rows.training.TrainingDelegate
import com.example.road.to.effective.snapshot.testing.recyclerview.utils.EventObserver
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
        make(this.requireView(), "Non Supported Action", LENGTH_SHORT).show()
    }

}