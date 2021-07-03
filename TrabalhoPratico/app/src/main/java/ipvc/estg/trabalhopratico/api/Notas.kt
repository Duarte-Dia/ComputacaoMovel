package ipvc.estg.trabalhopratico.api

data class Notas(
    val id: Int,
    val latitude: Float,
    val longitude: Float,
    val title: String,
    val description: String,
    val utilizador_id: Int
)