package com.example.road.to.effective.snapshot.testing.lazycolumnscreen.sharedtests.utils

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SnackbarScaffold(
    snackbar: @Composable (SnackbarHostState) -> Unit,
) {
    val snackbarHostState = SnackbarHostState()
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) {
        snackbar(snackbarHostState)
    }
}
