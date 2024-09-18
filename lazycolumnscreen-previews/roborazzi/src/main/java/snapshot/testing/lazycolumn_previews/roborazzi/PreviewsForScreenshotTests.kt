package snapshot.testing.lazycolumn_previews.roborazzi

import android.content.res.Configuration
import android.os.Build
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.Wallpapers
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.AppTheme
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.CoffeeDrinkAppBar
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

@Preview(apiLevel = 33)
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

// Dynamic Colors are applied from API 31+
@Preview(
    apiLevel = 30,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    apiLevel = 30,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    apiLevel = 33,
    wallpaper = Wallpapers.BLUE_DOMINATED_EXAMPLE,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    apiLevel = 33,
    wallpaper = Wallpapers.BLUE_DOMINATED_EXAMPLE,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    apiLevel = 33,
    wallpaper = Wallpapers.RED_DOMINATED_EXAMPLE,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    apiLevel = 33,
    wallpaper = Wallpapers.RED_DOMINATED_EXAMPLE,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    apiLevel = 33,
    wallpaper = Wallpapers.GREEN_DOMINATED_EXAMPLE,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    apiLevel = 33,
    wallpaper = Wallpapers.GREEN_DOMINATED_EXAMPLE,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    apiLevel = 33,
    wallpaper = Wallpapers.YELLOW_DOMINATED_EXAMPLE,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    apiLevel = 33,
    wallpaper = Wallpapers.YELLOW_DOMINATED_EXAMPLE,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun PreviewCoffeeDrinkAppBar() {
    AppTheme {
        CoffeeDrinkAppBar()
    }
}