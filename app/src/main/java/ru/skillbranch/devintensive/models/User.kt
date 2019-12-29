package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.utils.Utils
import java.util.*

data class User(
    var id: String,
    var firstName: String?,
    var lastName: String?,
    var avatar: String?,
    var rating: Int = 0,
    var respect: Int = 0,
    var lastVisit: Date? = null,
    var isOnline: Boolean = false
) {

    constructor(id: String, firstName: String?, lastName: String?) : this(
        id = id,
        firstName = firstName,
        lastName = lastName,
        avatar = null
    )

    constructor(id: String) : this(id, "John", "Doe")

    init {

        println(
            "It`s Alive!!!\n" +
                    "${if (lastName === "Doe") "His name id $firstName $lastName" else "And his name is $firstName $lastName!!!"}\n"
        )
    }

    fun printMe() = println(
        """
        id: $id
        firstName: $firstName
        lastName: $lastName
        avatar: $avatar
        rating: $rating
        respect: $respect
        lastVisit: $lastVisit
        isOnline: $isOnline
    """.trimIndent()
    )

    class Builder {

        val user: User = Factory.makeUser(null)

        var userId: String? = null
        var userFirstName: String? = null
        var userLastName: String? = null
        var userAvatar: String? = null
        var userRating: Int = 0
        var userRespect: Int = 0
        var userLastVisit: Date? = null
        var userIsOnline: Boolean = false

        fun id(id: String): Builder {
            user.id = id
            return this
        }

        fun firstName(firstName: String?): Builder {
            user.firstName = firstName
            return this
        }

        fun lastName(lastName: String?): Builder {
            user.lastName = lastName
            return this
        }

        fun avatar(avatar: String?): Builder {
            user.avatar = avatar
            return this
        }

        fun rating(rating: Int): Builder {
            user.rating = rating
            return this
        }

        fun respect(respect: Int): Builder {
            user.respect = respect
            return this
        }

        fun lastVisit(lastVisit: Date?): Builder {
            user.lastVisit = lastVisit
            return this
        }

        fun isOnline(isOnline: Boolean): Builder {
            user.isOnline = isOnline
            return this
        }

        fun build(): User {
            return user
        }

    }

    companion object Factory {
        private var lastId: Int = -1
        fun makeUser(fullName: String?): User {
            lastId++

            val (firstName, lastName) = Utils.parseFullName(fullName)

            if (firstName.isNullOrBlank() && lastName.isNullOrBlank())
                return User(id = "$lastId")
            return User(id = "$lastId", firstName = firstName, lastName = lastName)
        }
    }
}