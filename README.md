## LiquidScreens, A maintained fork of Compose Navigation Reimagined


### This is a maintained fork of [Compose Navigation Reimagined](https://github.com/olshevski/compose-navigation-reimagined), only material3 is kept for low maintainence overhead. Deprecated apis are replaced in favor of newer apis and updated the packages to match with current jetpack compose library suite.

Full Documentation: https://easyusesoft.github.io/LiquidScreens/

## Demo

https://github.com/user-attachments/assets/7aa2b9af-6ba6-4086-aff6-147bf40b2b0d

A small and simple, yet fully fledged and customizable navigation library for [Jetpack Compose](https://developer.android.com/jetpack/compose):

- Full **type-safety**
- Built-in state restoration
- Nested navigation with independent backstacks
- Own Lifecycle, ViewModelStore and SavedStateRegistry for every backstack entry
- Animated transitions
- Dialog and bottom sheet navigation
- Scopes for easier ViewModel sharing 
- No builders, no obligatory superclasses for your composables

## Quick start

### Installation via JitPack

**Step 1.** Add the JitPack repository to your build file

Add it in your root `settings.gradle` at the end of repositories:

```kotlin
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```

**Step 2.** Add the dependency

```kotlin
dependencies {
    implementation 'com.github.easyusesoft.LiquidScreens:reimagined:0.1.2'
}
```

Define a set of destinations. It is convenient to use a sealed class for this:

```kotlin
sealed class Destination : Parcelable {

    @Parcelize
    data object First : Destination()

    @Parcelize
    data class Second(val id: Int) : Destination()

    @Parcelize
    data class Third(val text: String) : Destination()

}
```

Create a composable with `NavController`, `NavBackHandler` and `NavHost`:

```kotlin
@Composable
fun NavHostScreen() {
    val navController = rememberNavController<Destination>(
        startDestination = Destination.First
    )

    NavBackHandler(navController)

    NavHost(navController) { destination ->
        when (destination) {
            is Destination.First -> Column {
                Text("First destination")
                Button(onClick = {
                    navController.navigate(Destination.Second(id = 42))
                }) {
                    Text("Open Second destination")
                }
            }

            is Destination.Second -> Column {
                Text("Second destination: ${destination.id}")
                Button(onClick = {
                    navController.navigate(Destination.Third(text = "Hello"))
                }) {
                    Text("Open Third destination")
                }
            }

            is Destination.Third -> {
                Text("Third destination: ${destination.text}")
            }
        }
    }
}
```

As you can see, `NavController` is used for switching between destinations, `NavBackHandler` handles back presses and `NavHost` provides a composable corresponding to the last destination in the backstack. As simple as that.

### What about animations?

Just replace `NavHost` with `AnimatedNavHost`. The default transition between destinations is a simple crossfade, but you can customize each transition with the `transitionSpec` parameter:

```kotlin
AnimatedNavHost(
    controller = navController,
    transitionSpec = { action, _, _ ->
        val direction = if (action == NavAction.Pop) {
            AnimatedContentTransitionScope.SlideDirection.End
        } else {
            AnimatedContentTransitionScope.SlideDirection.Start
        }
        slideIntoContainer(direction) togetherWith slideOutOfContainer(direction)
    }
) { destination ->
    // ...
}
```

## Documentation

Full documentation is available [here](https://easyusesoft.github.io/LiquidScreens).

## Additional dependencies

Library-specific `hiltViewModel()` implementation:

```kotlin
implementation 'com.github.easyusesoft.LiquidScreens:reimagined-hilt:0.1.2'
```

`BottomSheetNavHost` implementation for Material 3:

```kotlin
implementation 'com.github.easyusesoft.LiquidScreens:reimagined-material3:0.1.2'
```

## Sample

Explore the [sample](https://github.com/easyusesoft/LiquidScreens/tree/main/sample). It demonstrates:

- passing values and returning results
- animated transitions
- dialog and bottom sheet navigation
- nested navigation
- [BottomNavigation](https://developer.android.com/reference/kotlin/androidx/compose/material/package-summary#BottomNavigation(androidx.compose.ui.Modifier,androidx.compose.ui.graphics.Color,androidx.compose.ui.graphics.Color,androidx.compose.ui.unit.Dp,kotlin.Function1)) integration
- entry-scoped and shared ViewModels
- hoisting of NavController to the ViewModel layer
- deeplinks
