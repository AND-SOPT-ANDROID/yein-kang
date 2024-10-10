
    private fun handledIntent(state: MutableState<SignInState>, intent: SignInIntent) {
        when (intent) {
            is SignInIntent.EnterId -> {
                state.value = state.value.copy(id = intent.id)
            }
            is SignInIntent.EnterPassword -> {
                state.value = state.value.copy(password = intent.password)
            }
            is SignInIntent.SignIn -> {
                if (isValidatedSignIn(state.value.id, state.value.password)) {
                    state.value = state.value.copy(
                        signInSuccessful = true,
                        snackBarMessage = getString(R.string.signin_success_text)
                    )


                } else {
                    state.value = state.value.copy(
                        snackBarMessage = getString(R.string.signin_failure_text)
                    )
                }

            }
        }
    }

    private fun isValidatedSignIn(id: String, password: String): Boolean{
        return signUpState.let {
            it.value.id == id && it.value.password == password
        }
    }

