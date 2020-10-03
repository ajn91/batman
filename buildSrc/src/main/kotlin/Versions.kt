import org.gradle.plugin.use.PluginDependenciesSpec
import org.gradle.plugin.use.PluginDependencySpec

/**
 * Generated by https://github.com/jmfayard/buildSrcVersions
 *
 * Find which updates are available by running
 *     `$ ./gradlew buildSrcVersions`
 * This will only update the comments.
 *
 * YOU are responsible for updating manually the dependency version.
 */
object Versions {
    const val io_gitlab_arturbosch_detekt: String = "1.10.0" // available: "1.12.0"

    const val com_squareup_retrofit2: String = "2.9.0"

    const val org_jetbrains_kotlin: String =
        "1.3.72" // use 1.4.20-dev when available https://github.com/airbnb/MvRx/pull/438

    const val com_squareup_inject: String = "0.5.2"

    const val com_google_dagger: String = "2.28.3-alpha"

    const val com_android_tools_build_gradle: String = "4.0.1"

    const val com_google_android_play_core: String = "1.8.0"

    const val de_fayard_buildsrcversions_gradle_plugin: String = "0.7.0"

    const val lifecycle_viewmodel_ktx: String = "2.2.0"

    const val logging_interceptor: String = "4.8.1"

    const val leakcanary_android: String = "2.4"

    const val swiperefreshlayout: String = "1.1.0"

    const val constraintlayout: String = "2.0.1"

    const val epoxy_processor: String = "4.0.0-beta6"

    const val core_testing: String = "2.1.0"

    const val mvrx_testing: String = "2.0.0-alpha2"

    const val recyclerview: String = "1.2.0-alpha03"

    const val lint_gradle: String = "27.0.1"

    const val appcompat: String = "1.3.0-alpha01"

    const val core_ktx: String = "1.5.0-alpha01"

    const val material: String = "1.3.0-alpha02"

    const val rxpaper2: String = "1.5.0"

    const val lottie: String = "3.4.2"

    const val rxjava: String = "2.2.19"

    const val timber: String = "4.7.1"

    const val aapt2: String = "4.0.1-6197926"

    const val epoxy: String = "4.0.0-beta6"

    const val glide: String = "4.11.0"

    const val junit: String = "4.13"

    const val mockk: String = "1.10.0"

    const val mvrx: String = "2.0.0-alpha2"

    /**
     * Current version: "6.6.1"
     * See issue 19: How to update Gradle itself?
     * https://github.com/jmfayard/buildSrcVersions/issues/19
     */
    const val gradleLatestVersion: String = "6.6.1"
}

/**
 * See issue #47: how to update buildSrcVersions itself
 * https://github.com/jmfayard/buildSrcVersions/issues/47
 */
val PluginDependenciesSpec.buildSrcVersions: PluginDependencySpec
    inline get() =
        id("de.fayard.buildSrcVersions").version(Versions.de_fayard_buildsrcversions_gradle_plugin)
