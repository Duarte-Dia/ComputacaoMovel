package ipvc.estg.trabalhopratico.api

data class Notas(
    val id: Int,
    val lat: Float,
    val long: Float,
    val title: String,
    val description: String,
    val utilizador_id: Int
)