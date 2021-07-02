package ipvc.estg.trabalhopratico.api

data class OutputLogin(
    val username: String,
    val password: String,
    val email: String,
    val status: String,
    val MSG: String,
    val id: Int
)