package org.acme

import jakarta.persistence.*
import jakarta.persistence.AccessType.PROPERTY
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes
import org.hibernate.validator.constraints.Length
import java.util.*

@Entity
@Table(name = "shape")
@Inheritance(strategy = InheritanceType.JOINED)
abstract class Shape(

  @Id
  @JdbcTypeCode(SqlTypes.VARCHAR)
  @Column(name = "id", updatable = false, nullable = false, unique = true)
  open val id: UUID,

  @Length(max = 100)
  @Column(name = "name", nullable = false, length = 100)
  @Access(PROPERTY)
  open var name: String,

  @Enumerated(EnumType.STRING)
  @Column(name = "color", nullable = false, length = 50)
  @Access(PROPERTY)
  open var color: Color,

  @Embedded
  @Access(PROPERTY)
  var properties: Properties?,

  ) {

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false
    return id == (other as Shape).id
  }

  override fun hashCode(): Int = id.hashCode()
}