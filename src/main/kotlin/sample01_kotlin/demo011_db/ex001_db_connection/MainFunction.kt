package sample01_kotlin.demo011_db.ex001_db_connection

import java.sql.Connection
import java.sql.DriverManager

fun main1() {
    val jdbcUrl = "jdbc:postgresql://localhost:5432/ltlk"
    val username = "123"
    val password = "456"

    Class.forName("org.postgresql.Driver") // Register the PostgreSQL driver
    val connection: Connection = DriverManager.getConnection(jdbcUrl, username, password) // Connect to the database
    connection.close()
}
