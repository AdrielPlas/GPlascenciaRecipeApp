import kotlinx.serialization.Serializable


@Serializable
object LoginScreenRoute

@Serializable
object RegisterScreenRoute

@Serializable
data class MainScreenGraph(val userId: Int)

@Serializable
object HomeScreenRoute

@Serializable
object MainScreenRoute