package snapshot.testing.lazycolumn_previews.compose_screenshot

import android.os.Build
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import com.android.tools.screenshot.PreviewTest
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.AppTheme
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.CoffeeDrinkItem
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.CoffeeDrinkList
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.R

/**
 * Record: ./gradlew :lazycolumnscreen-previews:compose-screenshot:updateDebugScreenshotTest
 * Verify: ./gradlew :lazycolumnscreen-previews:compose-screenshot:validateDebugScreenshotTest
 *
 * Keep in mind that currently, Compose Preview screenshot testing tool only supports previews
 * in the "screenshotTest" source, so previews in the "main" source are ignored
 */
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
    @PreviewTest
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

    @PreviewTest
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

    @PreviewTest
    @Preview(locale = "ar-rXB")
    @Composable
    fun CoffeeDrinkListPseudoLocalePreview() {
        AppTheme {
            CoffeeDrinkList(
                coffeeDrink = coffeeDrink
            )
        }
    }

    @PreviewTest
    @Preview(apiLevel = 31)
    @Composable
    fun CoffeeDrinkListApiLevelPreview() {
        AppTheme {
            CoffeeDrinkList(
                coffeeDrink = coffeeDrink.copy(name = "API ${Build.VERSION.SDK_INT}")
            )
        }
    }
}