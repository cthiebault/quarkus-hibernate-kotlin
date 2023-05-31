package org.acme

import org.hibernate.annotations.Type
import org.hibernate.validator.constraints.Length
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "shape")
@Inheritance(strategy = InheritanceType.JOINED)
abstract class Shape(

  @Id
  @Type(type = "uuid-char")
  @Column(name = "id", updatable = false, nullable = false, unique = true)
  open val id: UUID,

  @Length(max = 100)
  @Column(name = "name", nullable = false, length = 100)
  open var name: String,

  @Enumerated(EnumType.STRING)
  @Column(name = "color", nullable = false, length = 50)
  open var color: Color,

  @Embedded
  var properties: Properties?,

  ) {

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false
    return id == (other as Shape).id
  }

  override fun hashCode(): Int = id.hashCode()
}