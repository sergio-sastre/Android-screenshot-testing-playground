package snapshot.testing.recyclerview_previews.paparazzi.utils

annotation class PaparazziConfig(
    val enableAccessibility: Boolean = false,
    val renderingMode: RenderingMode = RenderingMode.SHRINK,
){
    enum class RenderingMode {
        NORMAL,
        V_SCROLL,
        H_SCROLL,
        FULL_EXPAND,
        SHRINK
    }
}
