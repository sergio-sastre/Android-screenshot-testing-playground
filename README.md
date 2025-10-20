part of blog posts</br>
<a href="https://androidweekly.net/issues/issue-479">
<img src="https://androidweekly.net/issues/issue-479/badge">
</a><a href="https://androidweekly.net/issues/issue-485">
<img src="https://androidweekly.net/issues/issue-485/badge">
</a><a href="https://androidweekly.net/issues/issue-512">
<img src="https://androidweekly.net/issues/issue-512/badge">
</a>
<img src="https://androidweekly.net/issues/issue-558/badge">
</a>
<a href="https://androidweekly.net/issues/issue-596">
<img src="https://androidweekly.net/issues/issue-596/badge">
</a>

# Android screenshot testing playground </br>

![snapshotVsUiTests](https://user-images.githubusercontent.com/6097181/144911921-bae6182b-dae7-4f59-9dba-c88c9052b9b7.gif)

A sample repo to introduce screenshot testing in Android.<br/> It contains a wide variety of
examples written with different screenshot testing libraries for a better comparison among them.
These examples include tests for screens like the one above (module `:recyclerviewscreen`)

> **Warning**</br>
> It is configured with AGP 8.7.2, so it requires Android Studio Lady Bug or higher!

## Awards

<p align="center">
   <img src="https://github.com/sergio-sastre/Android-screenshot-testing-playground/assets/6097181/2538e857-e3c4-4ad6-8737-954b3a3ab7b2">
</p>

Android-screenshot-testing-playground won a **[Google Open Source Peer Bonus](https://github.com/sergio-sastre/Android-screenshot-testing-playground/files/15421726/OSPB-2024-Sergio-Sastre-award-letter.pdf)
award in 2024**, whose winners were listed in [Google Open Source blog](https://opensource.googleblog.com/2024/06/google-open-source-peer-bonus-program-first-group-2024-recipients.html). Thanks to everybody who has found this project insightful and helped get
Android-screenshot-testing-playground where it is today.
<br clear="left"/>

## Sponsors

Thanks to [Screenshotbot](https://screenshotbot.io) for their support!
[<img align="left" width="100" src="https://user-images.githubusercontent.com/6097181/192350235-b3b5dc63-e7e7-48da-bdb6-851a130aaf8d.png">](https://screenshotbot.io)

By using Screenshotbot instead of the in-build record/verify modes provided by most screenshot
libraries, you'll give your colleages a better developer experience, since they will not be required
to manually record screenshots after every run, instead getting notifications on their Pull
Requests.
<br clear="left"/>
<br/>

Thanks to [EmergeTools](https://www.emergetools.com) for their support!
[<img align="left" width="100" src="https://www.emergetools.com/images/emergetoolsstandard.png">](https://docs.emergetools.com/docs/snapshot-management-diffing)

Emerge automatically generates and diffs snapshots on your behalf, eliminating complicated CI setup
with Emulators, file storage, and golden/diffing management.
<br clear="left"/>

# Introduction

This repo showcases how to snapshot test Dialogs, ViewHolders, RecyclerViews, Activities, Fragments, Jetpack Compose & **Composable @Previews**!<br/>
And even better: It is ready for you to add your own examples and try screenshot testing with many
libraries on your own!

In order to help find the desired examples, the app is modularized accordingly:

1. `:dialogs`: Showcases how to screenshot test dialogs created with DialogBuilder from the android
   View system. Examples for Compose dialogs will be added as well
2. `:recyclerviewscreen`: Contains screenshot tests for Activities, Fragments, ViewHolders and
   RecyclerViews.
3. `:recyclerviewscreen-previews`: Includes screenshot tests examples generated from @Previews. This wrap a `ViewHolder` inside an `AndroidView` Composable to enable Previews for XML-based layouts under `:recyclerviewscreen`
4. `:lazycolumnscreen`: Includes Jetpack Compose screenshot tests examples, as well as examples for
   Activities & Fragments.
5. `:lazycolumnscreen-previews`: Includes screenshot tests examples generated from @Previews of Composables in `:lazycolumnscreen` 

Each of these modules contains submodules. Each submodule name corresponds to a screenshot testing
library. You'll find screenshot test examples with that library in it.

As of June 2024, there are many screenshot testing libraries that facilitate automated screenshot
testing, namely:

1. Google [Compose Preview Screenshot Testing tool](https://developer.android.com/studio/preview/compose-screenshot-testing)
2. Cashapp [Paparazzi](https://github.com/cashapp/paparazzi)
3. [Roborazzi from takahirom](https://github.com/takahirom/roborazzi)
4. Dropbox [Dropshots](https://github.com/dropbox/dropshots)
5. [Shot from pedrovgs](https://github.com/pedrovgs/Shot)
6. Ndtp [Android-testify](https://github.com/ndtp/android-testify) <sup>1<sup/>
7. Facebook [screenshot-tests-for-android](https://github.com/facebook/screenshot-tests-for-android) 
8. QuickBird [Snappy](https://github.com/QuickBirdEng/kotlin-snapshot-testing)

All of them have their own pros and cons.
The ultimate goal of this repo is to help you choose the libraries that better meet your project's
needs!

In order to do that, it contains the same/similar examples but written with different libraries:

1. [Compose Preview Screenshot Testing tool](https://developer.android.com/studio/preview/compose-screenshot-testing) (check `:lazycolumnscreen-previews` and `:recyclerviewscreen-previews`)
2. [Paparazzi](https://github.com/cashapp/paparazzi)
3. [Roborazzi](https://github.com/takahirom/roborazzi)
4. [Dropshots](https://github.com/dropbox/dropshots) (coming to `:lazycolumnscreen-previews` soon, but already available in `:recyclerviewscreen-previews`)
5. [Shot](https://github.com/pedrovgs/Shot) (coming to `:lazycolumnscreen-previews` soon, but already available in `:recyclerviewscreen-previews`)
6. [Android-testify](https://github.com/ndtp/android-testify) (coming to `:lazycolumnscreen-previews` soon, but already available in `:recyclerviewscreen-previews`)

**BONUS**:
It also contains examples of **Cross-Library Screenshot Tests**: *the very same screenshot tests
running with multiple libraries, namely: Paparazzi, Roborazzi, Shot, Dropshots & Android-Testify*.
Compose Screenshot Testing Tool support coming soon.
For that it
uses [Android UI Testing Utils 2.x.x](https://github.com/sergio-sastre/AndroidUiTestingUtils)

You can read more about it in this blog post series:

1. [A World Beyond Libraries: Cross-Library Screenshot tests on android](https://sergiosastre.hashnode.dev/a-world-beyond-libraries-cross-library-screenshot-tests-on-android)
2. [Write Once, Test Everywhere: Cross-Library Screenshot Testing with AndroidUiTestingUtils 2.0.0](https://sergiosastre.hashnode.dev/write-once-test-everywhere-cross-library-screenshot-testing-with-androiduitestingutils)</br></br>
   More screenshot test examples, as well as examples with other libraries will be continuously
   added.

<sup>1</sup> Android-testify was started at Shopify and changed to Ndtp in summer 2022.

## Table of Contents

- [Before you start...](#before-you-start)
    - [The need for screenshot testing](#the-need-for-screenshot-testing)
    - [Emulators](#emulators)
        - [Animations](#animations)
- [Comparing screenshot testing libraries](#comparing-screenshot-testing-libraries)
    - [Paparazzi vs. on-device screenshot testing libraries](#paparazzi-vs-on-device-screenshot-testing-libraries)
        - [Summary: Pros and Cons](#summary-pros-and-cons)
- [Recording and verifying screenshots](#recording-and-verifying-screenshots)
    - [On-device tests with Android Orchestrator](#on-device-tests-with-android-orchestrator)
    - [Compose Preview Screenshot Testing tool](#compose-preview-screenshot-testing-tool)
    - [Paparazzi](#paparazzi)
    - [Roborazzi](#roborazzi)
    - [Dropshots](#dropshots)
    - [Shot](#shot)
    - [Android-Testify](#android-testify)
    - [Cross-library](#cross-library)
- [Parameterized screenshot tests](#parameterized-screenshot-tests)
- [Filtered parameterized screenshot tests](#filtered-parameterized-screenshot-tests)
    - [Instrumented tests](#instrumented-tests)
    - [Gradle tests](#gradle-tests)
- [What is coming next](#what-is-coming-next)
- [Code attribution](#code-attribution)
- [Attribution of icons in the app](#attribution-of-icons-in-the-app)

## Before you start...

### The need for screenshot testing

I strongly recommend to have a look at my blog series
on [snapshot testing](https://sergiosastre.hashnode.dev/an-introduction-to-snapshot-testing-on-android-in-2021)

If reading is not your thing, you can always watch my 2021 Droidcon tech-talks on the matter:

1. [Droidcon Berlin 2021](https://www.droidcon.com/2021/11/10/an-introduction-to-effective-snapshot-testing-on-android/)
2. [Droidcon London 2021](https://www.droidcon.com/2021/11/17/an-introduction-to-effective-snapshot-testing-on-android-2/)

### Emulators

For instrumented screenshot testing (which excludes Paparazzi), I've been using emulators running
API 27-31.
Moreover, if you are running screenshot tests on a Windows machine, beware that Shot had
some [issues in the past](https://github.com/pedrovgs/Shot/issues/244), although they should be
already solved.

#### Animations

Instrumented screenshot tests might have issues due to running animations at the moment the snapshot
are taken.

In order to keep animations disabled, this project uses `testOptions { animationsDisabled = true }`
in the gradle file. Unfortunately, it does not work on all APIs (e.g. API 26 or lower). Therefore,
if you
come across some issues, disable animations on the emulator *via settings* before running the
screenshot tests.

## Comparing screenshot testing libraries

### Paparazzi vs. on-device screenshot testing libraries

**Need for emulators**

Google's brand new Compose Screenshot Testing tool, as well as Roborazzi & Paparazzi let you run screenshot tests on the JVM, without emulators/devices.
I'm still evaluating Roborazzi and Compose Screenshot Testing tool, so this section refers to Paparazzi only, and will be extended in
the future.

Although running screenshot tests on the JVM comes with some speed wins, its main advantage is that
one doesn't have to deal with emulator and their problems, such as:

1. Emulators eventually freezing/crashing (specially on CI)
2. "Insufficient storage" exception
3. "Out of Memory" exceptions

**Rendering elevation in generated screenshots**

Paparazzi uses PixelCopy to generate bitmaps out of Views.
Most on-device screenshot testing frameworks use Canvas as default<sup>1</sup> to generate bitmaps,
what ignores elevation.

This is specially noticeable in API 31:

<p align="center">
<img width="350" src="https://user-images.githubusercontent.com/6097181/209678572-c4610c75-0122-41c4-bfae-304e8a633b2d.jpeg">
</p>

However, on-device screenshot testing libraries also accept bitmaps as arguments of their
take/verify screenshot methods.

```kotlin
// Shot
compareScreenshot(
    bitmap = pixelCopyBitmapFromView
)
```

```kotlin
// Dropshots
dropshots.assertSnapshot(
    bitmap = pixelCopyBitmapFromView
)
```

Therefore, they could render elevation by converting views to bitmaps using PixelCopy.
[Android UI Testing Utils](https://github.com/sergio-sastre/AndroidUiTestingUtils) provides
the `drawToBitmapWithElevation()` method for that.

And the resulting screenshot would render elevation
<p align="center">
<img width="350" src="https://user-images.githubusercontent.com/6097181/209678214-9e4664b7-f898-4173-a9f7-36dfc764b035.png">
</p>

You can find such examples in this repo, under the `bitmap` folder under any `:dropshots`, `:shot`
and `:android-testify` module.

<sup>1</sup> Shot doesn't use Canvas when using `compareScreenshot(composeRule)`, so those
screenshots draw elevation. It does use Canvas for Views, Activities, Fragments & Dialogs though.

**Screenshot testing Activites and Fragments**

ActivityScenarios and FragmentScenarios are compatible with Robolectric, which stubs the Android
framework to run instrumented tests on the JVM.
Therefore, we could also use Robolectric to run Activity/Fragment screenshot tests on the JVM with
Paparazzi, theoretically.
However, it crashes in doing so with some Byte Buddy exception at runtime, likely due to some
clashes with Robolectric.

Roborazzi, on the other hand, is built on top of Robolectric, so ActivityScenarios and
FragmentScenarios are compatible with it.

This means, only on-device screenshot testing frameworks & Roborazzi can be used for snapshoting
Activites/Fragments. Only Paparazzi cannot.

**Rendering problems**

Paparazzi relies on layoutlib to record screenshots. That's a private library used to render the xml
layouts and Compose previews in Android Studio.
This comes with some limitations.
For example,

1. [Composables using NavHost cannot be rendered](https://github.com/cashapp/paparazzi/issues/635)
   in the @Preview, and therefore, Paparazzi cannot either, for now.
2. Renders incorrectly UI elements that use multiple `View.animate()`
   and/or `ObjectAnimator.ofPropertyValuesHolder()` for animations. You can check it out yourself by
   running
   `./gradlew :recyclerviewscreen:paparazzi:recordPaparazziDebug` in this repo. For instance:

| View.animate()                                                                                                               |                                               View.animate() + ObjectAnimator                                                |
|------------------------------------------------------------------------------------------------------------------------------|:----------------------------------------------------------------------------------------------------------------------------:|
| <img width="350" src="https://user-images.githubusercontent.com/6097181/209678715-c7356e7b-7d4c-413a-942f-76e42e445d0b.png"> | <img width="350" src="https://user-images.githubusercontent.com/6097181/209678760-b84bb060-03fb-4050-b283-ab2e28415df7.png"> |

On the other hand, on-device screenshot testing has its own issues as well. Most of them happen due
to the **hardware accelerated drawing model**. This happens for example, when running the same test
on machines with different architectures<sup>1</sup> e.g. Macbook Pro with M1 chip vs. Intel x64. It
might cause issues related to:

1. Shadows & elevation
2. Font smoothing & anti-aliasing
3. Image decompression and rendering
4. Alpha-blending.

Nevertheless, you can solve/mitigate such issues as follows:

1. Hardware acceleration can be enabled/disabled at different levels e.g. Activity, Window, View...
   to reduce such issues. Keep in mind that this affects how the screenshots are rendered.
2. Most libraries provide a "tolerance/maxPixelDiff" mechanism to set some error threshold when
   verifying the screenshot. Although it isn't a real fix, you could use it to mitigate such issues.
   There is an [interesting blog](https://ndtp.github.io/android-testify/blog/platform-differences)
   on android-testify about this.
3. The safest solution is to generate the screenshots only on the CI, then pushing them in a new
   commit on your PRs. Same goes for "verify". This ensures that the screenshots are always
   generated using the same hardware.

Read more about hardware acceleration in
the [official Android documentation](https://developer.android.com/topic/performance/hardware-accel)

<sup>1</sup> There is evidence of such problems in Paparazzi as well, when running tests on
different operating systems, as stated [here](https://github.com/cashapp/paparazzi/issues/311)

**Stability: Android Gradle Plugin and Compose runtime updates**

Unfortunately, any Android Gradle Plugin (a.k.a. AGP) or Compose update can make your working
Paparazzi screenshot tests break... or fix those broken.
For instance:

1. When updating to Compose runtime
   1.4.x: [this issue](https://github.com/cashapp/paparazzi/issues/641) & [the corresponding fix](https://github.com/cashapp/paparazzi/pull/650/files)
2. Before updating AGP to that required by Android Studio Dolphin:
   [Compose Dialog rendering issue](https://github.com/cashapp/paparazzi/issues/619)

#### Summary: Pros and Cons

Let's summarize.

**Pros**

1. No emulators needed.
    1. Faster
    2. No emulator troubleshooting
2. Uses PixelCopy by default to generate bitmaps out of the views. Thus, screenshots render UI
   elements with elevation (e.g. shadows)

**Cons**

1. Cannot screenshot Activities or Fragments
2. Rendering problems
    1. Incorrect screenshots for UI components that call View.animate() or
       ObjectAnimator.ofPropertyValuesHolder() several times.
    2. Only renders what the Compose @Previews can display
3. Fragile to AGP & Jetpack Compose updates

## Recording and verifying screenshots

For screenshot testing, 2 tasks are required:

1. **Record**. When executed, it generates a snapshot file for each test that will be taken as
   reference.
2. **Verify**. When executed, it generates a snapshot file for each test that will be compared,
   pixel by pixel, with the one taken as reference when recording.

All Screenshot testing frameworks provide at least these 2 tasks.

> **Warning**</br>
> All the commands in the description are for MacOS. You might need to adjust them depending on the
> operating system of your machine.

### On-device tests with Android Orchestrator

By default, Android Orchestrator is disabled.
That's because it makes the screenshot test run much slower, while adding no value. Android
orchestrator might make
sense when running screenshot test that might influence each other. For instance, when screenshot
testing Activities or Fragments
where we fetch some data.

For screenshot test that inflate a view and populate it with some data before the snapshot, it makes
little sense.

However, you can run Dropshots, Shot and Android-testify screenshot tests with Android Orchestrator
in this repo by adding the following to the corresponding command:
`-PuseOrchestrator`

For instance, to record with Dropshots:
`./gradlew :dialogs:dropshots:recordScreenshots -PuseOrchestrator`

### [Compose Preview Screenshot Testing tool](https://developer.android.com/studio/preview/compose-screenshot-testing)
The brand new Google's screenshot testing tool!
No emulators required. 

The tests are auto-generated!
Just place your previews in the new "screenshotTest" source.
For now, it cannot auto-generate tests from previews in the "main" source

Run the following gradle tasks

1. **Record**: `./gradlew :lazycolumnscreen-previews:compose-screenshot:recordPaparazziDebug`. 
2. **Verify**: `./gradlew :lazycolumnscreen-previews:compose-screenshot:verifyPaparazziDebug`.

### [Paparazzi](https://github.com/cashapp/paparazzi)

No emulators required.
Run the following gradle tasks depending on the module:

1. **Record**: `./gradlew :module_name:paparazzi:recordPaparazziDebug`. For instance:
    1. `./gradlew :dialogs::paparazzi:recordPaparazziDebug`
    2. `./gradlew :recyclerviewscreen:paparazzi:recordPaparazziDebug`
    3. `./gradlew :recyclerviewscreen-previews:paparazzi:recordPaparazziDebug`
    4. `./gradlew :lazycolumnscreen:paparazzi:recordPaparazziDebug`
    5. `./gradlew :lazycolumnscreen-previews:paparazzi:recordPaparazziDebug`
2. **Verify**: `./gradlew :module_name:paparazzi:verifyPaparazziDebug`. For instance:
    1. `./gradlew :dialogs:paparazzi:verifyPaparazziDebug`
    2. `./gradlew :recyclerviewscreen:paparazzi:verifyPaparazziDebug`
    3. `./gradlew :recyclerviewscreen-previews:paparazzi:verifyPaparazziDebug`
    4. `./gradlew :lazycolumnscreen:paparazzi:verifyPaparazziDebug`
    5. `./gradlew :lazycolumnscreen-previews:paparazzi:verifyPaparazziDebug`

> **Note**</br>
> You can record/verify the tests in parallel with the gradle property -Pparallel e.g.
> `./gradlew :module_name:paparazzi:recordPaparazziDebug -Pparallel`
> Please note that running tests in parallel is only worthwhile when dealing with a large number of
> tests.

### [Roborazzi](https://github.com/takahirom/roborazzi)

No emulators required.
Run the following gradle tasks depending on the module:

1. **Record**: `./gradlew :module_name:roborazzi:recordRoborazziDebug`. For instance:
    1. `./gradlew :dialogs::roborazzi:recordRoborazziDebug`
    2. `./gradlew :recyclerviewscreen:roborazzi:recordRoborazziDebug`
    3. `./gradlew :recyclerviewscreen-previews:roborazzi:recordRoborazziDebug`
    4. `./gradlew :lazycolumnscreen:roborazzi:recordRoborazziDebug`
    5. `./gradlew :lazycolumnscreen-previews:roborazzi:recordRoborazziDebug`
2. **Verify**: `./gradlew :module_name:roborazzi:verifyRoborazziDebug`. For instance:
    1. `./gradlew :dialogs:roborazzi:verifyRoborazziDebug`
    2. `./gradlew :recyclerviewscreen:roborazzi:verifyRoborazziDebug`
    3. `./gradlew :recyclerviewscreen-previews:roborazzi:verifyRoborazziDebug`
    4. `./gradlew :lazycolumnscreen:roborazzi:verifyRoborazziDebug`
    5. `./gradlew :lazycolumnscreen-previews:roborazzi:verifyRoborazziDebug`

In order to see the screenshots in Android Studio, change the view from "Android" to "Project".

> **Note 1**</br>
> Thanks to Roborazzi plugin, it is also possible to record/verify these tests directly from Android
> Studio.
> For recording, you need to add `roborazzi.test.record=true` in your `gradle.properties` file.
> For verifying, you need to add `roborazzi.test.verify=true` in your `gradle.properties` file.
> Remember to record before verifying, to have a reference to compare with.

> **Note 2**</br>
> You can record/verify the tests in parallel with the gradle property -Pparallel e.g.
> `./gradlew :module_name:roborazzi:recordRoborazziDebug -Pparallel`
> Please note that running tests in parallel is only worthwhile when dealing with a large number of
> tests.

### [Dropshots](https://github.com/dropbox/dropshots)

Start the emulator.
Then run the following gradle tasks depending on the module:

1. **Record**: `./gradlew :module_name:dropshots:recordScreenshots`. For instance:
    1. `./gradlew :dialogs:dropshots:recordScreenshots`
    2. `./gradlew :recyclerviewscreen:dropshots:recordScreenshots`
    3. `./gradlew :recyclerviewscreen-previews:dropshots:recordScreenshots`
    4. `./gradlew :lazycolumnscreen:dropshots:recordScreenshots`
2. **Verify**: `./gradlew :module_name:dropshots:connectedAndroidTest`. For instance:
    1. `./gradlew :dialogs:dropshots:connectedAndroidTest`
    2. `./gradlew :recyclerviewscreen:dropshots:connectedAndroidTest`
    3. `./gradlew :recyclerviewscreen-previews:dropshots:connectedAndroidTest`
    4. `./gradlew :lazycolumnscreen:dropshots:connectedAndroidTest`

> **Note**</br>
> Thanks to Dropshots plugin, it is also possible to record/verify these tests directly from Android
> Studio.
> By default, it will execute the verify task. For recording, you need to add `dropshots.record` in
> your `gradle.properties` file.
> Remember to record before verifying, to have a reference to compare with.

### [Shot](https://github.com/pedrovgs/Shot)

Start the emulator.
Then run the following gradle tasks depending on the module:

1. **Record**: `./gradlew :module_name:shot:executeScreenshotTests -Precord`. For instance:
    1. `./gradlew :dialogs:shot:executeScreenshotTests -Precord`
    2. `./gradlew :recyclerviewscreen:shot:executeScreenshotTests -Precord`
    3. `./gradlew :recyclerviewscreen-previews:shot:executeScreenshotTests -Precord`
    4. `./gradlew :lazycolumnscreen:shot:executeScreenshotTests -Precord`
2. **Verify**: `./gradlew :module_name:shot:executeScreenshotTests`. For instance:
    1. `./gradlew :dialogs:shot:executeScreenshotTests`
    2. `./gradlew :recyclerviewscreen:shot:executeScreenshotTests`
    3. `./gradlew :recyclerviewscreen-previews:shot:executeScreenshotTests`
    4. `./gradlew :lazycolumnscreen:shot:executeScreenshotTests`

> **Note**</br>
> The library says the record reports can be reviewed
> at `RoadToEffectiveSnapshotTesting/dialogs/shot/build/reports/shot/debug/index.html`
> However, it is wrong. The record reports can be reviewed
> at `RoadToEffectiveSnapshotTesting/dialogs/shot/build/reports/shot/debug/record/index.html`
> The path for the verification reports is right though.

### [Android-Testify](https://github.com/ndtp/android-testify)

Currently this is the only library that supports Gradle Managed Devices.
You can do it by directly running the following command:

1. **Record
   **: `./gradlew :module_name:android-testify:pixel3api30DebugAndroidTest -PuseTestStorage -PrecordModeGmd`.
   For instance:
    1. `./gradlew :dialogs:android-testify:pixel3api30DebugAndroidTest -PuseTestStorage -PrecordModeGmd`
    2. `./gradlew :recyclerviewscreen:android-testify:pixel3api30DebugAndroidTest -PuseTestStorage -PrecordModeGmd`
    3. `./gradlew :recyclerviewscreen-previews:android-testify:pixel3api30DebugAndroidTest -PuseTestStorage -PrecordModeGmd`
    4. `./gradlew :lazycolumnscreen:android-testify:pixel3api30DebugAndroidTest -PuseTestStorage -PrecordModeGmd`
2. **Verify**: `./gradlew :module_name:android-testify:pixel3api30DebugAndroidTest`. For instance:
    1. `./gradlew :dialogs:android-testify:pixel3api30DebugAndroidTest -PuseTestStorage`
    2. `./gradlew :recyclerviewscreen:android-testify:pixel3api30DebugAndroidTest -PuseTestStorage`
    3. `./gradlew :recyclerviewscreen-previews:android-testify:pixel3api30DebugAndroidTest -PuseTestStorage`
    4. `./gradlew :lazycolumnscreen:android-testify:pixel3api30DebugAndroidTest -PuseTestStorage`

> **Note 1**</br>
> You might also want to add the following gradle parameter to avoid verify failing for screenshots with elevation when executed on the same machine:
> -Pandroid.testoptions.manageddevices.emulator.gpu=host

> **Note 2**</br>
> Before verifying, you need to copy the generated screenshots under the corresponding module's
> build/outputs/managed_device_android_test_additional_output/...) to the correct location, as
> specified here: https://ndtp.github.io/android-testify/docs/recipes/gmd
> For that you can run the following gradle task:
> `./gradlew :module_name:android-testify:copyScreenshots -Pdevices=pixel3api30`. For instance:
>  1. `./gradlew :dialogs:android-testify:copyScreenshots -Pdevices=pixel3api30`
>  2. `./gradlew :recyclerviewscreen:android-testify:copyScreenshots -Pdevices=pixel3api30`
>  3. `./gradlew :lazycolumnscreen:android-testify:copyScreenshots -Pdevices=pixel3api30`

If you do not want to use Gradle Managed Devices, do as below:
First, start the emulator.
Then run the following gradle tasks depending on the module:

1. **Record**: `./gradlew :module_name:android-testify:screenshotRecord`. For instance:
    1. `./gradlew :dialogs:android-testify:screenshotRecord`
    2. `./gradlew :recyclerviewscreen:android-testify:screenshotRecord`
    3. `./gradlew :recyclerviewscreen-previews:android-testify:screenshotRecord`
    4. `./gradlew :lazycolumnscreen:android-testify:screenshotRecord`
2. **Verify**: `./gradlew :module_name:android-testify:screenshotTest`. For instance:
    1. `./gradlew :dialogs:android-testify:screenshotTest`
    2. `./gradlew :recyclerviewscreen:android-testify:screenshotTest`
    3. `./gradlew :recyclerviewscreen-previews:android-testify:screenshotTest`
    4. `./gradlew :lazycolumnscreen:android-testify:screenshotTest`

### Cross-Library

Run the very same screenshot tests with the screenshot testing library of your choice, among
Paparazzi, Roborazzi, Shot, Dropshots or Android-Testify.</br>
The most common scenario is to use 1 on-device (e.g. Shot, Dropshots, Android-Testify) and 1 JVM (
e.g. Paparazzi, Roborazzi) screenshot library.
For that, check the corresponding submodules
e.g. `:android-testify+paparazzi`, `:shot+roborazzi`, `:dropshots+roborazzi`</br>
You would execute such tests as you would do for the corresponding library
e.g. `./gradlew :lazycolumnscreen:dropshots+paparazzi:recordPaparazziDebug` would record with
Paparazzi.</br>

For examples containing 2+ on-device and/or 2+ JVM screenshot libraries, check the
corresponding `:crosslibrary` modules.</br>
Since they configure 2 on-device & 2 JVM screenshot libraries, you need to pass the library name via
command line for its correct execution. Here some examples with `:lazycolumnscreen` but same applies
to `:recyclerviewscreen` and `:dialogs`

1. **Record**:
    1. Paparazzi: `./gradlew :lazycolumnscreen:crosslibrary:recordPaparazziDebug -PscreenshotLibrary=paparazzi`
    2. Roborazzi: `./gradlew :lazycolumnscreen:crosslibrary:recordRoborazziDebug -PscreenshotLibrary=roborazzi`
    3. Shot:      `./gradlew :lazycolumnscreen:crosslibrary:executeScreenshotTests -Precord -PscreenshotLibrary=shot`
    4. Dropshots: `./gradlew :lazycolumnscreen:crosslibrary:recordScreenshots -PscreenshotLibrary=dropshots`
    5. Testify:   `./gradlew :lazycolumnscreen:crosslibrary:screenshotRecord -PscreenshotLibrary=android-testify`
2. **Verify**:
    1. Paparazzi: `./gradlew :lazycolumnscreen:crosslibrary:verifyPaparazziDebug -PscreenshotLibrary=paparazzi`
    2. Roborazzi: `./gradlew :lazycolumnscreen:crosslibrary:verifyRoborazziDebug -PscreenshotLibrary=roborazzi`
    3. Shot:      `./gradlew :lazycolumnscreen:crosslibrary:executeScreenshotTests -PscreenshotLibrary=shot`
    4. Dropshots: `./gradlew :lazycolumnscreen:crosslibrary:connectedAndroidTest -PscreenshotLibrary=dropshots`
    5. Testify:   `./gradlew :lazycolumnscreen:crosslibrary:screenshotTest -PscreenshotLibrary=android-testify`

> **Note**</br>
> You can also record and verify via Gradle Managed Devices with Android-Testify as specified in the
> previous section:
> 1. Record: `./gradlew :lazycolumnscreen:crosslibrary:pixel3api30DebugAndroidTest -PuseTestStorage -PrecordModeGmd`
> 2. Verify: `./gradlew :lazycolumnscreen:crosslibrary:pixel3api30DebugAndroidTest -PuseTestStorage`
>
> Before verifying, you need to copy the generated screenshots under the corresponding module's
> build/outputs/managed_device_android_test_additional_output/...) to the correct location, as
> specified here: https://ndtp.github.io/android-testify/docs/recipes/gmd
> For that you can run the following gradle task:
> `./gradlew :lazycolumnscreen:crosslibrary:copyScreenshots -Pdevices=pixel3api30`:

To enable cross-library screenshot testing, it
uses [Android UI Testing Utils 2.1.0](https://github.com/sergio-sastre/AndroidUiTestingUtils)

## Parameterized Screenshot Tests

Some tests in this repo are written as parameterized.
In doing so, we can test a given view with all possible different configurations, namely:

1. UI mode (Light and Dark)
2. Font size
3. Locale
4. Orientation
5. Custom themes
6. Display size
7. View dimensions (width and/or height)
8. Specific view states
9. Others...

With Parameterized snapshot test we can write the test once and run it for all the desired
configurations! You can read more about how we can profit from it here:

- [Design a pixel perfect Android app 🎨](https://sergiosastre.hashnode.dev/design-a-pixel-perfect-android-app-with-screenshot-testing)

This can be achieved by using
either `org.junit.runners.Parameterized`, `com.google.testing.junit.testparameterinjector.TestParameterInjector`
or in case of Roborazzi via `org.robolectric.ParameterizedRobolectricTestRunner`, as you'll find in
most examples in this repo.

Dropshots & Shot do not offer the possibility to set such configurations; Android-Testify only
partially; Paparazzi and Roborazzi (via Robolectric) do though. Nevertheless, we can set the desired
configurations for those libraries
with [Android UI Testing Utils](https://github.com/sergio-sastre/AndroidUiTestingUtils), as seen in
the examples.

> **Remark**
> The Parameterized runner injects the parameter passed in the Test class constructor to every
> single test. If that is not wished, consider using the TestParameterInjector.
> On the other hand, TestParameterInjector only works with instrumented test on API 24+. Keep that
> in
> mind.

## Filtered parameterized screenshot tests

Running snapshot tests for all configurations on every PR can be very time-consuming and lead to
incrementally slower builds. One approach to solve that issue is to run only a part of those tests
on every PR (e.g. the most common config, our "smoke/happy path" tests) and all snapshot tests -
or "all
non-smoke tests" - once a day (e.g. during the night or whenever it disturbs your team the least).
In doing so, we get informed of the most important visual regression bugs on every PR (i.e. blocking
bugs), and still get notified of the non-blocking bugs once a day.

We can accomplish this by filtering our tests accordingly.

### Instrumented tests

This applies for tests running on emulators/physical devices i.e. those using Dropshots, Shot or
Android-Testify

In order to filter tests, we need to provide the corresponding test instrumentation arguments. A
straightforward means to do that is to use custom annotations. For instance:

1. `-Pandroid.testInstrumentationRunnerArguments.annotation=com.your.package.YourAnnotation`
2. `-Pandroid.testInstrumentationRunnerArguments.notAnnotation=com.your.package.YourAnnotation`

That works for Shot, Dropshots and Android-Testify with Gradle Managed Devices.

For Android-Testify without Gradle Managed Devices, you need to configure it, otherwise it
uses `@ScreenshotInstrumentation`:

```groovy
def filterAnnotation = project.hasProperty("filterAnnotation") ? project.filterAnnotation : null

testify {
    screenshotAnnotation = filterAnnotation
}
```

and then run the record/verify command with
`-PfilterAnnotation=com.your.package.YourAnnotation`

> **Warning**</br>
> These arguments are supported by `org.junit.runners.Parameterized`
> and `com.google.testing.junit.testparameterinjector.TestParameterInjector`, but not by all
> runners, e.g. `JUnitParams` fails if used. I strongly recommend to
> use `com.google.testing.junit.testparameterinjector.TestParameterInjector` for filtered
> parameterized tests,
> because you can control which parameters are passed to each test method. That is not the case
> with `org.junit.runners.Parameterized`, because the parameters are injected into every single test
> method.
> Thus, you need to create different Test classes to inject different parameters.

That's why you'll find some tests in this repo with the following annotations, which are found in
the module `testannotations`:

1. `@UnhappyPath`
2. `@HappyPath`
3. `@ActivityTest`
4. `@FragmentTest`
5. `@ViewHolderTest`
6. `@ComposableTest`
   ...

In order to run filtered tests, execute the following gradle tasks, for the given annotation, for
example,`@UnhappyPath` with shot:

1. **Record
   **: `./gradlew :module_name:shot:executeScreenshotTests -Pandroid.testInstrumentationRunnerArguments.annotation=com.example.road.to.effective.snapshot.testing.testannotations.UnhappyPath -Precord`
2. **Verify
   **: `./gradlew :module_name:shot:executeScreenshotTests -Pandroid.testInstrumentationRunnerArguments.annotation=com.example.road.to.effective.snapshot.testing.testannotations.UnhappyPath`

The same goes for `@HappyPath`

### Gradle tests

The previous approach only works for instrumented tests. For non-instrumented tests, like those
running with Paparazzi or Roborazzi, the simplest approach is to use gradle test filters. We can
pass them as parameters when executing the corresponding paparazzi test tasks.
For this to work, it is important to define a clear test naming convention, for example:

1. UnhappyPath tests -> inside `class myTestClassNameUnhappyPathTest`
2. HappyPath tests -> inside `class myTestClassNameHappyPathTest`

This enables to filter the test separately by executing:

1. Only Happy path tests:
    1. Paparazzi: `./gradlew :module_name:paparazzi:recordPaparazziDebug --tests '*UnhappyPath*'`
    2. Roborazzi: `./gradlew :module_name:roborazzi:recordRoborazziDebug --tests '*UnhappyPath*'`
2. Only Unhappy path tests:
    1. Paparazzi: `./gradlew :module_name:paparazzi:recordPaparazziDebug --tests '*HappyPath*'`
    2. Roborazzi: `./gradlew :module_name:roborazzi:recordRoborazziDebug --tests '*HappyPath*'`

> **Note**</br>
> This approach does not work for instrumented tests though.

## What is coming next:

1. Comparison between libraries regarding e.g. speed, reliability, configurability, etc.
2. More Snapshot testing samples (e.g. ScrollViews, Material you + dynamic colors...)
3. Screenshot tests with other libraries: Facebook, ndtp/Testify, without library...
4. Running snapshot tests on multiple devices/JVM in parallel
6. Tips to remove flakiness
7. Tips to increase test execution speed and more...

## Code attribution

Special thanks to [Alex Zhukovich](https://github.com/AlexZhukovich) for
his [CoffeeDrinksWithJetpackCompose](https://github.com/AlexZhukovich/CoffeeDrinksWithJetpackCompose)
projects, from which
I've borrowed the Jetpack Compose examples!

## Attribution of icons in the app

Icons made by <a href="https://www.freepik.com" title="Freepik">Freepik</a>
from <a href="https://www.flaticon.com/" title="Flaticon">www.flaticon.com</a>
</br>
Icons made by <a href="https://www.flaticon.com/authors/surang" title="surang">surang</a>
from <a href="https://www.flaticon.com/" title="Flaticon">www.flaticon.com</a>

