package eth.abueide.necromancer.ecs.components

import com.badlogic.ashley.core.Component
import eth.abueide.math2d.Vector2

/**
  * Created by andrew on 12/17/17.
  */
class Movable extends Component{
  var velocity = new Vector2(0,0)
  var acceleration = new Vector2(0,0);
}
