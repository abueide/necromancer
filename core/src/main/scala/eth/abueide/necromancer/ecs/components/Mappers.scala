package eth.abueide.necromancer.ecs.components

import com.badlogic.ashley.core.ComponentMapper

/**
  * Created by andrew on 12/17/17.
  */
object Mappers {
  var player = ComponentMapper.getFor(classOf[Player])
  var drawable = ComponentMapper.getFor(classOf[Drawable])
  var movable = ComponentMapper.getFor(classOf[Movable])
}

