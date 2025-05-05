package ru.netology

data class Comments(
    val count: Int = 0,
    val canPost: Boolean = true,
    val groupsCanPost: Boolean = false
)

data class Likes(
    val count: Int = 0,
    val userLikes: Boolean = false,
    val canLike: Boolean = true
)

data class Post(
    val id: Int = 0,

    val date: Int = 0,

    val text: String = "",

    val friendsOnly: Boolean = false,

    val canPin: Boolean = false,

    val canDelete: Boolean = false,

    val canEdit: Boolean = false,

    val isPinned: Boolean = false,

    val markedAsAds: Boolean = false,

    val isFavorite: Boolean = false,

    val comments: Comments = Comments(),

    val likes: Likes = Likes()
)

object WallService {
    private var posts = mutableListOf<Post>()
    private var nextId = 1

    fun add(post: Post): Post {
        val postWithId = post.copy(id = nextId++)
        posts += postWithId
        return postWithId
    }

    fun update(post: Post): Boolean {
        for ((index, current) in posts.withIndex()) {
            if (current.id == post.id) {
                posts[index] = post.copy(
                    date = current.date,
                    comments = current.comments,
                    likes = current.likes
                )
                return true
            }
        }
        return false
    }

    fun getAll(): List<Post> = posts.toList()
}

fun main() {
    val postA = WallService.add(
        Post(date = 1680000020, text = "Hello, VK!")
    )
    println("Добавлен пост A: $postA")

    val postB = WallService.add(
        Post(date = 1680000030, text = "Another post.")
    )
    println("Добавлен пост B: $postB")

    println("\nСписок всех постов:")
    WallService.getAll().forEach { println(it) }

    val updated = WallService.update(
        postA.copy(text = "Hello, VK! (edited)")
    )
    println("\nРезультат обновления поста A: $updated")

    println("\nСписок постов после обновления:")
    WallService.getAll().forEach { println(it) }
}
