package com.example.road.to.effective.snapshot.testing

import android.content.Context
import android.content.Intent

object Actions {
    fun openRecyclerView(context: Context) =
        internalIntent(context, "action.recyclerviewscreen.open")

    fun openCoffeeDrinks(context: Context) =
        internalIntent(context, "action.coffeedrinkscompose.open")

    private fun internalIntent(context: Context, action: String) =
        Intent(action).setPackage(context.packageName)
}
