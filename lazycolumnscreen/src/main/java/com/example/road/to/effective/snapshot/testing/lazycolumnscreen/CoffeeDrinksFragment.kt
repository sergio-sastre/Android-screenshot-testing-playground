package com.example.road.to.effective.snapshot.testing.lazycolumnscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels

class CoffeeDrinksFragment : Fragment() {
    private val viewModel: CoffeeDrinksViewModel by viewModels {
        CoffeeDrinksViewModelFactory(
            RuntimeCoffeeDrinkRepository,
            CoffeeDrinkItemMapper()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            id = R.id.coffeeDrinksFragment
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )

            setContent {
                AppTheme {
                    viewModel.uiState.observeAsState(initial = UiState.Loading).value.let { uiState ->
                        when (uiState) {
                            is UiState.Success -> {
                                ShowSuccessCoffeeDrinksScreen(
                                    coffeeDrinksState = uiState.data,
                                    coffeeShopName = arguments?.getString("coffee_shop_name").toString()
                                )
                            }
                            UiState.Loading -> {
                                // TODO
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadCoffeeDrinks()
    }
}
