package com.example.road.to.effective.snapshot.testing.lazycolumnscreen_previews.compose_preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.CoffeeDrinkItem
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.CoffeeDrinkList
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.R

private val coffeeDrink =
    CoffeeDrinkItem(
        id = 1L,
        name = "Americano",
        imageUrl = R.drawable.americano_small,
        description =
        """
        Americano is a type of coffee drink prepared by diluting an espresso with hot water,
        giving it a similar strength to, but different flavour from, traditionally brewed coffee.
        """.trimIndent(),
        ingredients = "Espresso, Water",
        isFavourite = false
    )

@Composable
@Preview
fun CoffeeDrinkListPreview(){
    CoffeeDrinkList(
        coffeeDrink = coffeeDrink
    )
}
/*
class PreviewsForScreenshotTests {

    private val coffeeDrink =
        CoffeeDrinkItem(
            id = 1L,
            name = "Americano",
            imageUrl = R.drawable.americano_small,
            description =
            """
        Americano is a type of coffee drink prepared by diluting an espresso with hot water,
        giving it a similar strength to, but different flavour from, traditionally brewed coffee.
        """.trimIndent(),
            ingredients = "Espresso, Water",
            isFavourite = false
        )

    @Composable
    @Preview
    fun CoffeeDrinkListPreview(){
        CoffeeDrinkList(
            coffeeDrink = coffeeDrink
        )
    }
}

 */