package br.vedovato.model

import enumeratum.{ CirceEnum, EnumEntry }

import scala.collection.immutable

sealed trait Direction extends EnumEntry

object Directions extends enumeratum.Enum[Direction] with CirceEnum[Direction] {
  case object North extends Direction
  case object South extends Direction
  case object East extends Direction
  case object West extends Direction

  def values: immutable.IndexedSeq[Direction] = findValues
}
