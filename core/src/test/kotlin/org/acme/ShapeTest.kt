package org.acme

import io.quarkus.logging.Log
import io.quarkus.test.junit.QuarkusTest
import javax.inject.Inject
import javax.transaction.Transactional
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

    val found = repository.findById(rectangle.id)
    Log.info("found: $found")

    assertEquals(rectangle, found)
    assertEquals(1, repository.count())
  }
}