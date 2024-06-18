package snapshot.testing.lazycolumn_previews.paparazzi

import android.os.Build
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.AppTheme
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.CoffeeDrinkItem
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.CoffeeDrinkList
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.R
import snapshot.testing.lazycolumn_previews.paparazzi.utils.CoffeeDrinkItemProvider
import snapshot.testing.lazycolumn_previews.paparazzi.utils.PaparazziConfig
import snapshot.testing.lazycolumn_previews.paparazzi.utils.PaparazziConfig.RenderingMode.NORMAL

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


@PreviewFontScale
@PreviewLightDark
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

/**
 * This shows "API 33" instead of "API 31", since Paparazzi does not support
 * different API levels. See https://github.com/cashapp/paparazzi/issues/39
 */
@Preview(apiLevel = 31)
@Composable
fun CoffeeDrinkListApiLevelPreview() {
    AppTheme {
        CoffeeDrinkList(
            coffeeDrink = coffeeDrink.copy(name = "API ${Build.VERSION.SDK_INT}")
        )
    }
}

@PaparazziConfig(
    enableAccessibility = true,
    renderingMode = NORMAL
)
@Preview(name = "Accessibility")
@Composable
fun CoffeeDrinkListAccessibilityPreview() {
    AppTheme {
        CoffeeDrinkList(
            coffeeDrink = coffeeDrink
        )
    }
}