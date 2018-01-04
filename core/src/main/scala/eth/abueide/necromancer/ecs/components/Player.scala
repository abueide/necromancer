package eth.abueide.necromancer.ecs.components

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.math.MathUtils
import eth.abueide.math2d.Vector2

/**
  * Created by andrew on 12/17/17.
  */
class Player(var pos: Vector2, var offset: Float, var spin: Boolean) extends Component {


  def updatePos(currentOrbitDegrees: Float, distanceFromCenterPoint: Float, centerPoint: Vector2): Unit = {
    val radians = Math.toRadians(currentOrbitDegrees + offset)
    val x = (Math.cos(radians) * distanceFromCenterPoint).toFloat + centerPoint.x
    val y = (Math.sin(radians) * distanceFromCenterPoint).toFloat + centerPoint.y
    pos = new Vector2(x,y)
  }


}


