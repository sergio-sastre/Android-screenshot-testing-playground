package snapshot.testing.lazycolumn_previews.paparazzi.utils

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.CoffeeDrinkItem
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.R

class CoffeeDrinkItemProvider : PreviewParameterProvider<CoffeeDrinkItem> {
    override val values: Sequence<CoffeeDrinkItem>
        get() = sequenceOf(
            CoffeeDrinkItem(
                id = 2L,
                name = "Cappuccino",
                imageUrl = R.drawable.cappuccino_small,
                description = "A cappuccino is an espresso-based coffee drink that originated in Italy, and is traditionally prepared with steamed milk foam.",
                ingredients = "Espresso, Steamed milk foam",
                isFavourite = false
            ),
            CoffeeDrinkItem(
                id = 3L,
                name = "Espresso",
                imageUrl = R.drawable.espresso_small,
                description = "Espresso is coffee of Italian origin, brewed by forcing a small amount of nearly boiling water under pressure (expressing) through finely-ground coffee beans.",
                ingredients = "Ground coffee, Water",
                isFavourite = false
            ),
        )
}