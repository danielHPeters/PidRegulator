package ch.peters.daniel.pidcontroller.model

import spock.lang.Specification

class PointTest extends Specification {
  def 'constructor should set x and y coordinates'() {
    given:
    def x = 23
    def y = 103

    when:
    def point = new Point<Integer>(x, y)

    then:
    point.x == x
    point.y == y
  }

  def 'x and y values are changeable' () {
    given:
    def x = 23
    def y = 103
    def point = new Point<Integer>(x, y)

    when:
    point.set(10, 12)
    then:
    point.x == 10
    point.y == 12
  }
}
