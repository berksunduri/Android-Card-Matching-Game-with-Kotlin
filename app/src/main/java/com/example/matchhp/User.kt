package com.example.matchhp


class User {
    private lateinit var username: String
    private lateinit var password: String

    constructor() {}
    constructor(username: String, password: String) {
        this.username = username
        this.password = password
    }

    fun getUsername(): String {
        return username
    }

    fun getPassword(): String {
        return password
    }

    fun setUsername(username: String) {
        this.username = username
    }

    fun setPassword(password: String) {
        this.password = password
    }


}

