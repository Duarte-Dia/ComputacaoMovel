package ipvc.estg.trabalhopratico.api

data class Notas(
    val id: Int,
    val lat: Float,
    val long: Float,
    val tipo: String,
    val descricao: String,
    val utilizador_id: Int
)