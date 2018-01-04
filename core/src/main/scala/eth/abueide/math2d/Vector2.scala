package eth.abueide.math2d

/**
  * Created by Andrew Bueide on 12/17/17.
  */
object Vector2{
    def calculateOrbit(currentOrbitDegrees: Float, distanceFromCenterPoint: Float, centerPoint: Vector2 ): Vector2 = {
    val radians = Math.toRadians(currentOrbitDegrees)
    val x = (Math.cos(radians) * distanceFromCenterPoint).toFloat + centerPoint.x
    val y = (Math.sin(radians) * distanceFromCenterPoint).toFloat + centerPoint.y
    new Vector2(x, y)
  }
}

class Vector2(val x: Float, val y: Float) {

  def +(that: Vector2): Vector2 = new Vector2(this.x + that.x, this.y + that.y)
  def -(that: Vector2): Vector2 = new Vector2(this.x - that.x, this.y - that.y)
  def *(that: Vector2): Vector2 = new Vector2(this.x * that.x, this.y * that.y)
  def /(that: Vector2): Vector2 = new Vector2(this.x / that.x, this.y / that.y)

  def +(that: Float): Vector2 = this + new Vector2(that, that);
  def -(that: Float): Vector2 = this - new Vector2(that, that);
  def *(that: Float): Vector2 = this * new Vector2(that, that);
  def /(that: Float): Vector2 = this / new Vector2(that, that);

  override def toString = s"Vector2($x, $y)"
}
