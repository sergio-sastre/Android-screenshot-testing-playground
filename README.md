part of blog posts</br>
<a href="https://androidweekly.net/issues/issue-479">
<img src="https://androidweekly.net/issues/issue-479/badge">
</a><a href="https://androidweekly.net/issues/issue-485">
<img src="https://androidweekly.net/issues/issue-485/badge">
</a><a href="https://androidweekly.net/issues/issue-512">
<img src="https://androidweekly.net/issues/issue-512/badge">
</a>

# Road to effective snapshot testing </br>

![snapshotVsUiTests](https://user-images.githubusercontent.com/6097181/144911921-bae6182b-dae7-4f59-9dba-c88c9052b9b7.gif)

A sample repo containing code samples to introduce snapshot testing on Android for UI components and screens like the one above.

# Introduction
This repo showcases how to snapshot test Dialogs, ViewHolders, Activities, Fragments and **Jetpack Compose**!.

For the time being, all the samples use [Shot from pedrovgs](https://github.com/pedrovgs/Shot). However, the goal is to showcase how to write screenshot tests with all the most popular libraries, namely: 
1. Cashapp [Paparazzi](https://github.com/cashapp/paparazzi)
2. Facebook [screenshot-tests-for-android](https://github.com/facebook/screenshot-tests-for-android)
3. Dropbox [Dropshots](https://github.com/dropbox/dropshots)
4. Ndtp [android-testify](https://github.com/ndtp/android-testify)

Every screenshot library has its own pros and cons.
The ultimate goal is to help you choose the library that better meets your testing needs!

## Table of Contents

- [Before you start...](#before-you-start)
    - [The need for screenshot testing](#the-need-for-screenshot-testing)
    - [Emulators](#emulators)
    - [Animations](#animations)
- [Recording and verifying screenshots](#recording-and-verifying-screenshots)
- [Parameterized snapshot tests](#parameterized-snapshot-tests)
- [Filter parameterized tests](#filtered-parameterized-tests)
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

I've been using emulators running API 27-31. That is because I encountered some issues on lower & greater APIs, namely API 24, 26 & 33.
These issues are mainly related to ComposeTestRule not playing nicely with ActivityScenarios for such APIs.
Moreover, if you are using Windows, beware that Shot 5.14.1 still may have [some issues on API 29](https://github.com/pedrovgs/Shot/issues/244)

### Animations

Snapshot tests might have issues due to running animations at the moment the snapshot are taken.

In order to keep animations disabled, this project uses `testOptions { animationsDisabled = true }`
in the gradle file. Unfortunately, it does not work on all APIs (e.g. API 26 or lower). Therefore, if you
come across some issues, disable animations on the emulator *via settings* before running the
snapshot tests.

## Recording and verifying screenshots
For screenshot testing, 2 tasks are required:
1. Record. When executed, it generates a snapshot file for each test that will be taken as reference.
2. Verify. When executed, it generates a snapshot file for each test that will be compared, pixel by pixel, with the one taken as reference when recording.

Most Screenshot testing frameworks provide at least these 2 tasks.

Since we are using [Shot from pedrovgs](https://github.com/pedrovgs/Shot), you can run the following gradle tasks:
1. Record: `./gradlew executeScreenshotTests -Precord`
2. Verify: `./gradlew executeScreenshotTests`

**Warning**: All the commands in the description are for MacOS. You might need to adjust them accordingly.

## Parameterized Snapshot Tests

Most of the tests in this repo are written as parameterized. 
In doing so, we can test a given view with all possible different configurations, namely:

1. UI mode (Light and Dark)
2. Font size
3. Locale
4. Orientation 
5. View dimensions (width and/or height)
6. Specific view states
7. Others...

With Parameterized snapshot test we can write the test once and run it for all the desired
configurations! You can read more about how we can profit from it here:
- [Design a pixel perfect Android app 🎨](https://sergiosastre.hashnode.dev/design-a-pixel-perfect-android-app-with-screenshot-testing)

This can be achieved by using either `org.junit.runners.Parameterized`
or `com.google.testing.junit.testparameterinjector.TestParameterInjector`, as you'll find in most examples in this repo.

We'll use them together with [Android UI Testing Utils](https://github.com/sergio-sastre/AndroidUiTestingUtils) to set the desired configuration for the test.

**Remark**: The Parameterized runner injects the parameter passed in the Test class constructor to every
single test. If that is not wished, consider using the TestParameterInjector.
On the other hand, TestParameterInjector only works with instrumented test on API 24+. Keep that in mind.

## Filtered Parameterized Tests

Running snapshot tests for all configurations on every PR can be very time-consuming and lead to
incrementally slower builds. One approach to solve that issue is to run only a part of those tests
on every PR (e.g. the most common config, our "smoke/happy path" tests) and all snapshot tests - or "all
non-smoke tests" - once a day (e.g. during the night or whenever it disturbs your team the least).
In doing so, we get informed of the most important visual regression bugs on every PR (i.e. blocking
bugs), and still get notified of the non-blocking bugs once a day.

We can accomplish this by filtering our tests with any of these
1. `-Pandroid.testInstrumentationRunnerArguments.annotation=com.your.package.YourAnnotation`
2. `-Pandroid.testInstrumentationRunnerArguments.notAnnotation=com.your.package.YourAnnotation`

**Warning**: These arguments are supported by `org.junit.runners.Parameterized` and `com.google.testing.junit.testparameterinjector.TestParameterInjector`, but not by all
runners, e.g. `JUnitParams` fails if used. I strongly recommend to use `com.google.testing.junit.testparameterinjector.TestParameterInjector` for filtered parameterized tests,
because you can control which parameters are passed wo each test method. That is not the case with `org.junit.runners.Parameterized`, because the parameters are injected into every single test method.
Thus, you need to create different Test classes to inject different parameters.

That's why you'll find some tests in this repo with the following annotations:
1. `@UnhappyPath`
2. `@HappyPath`

In order to run filtered tests, execute the following gradle tasks, for the given annotation, for example,`@UnhappyPath`:
1. Record: `./gradlew executeScreenshotTests -Pandroid.testInstrumentationRunnerArguments.annotation=com.example.road.to.effective.snapshot.testing.utils.annotations.UnhappyPath -Precord`
2. Verify: `./gradlew executeScreenshotTests -Pandroid.testInstrumentationRunnerArguments.annotation=com.example.road.to.effective.snapshot.testing.utils.annotations.UnhappyPath`

The same goes for `@HappyPath`

## What is coming next:

1. More Snapshot testing samples (e.g. Material you)
2. Screenshot tests with other libraries: Paparazzi, Facebook, Dropshots..
3. Running snapshot tests on multiple devices/JVM in parallel
4. **framework-agnostic** & **shared screenshot testing** i.e. same test running either on device or on JVM
5. Tips to remove flakiness
6. Tips to increase test execution speed and more...

## Code attribution
Special thanks to [Alex Zhukovich](https://github.com/AlexZhukovich) for his [CoffeeDrinksWithJetpackCompose](https://github.com/AlexZhukovich/CoffeeDrinksWithJetpackCompose) projects, from which
I've borrowed the Jetpack Compose examples!

## Attribution of icons in the app

Icons made by <a href="https://www.freepik.com" title="Freepik">Freepik</a>
from <a href="https://www.flaticon.com/" title="Flaticon">www.flaticon.com</a>
</br>
Icons made by <a href="https://www.flaticon.com/authors/surang" title="surang">surang</a>
from <a href="https://www.flaticon.com/" title="Flaticon">www.flaticon.com</a>

