package com.example.road.to.effective.snapshot.testing.lazycolumnscreen

class DummyCoffeeDrinksDataSource : CoffeeDrinkDataSource {

    override fun getCoffeeDrinks(): List<CoffeeDrink> {
        return mutableListOf(
            CoffeeDrink(
                id = 1L,
                name = "Americano",
                imageUrl = R.drawable.americano_small,
                description = "Americano is a type of coffee drink prepared by diluting an espresso with hot water, giving it a similar strength to, but different flavour from, traditionally brewed coffee. ",
                ingredients = "Espresso, Water",
                orderDescription = "150 ml",
                price = 6.5,
                isFavourite = false
            ),
            CoffeeDrink(
                id = 2L,
                name = "Cappuccino",
                imageUrl = R.drawable.cappuccino_small,
                description = "A cappuccino is an espresso-based coffee drink that originated in Italy, and is traditionally prepared with steamed milk foam.",
                ingredients = "Espresso, Steamed milk foam",
                orderDescription = "250 ml",
                price = 6.0,
                isFavourite = false
            ),
            CoffeeDrink(
                id = 3L,
                name = "Espresso",
                imageUrl = R.drawable.espresso_small,
                description = "Espresso is coffee of Italian origin, brewed by forcing a small amount of nearly boiling water under pressure (expressing) through finely-ground coffee beans.",
                ingredients = "Ground coffee, Water",
                orderDescription = "200 ml",
                price = 5.0,
                isFavourite = false
            ),
            CoffeeDrink(
                id = 4L,
                name = "Espresso Macchiato",
                imageUrl = R.drawable.espresso_macchiato_small,
                description = "Espresso Macchiato is a coffee beverage (a single or double espresso topped with a dollop of heated, foamed milk).",
                ingredients = "Espresso, Foamed milk",
                orderDescription = "300 ml",
                price = 6.5,
                isFavourite = false
            ),
            CoffeeDrink(
                id = 5L,
                name = "Frappino",
                imageUrl = R.drawable.frappino_small,
                description = "Frappino is a blended coffee drinks. It consists of coffee base, blended with ice and other various ingredients, usually topped with whipped cream.",
                ingredients = "Espresso, Cold milk, Sugar, Ice cubes, Irish Cream flavoured syrup, Whipped cream, Chocolate sauce",
                orderDescription = "400 ml",
                price = 6.0,
                isFavourite = false
            ),
            CoffeeDrink(
                id = 6L,
                name = "Iced Mocha",
                imageUrl = R.drawable.iced_mocha_small,
                description = "Iced Mocha is a coffee beverage. It based on Espresso and chocolate syrup with cold milk, foam and whipped cream or ice cream.",
                ingredients = "Cold coffee, Milk, Chocolate syrup, Whipped cream, Ice cream",
                orderDescription = "400 ml",
                price = 6.5,
                isFavourite = false
            ),
            CoffeeDrink(
                id = 7L,
                name = "Irish coffee",
                imageUrl = R.drawable.irish_coffee_small,
                description = "Irish coffee is a cocktail consisting of hot coffee, Irish whiskey, and sugar stirred, and topped with cream.",
                ingredients = "Irish whiskey, Hot strong brewed coffee, Heavy whipping cream, Sugar, Creme de menthe liqueur",
                orderDescription = "250 ml",
                price = 6.0,
                isFavourite = false
            ),
            CoffeeDrink(
                id = 8L,
                name = "Latte",
                imageUrl = R.drawable.latte_small,
                description = "A latte is a coffee drink made with espresso and steamed milk.",
                ingredients = "Espresso, Steamed milk",
                orderDescription = "300 ml",
                price = 6.0,
                isFavourite = false
            ),
            CoffeeDrink(
                id = 9L,
                name = "Latte Macchiato",
                imageUrl = R.drawable.latte_macchiato_small,
                description = "Latte Macchiato is a coffee beverage; the name literally means stained milk.",
                ingredients = "Espresso, Milk, Milk foam, Flavoured coffee syrup",
                orderDescription = "300 ml",
                price = 6.5,
                isFavourite = false
            ),
            CoffeeDrink(
                id = 10L,
                name = "Mocha",
                imageUrl = R.drawable.mocha_small,
                description = "A Mocha, also called mocaccino, is a chocolate-flavored variant of a Latte.",
                ingredients = "Espresso, Chocolate flavoring",
                orderDescription = "300 ml",
                price = 6.0,
                isFavourite = false
            )
        )
    }
}
