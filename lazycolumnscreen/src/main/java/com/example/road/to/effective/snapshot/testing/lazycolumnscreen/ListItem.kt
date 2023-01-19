package com.example.road.to.effective.snapshot.testing.lazycolumnscreen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val COFFEE_DRINK_IMAGE_SIZE = 72.dp

@Preview
@Composable
fun PreviewListItem() {
    val mapper = CoffeeDrinkItemMapper()
    val coffeeDrink = mapper.map(
        DummyCoffeeDrinksDataSource().getCoffeeDrinks().first()
    )

    MaterialTheme(colors = lightThemeColors) {
        CoffeeDrinkListItem(
            coffeeDrink = coffeeDrink
        )
    }
}

@Composable
fun CoffeeDrinkList(
    coffeeDrink: CoffeeDrinkItem
) {
    Column {
        CoffeeDrinkListItem(
            coffeeDrink = coffeeDrink
        )
        AppDivider(PaddingValues(start = COFFEE_DRINK_IMAGE_SIZE))
    }
}

@Composable
fun CoffeeDrinkListItem(
    coffeeDrink: CoffeeDrinkItem
) {
    Row {
        CoffeeDrinkLogo(id = coffeeDrink.imageUrl)
        Box(
            modifier = Modifier.weight(1f)
        ) {
            Column {
                CoffeeDrinkTitle(title = coffeeDrink.name)
                CoffeeDrinkIngredient(ingredients = coffeeDrink.ingredients)
            }
        }
    }
}

@Composable
private fun CoffeeDrinkLogo(@DrawableRes id: Int) {
    Surface(
        modifier = Modifier
            .size(COFFEE_DRINK_IMAGE_SIZE)
            .padding(8.dp)
            .shadow(elevation = 4.dp, shape = CircleShape),
        color = Color(0xFFFAFAFA)
    ) {
        Image(
            painter = BitmapPainter(ImageBitmap.imageResource(id = id)),
            modifier = Modifier.fillMaxSize(),
            contentDescription = null
        )
    }
}

@Composable
private fun CoffeeDrinkTitle(title: String) {
    Text(
        text = title,
        modifier = Modifier.padding(top = 8.dp, end = 8.dp),
        style = TextStyle(fontSize = 24.sp),
        color = MaterialTheme.colors.onSurface,
        maxLines = 1
    )
}

@Composable
private fun CoffeeDrinkIngredient(ingredients: String) {
    Text(
        text = ingredients,
        modifier = Modifier
            .padding(end = 8.dp)
            .alpha(0.54f),
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        style = MaterialTheme.typography.body1,
        color = MaterialTheme.colors.onSurface
    )
}
