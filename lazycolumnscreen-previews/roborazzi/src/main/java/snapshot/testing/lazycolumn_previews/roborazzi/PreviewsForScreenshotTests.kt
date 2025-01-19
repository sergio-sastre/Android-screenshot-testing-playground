package snapshot.testing.lazycolumn_previews.roborazzi

import android.os.Build
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.AppTheme
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.CoffeeDrinkItem
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.CoffeeDrinkList
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.R
import snapshot.testing.lazycolumn_previews.roborazzi.utils.CoffeeDrinkItemProvider
import snapshot.testing.lazycolumn_previews.roborazzi.utils.RoborazziConfig

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
fun CoffeeDrinkListWithParametersPreview(
    @PreviewParameter(provider = CoffeeDrinkItemProvider::class) drinkItem: CoffeeDrinkItem
) {
    AppTheme {
        CoffeeDrinkList(
            coffeeDrink = drinkItem
        )
    }
}

@PreviewScreenSizes
@PreviewFontScale
@PreviewLightDark
@Preview(
    widthDp = 1000,
    heightDp = 2000,
    device = "id:pixel_xl",
    showBackground = true,
    backgroundColor = 0xAAFFFFFF
)
@Composable
fun CoffeeDrinkListMultiConfigPreview() {
    AppTheme {
        CoffeeDrinkList(
            coffeeDrink = coffeeDrink
        )
    }
}

@Preview(locale = "ar-rXB")
@Composable
fun CoffeeDrinkListPseudoLocalePreview() {
    AppTheme {
        CoffeeDrinkList(
            coffeeDrink = coffeeDrink
        )
    }
}

@Preview(apiLevel = 31)
@Composable
fun CoffeeDrinkListApiLevelPreview() {
    AppTheme {
        CoffeeDrinkList(
            coffeeDrink = coffeeDrink.copy(name = "API ${Build.VERSION.SDK_INT}")
        )
    }
}

@RoborazziConfig(enableAccessibility = true)
@Preview(name = "Accessibility")
@Composable
fun CoffeeDrinkListAccessibilityPreview() {
    AppTheme {
        CoffeeDrinkList(
            coffeeDrink = coffeeDrink
        )
    }
}
