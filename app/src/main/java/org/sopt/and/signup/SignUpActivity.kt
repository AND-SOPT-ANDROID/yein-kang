    private fun handledIntent(state: MutableState<SignUpState>, signUpIntent: SignUpIntent) {
        when (signUpIntent) {
            is SignUpIntent.EnterId -> {
                state.value = state.value.copy(id = signUpIntent.id)
            }
            is SignUpIntent.EnterPassword -> {
                state.value = state.value.copy(password = signUpIntent.password)
            }
            is SignUpIntent.SignUp -> {
                val currentState = state.value
                val isIdValid = isValidatedId(currentState.id)
                val isPasswordValid = isValidatedPassword(currentState.password)

                when {
                    !isIdValid -> {
                        state.value = currentState.copy(
                            snackBarMessage = getString(R.string.signup_failure_email_text)
                        )
                    }
                    !isPasswordValid -> {
                        state.value = currentState.copy(
                            snackBarMessage = getString(R.string.signup_failure_password_text)
                        )
                    }
                    else -> {
                        state.value = currentState.copy(
                            signUpSuccessful = true,
                        )
                        intent.putExtra("id", currentState.id)
                        intent.putExtra("password", currentState.password)
                        intent.putExtra("snackBarMessage", getString(R.string.signup_success_text))
                        setResult(Activity.RESULT_OK, intent)
                        finish()
                    }
                }
            }
        }
    }
    private fun isValidatedId(id: String): Boolean{
        val emailPattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"
        return id.matches(emailPattern.toRegex())
    }

    private fun isValidatedPassword(password: String): Boolean{
        val passwordPattern = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,20}$"
        return password.matches(passwordPattern.toRegex())
    }
