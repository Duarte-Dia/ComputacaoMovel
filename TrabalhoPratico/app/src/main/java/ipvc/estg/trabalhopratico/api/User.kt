package ipvc.estg.trabalhopratico.api

// estas classes servem para segurar a informacao dada pelo servidor
data class User(
    val id: Int,
    val username: String,
    val password: String,
    val email: String
  //  val adress: Address
)

data class Address( val city: String,
                    val zipcode: String)
