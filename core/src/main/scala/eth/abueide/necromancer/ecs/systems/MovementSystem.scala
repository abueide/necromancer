package eth.abueide.necromancer.ecs.systems

import com.badlogic.ashley.core.{Engine, Entity, EntitySystem, Family}
import com.badlogic.ashley.utils.ImmutableArray
import com.badlogic.gdx.{Gdx, Input}
import eth.abueide.math2d.Vector2
import eth.abueide.necromancer.Necromancer
import eth.abueide.necromancer.ecs.components.{Mappers, Movable, Player}

import scala.collection.JavaConverters._

/**
  * Created by andrew on 12/17/17.
  */
class MovementSystem extends EntitySystem {
  private var entities: ImmutableArray[Entity] = null
  private var passed: Float = 0
  private var centerPoint = new Vector2(0, 0)


  override def addedToEngine(engine: Engine): Unit = {
    entities = engine.getEntitiesFor(Family.all(classOf[Movable], classOf[Player]).get())
  }

  override def update(deltaTime: Float): Unit = {
    passed = passed + deltaTime * 40
    for (entity: Entity <- entities.asScala) {
      val player = Mappers.player.get(entity)
      val movable = Mappers.movable.get(entity)
      movable.velocity = movable.velocity + (movable.acceleration * deltaTime)
      player.pos = player.pos + (movable.velocity * deltaTime)
      if (!player.spin) {
        centerPoint = player.pos
      } else {
        player.updatePos(
          if (passed < 360f) passed else passed % 360f,
          math.sin(passed / 50).toFloat * 5 + 15,
          centerPoint
        )
      }
    }
  }

}
