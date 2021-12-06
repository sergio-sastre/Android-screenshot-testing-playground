<a href="https://androidweekly.net/issues/issue-479">
<img src="https://androidweekly.net/issues/issue-479/badge">
</a><a href="https://androidweekly.net/issues/issue-485">
<img src="https://androidweekly.net/issues/issue-485/badge">
</a>

# Road to effective snapshot testing </br>

![snapshotVsUiTests](https://user-images.githubusercontent.com/6097181/144911921-bae6182b-dae7-4f59-9dba-c88c9052b9b7.gif)

A sample repo containing code samples for the best practices for snapshot testing on Android. These have been discussed on:
1. my blog series on [snapshot testing](https://sergiosastre.hashnode.dev/an-introduction-to-snapshot-testing-on-android-in-2021). 
2. [Droidcon Berlin 2021](https://www.droidcon.com/2021/11/10/an-introduction-to-effective-snapshot-testing-on-android/)
3. [Droidcon London 2021](https://www.droidcon.com/2021/11/17/an-introduction-to-effective-snapshot-testing-on-android-2/)

This repo includes some samples to snapshot test Dialogs and ViewHolders for now, but I will also add Composable and custom View samples at least. 
These tests are written as:
1. **Parameterized Tests**: Write the test once and run it under all desired configurations. This includes snapshot tests under various **Font sizes**
2. **Filtered Parameterized Tests**: Create filters to group the configurations, so that we can run only the tests corresponding to a certain group. As described later in this ReadMe, this can be useful when having a large bunch of snapshot tests, in order to reduce building times. That way we can select to run a group of them on every PR (e.g. the essential ones), and the rest once a day.

All the samples use [Shot from Karumi](https://github.com/Karumi/Shot), what is a great library that facilitates
snasphot testing.
I've been using an emulator with API 23 for a matter of simplicity. That is because `Shot` requires less
configuration for API < 29. 

**WARNING**: Disable animations on the emulator *via settings* before running snapshot test. Unfortunately, `testOptions { animationsDisabled = true }` does not work in all APIs

## Parameterized Snapshot Tests
They enable us to test a given view with all possible different configurations, namely:
  1. Theme (Light and Dark)
  2. Font Size
  3. Languages
  4. View dimensions (width and height)
  5. Specific view states
  6. Others...

With Parameterized snapshot test we can write the test once and run it for all the desired configurations!
This can be achieved by using `org.junit.runners.Parameterized` as in the example below.

1. Create a `TestItem` which encapsulates all the info we want to inject as parameter. I strongly
recommend to add a testName to it, which must be unique among all tests. This is used to distinguish
each snapshot file from the rest.
```kotlin
class TestItem(
    val fontScale: FontScale,
    val theme: DialogTheme,
    val texts: Array<Int>,
    val testName: String
)
```

2. Write the method that inflates the view and takes/compares the screenshot. Use the parameters from
`TestItem` to set the view into the right state.
If you also want to enable different `Font size` configurations to your tests, you can add the
[FontSizeTestRule](https://github.com/sergio-sastre/FontSizeTestRule) library to your project. For example,
you can use the `FontSizeActivityScenario` as follows:
```kotlin
object DeleteDialogSnapshotHelper : ScreenshotTest {
    fun snapDeleteDialogWithActivityScenario(testItem: DeleteDialogTestItem) {
        val activity = FontSizeActivityScenario.launchWith(testItem.fontScale)
            .waitForActivity(testItem.theme.themId)

        val dialog = waitForView {
            DialogBuilder.buildDeleteDialog(
                activity,
                onDeleteClicked = {/* no-op*/ },
                bulletTexts = itemArray(testItem.texts)
            )
        }

        compareScreenshot(
            dialog,
            name = testItem.testName,
            widthInPx = testItem.dialogWidth.widthInPx
        )
    }
}
```

3. Finally, we need to add the parameters. We will use `org.junit.runners.Parameterized` for that.
We will pass the `TestItem` in the class constructor, and define each single configuration to be run
inside a method annotated with `@Parameterized.Parameters`.

```kotlin
@RunWith(Parameterized::class)
class DeleteDialogWithFontSizeActivityScenarioTest(private val testItem: DeleteDialogTestItem) : ScreenshotTest {

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Array<DeleteDialogTestItem> =
            arrayOf(
                // spacious item: small font, little wording, wide view
                DeleteDialogTestItem(
                    SMALL,
                    MATERIAL_DARK_DIALOG,
                    arrayOf(R.string.shortest),
                    DialogWidth.WIDE,
                    "DARK_SMALL_WIDE"
                ),
                // suffocated item: huge font, a lot of wording, narrow view
                DeleteDialogTestItem(
                    HUGE,
                    MATERIAL_DARK_DIALOG,
                    repeatedItem(7, R.string.largest),
                    DialogWidth.NARROW,
                    "DARK_HUGE_NARROW"
                )
            )
    }

    @UnhappyPath
    @Test
    fun snapDialog() {
        DeleteDialogSnapshotHelper.snapDeleteDialogWithActivityScenario(testItem)
    }
}
```
Now you can just run your test with `./gradlew executeScreenshotTests -Precord` and you should see the
results!

## Filter Parameterized Tests
Running snapshot tests for all configurations on every PR can be very time-consuming and lead to
incrementally slower builds. One approach to solve that issue is to run only a part of those tests
on every PR (e.g. the most common config, our "smoke" tests) and all snapshot tests - or "all non-smoke
tests" - once a day (e.g. during the night or whenever it disturbs your team the least).
In doing so, we get informed of the most important visual regression bugs on every PR (i.e. blocking bugs),
and still get notified of the non-blocking bugs once a day.

We can accomplish this by filtering our tests with
`-Pandroid.testInstrumentationRunnerArguments.annotation=com.your.package.YourAnnotation`
or
`-Pandroid.testInstrumentationRunnerArguments.notAnnotation=com.your.package.YourAnnotation`

**Warning**: These arguments are supported by `org.junit.runners.Parameterized`, but not by all runners,
e.g. `JUnitParams` fails if used.

So first of all, we need to create our annotation
```kotlin
annotation class UnhappyPath
```

And then we add another Test Class that reuses our previously defined `TestItem` and `snapTrainingViewHolder(testItem)`
containing the "most common config" parameters, like here below
```kotlin
@RunWith(Parameterized::class)
class TrainingItemUnhappyPath(private val testItem: TrainingTestItem) : ScreenshotTest {

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Array<TrainingTestItem> =
            arrayOf(
                withWordsToTrainUnhappyPath(),
                withoutWordsToTrainUnhappyPath(ViewWidth.NARROW),
                withoutWordsToTrainUnhappyPath(ViewWidth.WIDE),
            )
    }

    @UnhappyPath
    @Test
    fun snapTrainingItem() {
        TrainingViewSnapshotHelper.snapTrainingViewHolder(testItem)
    }
}
```

Take a look at [TrainingViewHolderTest.kt](https://github.com/sergio-sastre/RoadToEffectiveSnapshotTesting/blob/master/app/src/androidTest/java/com/example/road/to/effective/snapshot/testing/parameterized/trainingitem/TrainingViewHolderTest.kt) to see how these Parameterized Tests are implemented and run
`./gradlew executeScreenshotTests -Precord -Pandroid.testInstrumentationRunnerArguments.annotation=com.example.road.to.effective.snapshot.testing.utils.annotations.UnhappyPath`
to verify that only the `@UnhappyTest`s run!

## What is coming next:
1. More advance samples
2. Tips to remove flakiness
3. Tips to increase test execution speed
4. Jetpack compose samples
and more...

## Attribution of icons in the app
Icons made by <a href="https://www.freepik.com" title="Freepik">Freepik</a> from <a href="https://www.flaticon.com/" title="Flaticon">www.flaticon.com</a>
</br>
Icons made by <a href="https://www.flaticon.com/authors/surang" title="surang">surang</a> from <a href="https://www.flaticon.com/" title="Flaticon">www.flaticon.com</a>

