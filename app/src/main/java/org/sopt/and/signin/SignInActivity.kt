
    private fun isValidatedSignIn(id: String, password: String): Boolean{
        return signUpState.let {
            it.value.id == id && it.value.password == password
        }
    }

