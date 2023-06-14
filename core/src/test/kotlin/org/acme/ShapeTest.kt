package org.acme

import io.quarkus.logging.Log
import io.quarkus.test.junit.QuarkusTest
import jakarta.inject.Inject
import jakarta.transaction.Transactional
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.*

@QuarkusTest
class ShapeTest {

  @Inject
  lateinit var repository: ShapeRepository

  @AfterEach
  @Transactional
  fun deleteEntities() {
    repository.deleteAll()
  }

  @Test
  @Transactional
  fun test() {

    val rectangle = Rectangle(
      id = UUID.randomUUID(),
      name = "Rectangle",
      color = Color.Red,
      properties = Properties("foo", "bar")
    )

    Log.info("rectangle: $rectangle")

    repository.persist(rectangle)

    repository.flush()

    val found = repository.findById(rectangle.id)
    Log.info("found: $found")
    assertEquals(rectangle, found)

    val all = repository.findAll().list()
    Log.info("all: $found")
    assertEquals(1, all.size)
    assertEquals(rectangle, all.first())

    assertEquals(1, repository.count())
  }

  private fun assertEquals(expected: Shape, actual: Shape) {
    assertEquals(expected.id, actual.id)
    assertEquals(expected.name, actual.name)
    assertEquals(expected.color, actual.color)
    assertEquals(expected.properties, actual.properties)
  }
}