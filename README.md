part of blog posts</br>
<a href="https://androidweekly.net/issues/issue-479">
<img src="https://androidweekly.net/issues/issue-479/badge">
</a><a href="https://androidweekly.net/issues/issue-485">
<img src="https://androidweekly.net/issues/issue-485/badge">
</a><a href="https://androidweekly.net/issues/issue-512">
<img src="https://androidweekly.net/issues/issue-512/badge">
</a>

# Android screenshot testing playground </br>

![snapshotVsUiTests](https://user-images.githubusercontent.com/6097181/144911921-bae6182b-dae7-4f59-9dba-c88c9052b9b7.gif)

A sample repo containing code samples to introduce screenshot testing for Android UI components and screens like the one above (module `:recyclerviewscreen`).
Contains "ready-to-use"

# Introduction
This repo showcases how to snapshot test Dialogs, ViewHolders, Activities, Fragments and **Jetpack Compose**!.
And even better: It is ready for you to add your own examples and try screenshot testing on your own!

In order to help find the desire examples, the app is modularized accordingly:
1. `:dialogs`: Showcases how to screenshot test dialogs created with DialogBuilder from the android View system. Examples for Compose dialogs will be added as well
2. `:recyclerviewscreen`: Contains screenshot tests for Activities, Fragments and ViewHolders
3. `:lazycolumnscreen`: Includes Jetpack Compose screenshot tests examples, as well as examples for Activities & Fragments.

Each of these modules contains submodules. Each submodule name corresponds to a screenshot testing library. You'll find screenshot test examples with that library in it. 

As of December 2022, there are many screenshot testing libraries that facilitate automated screenshot testing, namely:
1. Cashapp [Paparazzi](https://github.com/cashapp/paparazzi)
2. Dropbox [Dropshots](https://github.com/dropbox/dropshots)
3. [Shot from pedrovgs](https://github.com/pedrovgs/Shot)
4. Facebook [screenshot-tests-for-android](https://github.com/facebook/screenshot-tests-for-android)
5. Ndtp [android-testify](https://github.com/ndtp/android-testify) <sup>1<sup/>

All of them have their own pros and cons.
The ultimate goal of this repo is to help you choose the libraries that better meet your needs!

In order to do that, it contains the same/similar examples but written with different libraries<sup>2<sup/>:
1. [Paparazzi](https://github.com/cashapp/paparazzi)
2. [Dropshots](https://github.com/dropbox/dropshots)
3. [Shot](https://github.com/pedrovgs/Shot)

More screenshot test examples, as well as examples with other libraries will be continuously added.

<sup>1<sup/> Android-testify was started at Shopify and changed to Ndtp in summer 2022.

<sup>2<sup/> Paparazzi does not support screenshot tests for Activities and Fragments

## Table of Contents

- [Before you start...](#before-you-start)
    - [The need for screenshot testing](#the-need-for-screenshot-testing)
    - [Emulators](#emulators)
      - [Animations](#animations)
- [Recording and verifying screenshots](#recording-and-verifying-screenshots)
  - [Paparazzi](#paparazzihttpsgithubcomcashapppaparazzi)
  - [Dropshots](#dropshotshttpsgithubcomdropboxdropshots)
  - [Shot](#shothttpsgithubcompedrovgsshot)
- [Parameterized screenshot tests](#parameterized-screenshot-tests)
- [Filter parameterized screenshot tests](#filtered-parameterized-tests)
  - [Instrumented tests](#instrumented-tests)
  - [Gradle tests](#gradle-tests)
- [What is coming next](#what-is-coming-next)
- [Code attribution](#code-attribution)
- [Attribution of icons in the app](#attribution-of-icons-in-the-app)

## Before you start...
### The need for screenshot testing 
I strongly recommend to have a look at my blog series on [snapshot testing](https://sergiosastre.hashnode.dev/an-introduction-to-snapshot-testing-on-android-in-2021)

If reading is not your thing, you can always watch my 2021 Droidcon tech-talks on the matter:
1. [Droidcon Berlin 2021](https://www.droidcon.com/2021/11/10/an-introduction-to-effective-snapshot-testing-on-android/)
2. [Droidcon London 2021](https://www.droidcon.com/2021/11/17/an-introduction-to-effective-snapshot-testing-on-android-2/)

### Emulators

For instrumented screenshot testing (which excludes Paparazzi), I've been using emulators running API 27-31. That is because I encountered some issues on lower & greater APIs, namely API 24, 26 & 33.
These issues are mainly related to `ComposeTestRule` not playing nicely with `ActivityScenarios` for such APIs.
Moreover, if you are running screenshot tests on a Windows machine, beware that Shot 5.14.1 still may have [some issues on API 29](https://github.com/pedrovgs/Shot/issues/244)

#### Animations

Instrumented screenshot tests might have issues due to running animations at the moment the snapshot are taken.

In order to keep animations disabled, this project uses `testOptions { animationsDisabled = true }`
in the gradle file. Unfortunately, it does not work on all APIs (e.g. API 26 or lower). Therefore, if you
come across some issues, disable animations on the emulator *via settings* before running the
screenshot tests.

## Recording and verifying screenshots
For screenshot testing, 2 tasks are required:
1. **Record**. When executed, it generates a snapshot file for each test that will be taken as reference.
2. **Verify**. When executed, it generates a snapshot file for each test that will be compared, pixel by pixel, with the one taken as reference when recording.

All Screenshot testing frameworks provide at least these 2 tasks.

> **Warning**
> All the commands in the description are for MacOS. You might need to adjust them depending on the operating system of your machine.

### [Paparazzi](https://github.com/cashapp/paparazzi)
No emulators required.
Run the following gradle tasks depending on the module:
1. **Record**: `./gradlew :module_name:paparazzi:recordPaparazziDebug`. For instance:
   1. `./gradlew :dialogs::paparazzi:recordPaparazziDebug`
   2. `./gradlew :recyclerviewscreen:paparazzi:recordPaparazziDebug`
   3. `./gradlew :lazycolumnscreen:paparazzi:recordPaparazziDebug`
2. **Verify**: `./gradlew :module_name:paparazzi:verifyPaparazziDebug`. For instance:
   1. `./gradlew :dialogs:paparazzi:verifyPaparazziDebug`
   2. `./gradlew :recyclerviewscreen:paparazzi:verifyPaparazziDebug`
   3. `./gradlew :lazycolumnscreen:paparazzi:verifyPaparazziDebug`

### [Dropshots](https://github.com/dropbox/dropshots)
Start the emulators.
Then run the following gradle tasks depending on the module:
1. **Record**: `./gradlew :module_name:dropshots:connectedAndroidTest -Pdropshots.record`. For instance:
   1. `./gradlew :dialogs:dropshots:connectedAndroidTest -Pdropshots.record`
   2. `./gradlew :recyclerviewscreen:dropshots:connectedAndroidTest -Pdropshots.record`
   3. `./gradlew :lazycolumnscreen:dropshots:connectedAndroidTest -Pdropshots.record`
2. **Verify**: `./gradlew :module_name:dropshots:connectedAndroidTest`. For instance:
   1. `./gradlew :dialogs:dropshots:connectedAndroidTest`
   2. `./gradlew :recyclerviewscreen:dropshots:connectedAndroidTest`
   3. `./gradlew :lazycolumnscreen:dropshots:connectedAndroidTest`

### [Shot](https://github.com/pedrovgs/Shot)
Start the emulators. 
Then run the following gradle tasks depending on the module:
1. **Record**: `./gradlew :module_name:shot:executeScreenshotTests -Precord`. For instance:
   1. `./gradlew :dialogs:shot:executeScreenshotTests -Precord`
   2. `./gradlew :recyclerviewscreen:shot:executeScreenshotTests -Precord`
   3. `./gradlew :lazycolumnscreen:shot:executeScreenshotTests -Precord`
2. **Verify**: `./gradlew :module_name:shot:executeScreenshotTests`. For instance:
   1. `./gradlew :dialogs:shot:executeScreenshotTests`
   2. `./gradlew :recyclerviewscreen:shot:executeScreenshotTests`
   3. `./gradlew :lazycolumnscreen:shot:executeScreenshotTests`

> **NOTE**
> The library says the record reports can be reviewed at `RoadToEffectiveSnapshotTesting/dialogs/shot/build/reports/shot/debug/index.html`
> However, it is wrong. The record reports can be reviewed at `RoadToEffectiveSnapshotTesting/dialogs/shot/build/reports/shot/debug/record/index.html`
> The path for the verification reports is right though.

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

This can be achieved by using either `org.junit.runners.Parameterized`
or `com.google.testing.junit.testparameterinjector.TestParameterInjector`, as you'll find in most examples in this repo.

Neither Dropbox nor Shot offer the possibility to set such configurations (Paparazzi does though). However, we can set the desired configurations for our Dropbox/Shot instrumented screenshot tests with [Android UI Testing Utils](https://github.com/sergio-sastre/AndroidUiTestingUtils), as seen in the examples.

> **Remark**
> The Parameterized runner injects the parameter passed in the Test class constructor to every
single test. If that is not wished, consider using the TestParameterInjector.
On the other hand, TestParameterInjector only works with instrumented test on API 24+. Keep that in mind.

## Filtered parameterized screenshot tests

Running snapshot tests for all configurations on every PR can be very time-consuming and lead to
incrementally slower builds. One approach to solve that issue is to run only a part of those tests
on every PR (e.g. the most common config, our "smoke/happy path" tests) and all snapshot tests - or "all
non-smoke tests" - once a day (e.g. during the night or whenever it disturbs your team the least).
In doing so, we get informed of the most important visual regression bugs on every PR (i.e. blocking
bugs), and still get notified of the non-blocking bugs once a day.

We can accomplish this by filtering our tests accordingly.

### Instrumented tests
This applies for tests running on emulators/physical devices i.e. those using Dropshots or Shot.

In order to filter tests, we need to provide the corresponding test instrumentation arguments. A straightforward means to do that is to use custom annotations. For instance:
1. `-Pandroid.testInstrumentationRunnerArguments.annotation=com.your.package.YourAnnotation`
2. `-Pandroid.testInstrumentationRunnerArguments.notAnnotation=com.your.package.YourAnnotation`

> **Warning**
> These arguments are supported by `org.junit.runners.Parameterized` and `com.google.testing.junit.testparameterinjector.TestParameterInjector`, but not by all
> runners, e.g. `JUnitParams` fails if used. I strongly recommend to use `com.google.testing.junit.testparameterinjector.TestParameterInjector` for filtered parameterized tests,
> because you can control which parameters are passed wo each test method. That is not the case with `org.junit.runners.Parameterized`, because the parameters are injected into every single test method.
> Thus, you need to create different Test classes to inject different parameters.

That's why you'll find some tests in this repo with the following annotations, which are found in the module `testannotations`:
1. `@UnhappyPath`
2. `@HappyPath`

In order to run filtered tests, execute the following gradle tasks, for the given annotation, for example,`@UnhappyPath` with shot:
1. **Record**: `./gradlew :module_name:shot:executeScreenshotTests -Pandroid.testInstrumentationRunnerArguments.annotation=com.example.road.to.effective.snapshot.testing.utils.annotations.UnhappyPath -Precord`
2. **Verify**: `./gradlew :module_name:shot:executeScreenshotTests -Pandroid.testInstrumentationRunnerArguments.annotation=com.example.road.to.effective.snapshot.testing.utils.annotations.UnhappyPath`

The same goes for `@HappyPath`

### Gradle tests
The previous approach only works for instrumented tests. For non-instrumented tests, like those running with paparazzi, the simplest approach is to use gradle test filters. We can pass them as parameters when executing the corresponding paparazzi test tasks.
For this to work, it is important to define a clear test naming convention, for example:
1. UnhappyPath tests -> inside `class myTestClassNameUnhappyPathTest`
2. HappyPath tests -> inside `class myTestClassNameHappyPathTest`

This enables to filter the test separately by executing:
1. Only Happy path tests:`./gradlew :module_name:paparazzi:recordPaparazziDebug --tests '*UnhappyPath*'`
2. Only Unhappy path tests:`./gradlew :module_name:paparazzi:recordPaparazziDebug --tests '*HappyPath*'`

> **Note**
> This approach does not work for instrumented tests though.

## What is coming next:

1. Comparison between libraries regarding e.g. speed, reliability, configurability, etc.
2. More Snapshot testing samples (e.g. ScrollViews, Material you + dynamic colors...)
3. Screenshot tests with other libraries: Facebook, ndtp/Testify, without library...
4. Running snapshot tests on multiple devices/JVM in parallel
5. **framework-agnostic** & **shared screenshot testing** i.e. same test running either on device or on JVM
6. Tips to remove flakiness
7. Tips to increase test execution speed and more...

## Code attribution
Special thanks to [Alex Zhukovich](https://github.com/AlexZhukovich) for his [CoffeeDrinksWithJetpackCompose](https://github.com/AlexZhukovich/CoffeeDrinksWithJetpackCompose) projects, from which
I've borrowed the Jetpack Compose examples!

## Attribution of icons in the app

Icons made by <a href="https://www.freepik.com" title="Freepik">Freepik</a>
from <a href="https://www.flaticon.com/" title="Flaticon">www.flaticon.com</a>
</br>
Icons made by <a href="https://www.flaticon.com/authors/surang" title="surang">surang</a>
from <a href="https://www.flaticon.com/" title="Flaticon">www.flaticon.com</a>

