    private fun isValidatedId(id: String): Boolean{
        val emailPattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"
        return id.matches(emailPattern.toRegex())
    }

    private fun isValidatedPassword(password: String): Boolean{
        val passwordPattern = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,20}$"
        return password.matches(passwordPattern.toRegex())
    }
