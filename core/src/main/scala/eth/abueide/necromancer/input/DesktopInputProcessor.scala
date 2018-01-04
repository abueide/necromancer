package eth.abueide.necromancer.input


import javafx.scene.input.KeyCode

import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.{Gdx, Input, InputProcessor}
import eth.abueide.math2d.Vector2
import eth.abueide.necromancer.ecs.components.Mappers

/**
  * Created by Andrew Bueide on 12/13/17.
  */
class DesktopInputProcessor(val entity: Entity) extends InputProcessor{

  override def touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean = false

  override def touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean = {
    Mappers.player.get(entity).pos = new Vector2(screenX, Gdx.graphics.getHeight - screenY)
    true
  }

  override def keyUp(keycode: Int): Boolean = {
    val movable = Mappers.movable.get(entity)
    keycode match {
      case Input.Keys.W => movable.velocity = new Vector2(0, 0)
      case Input.Keys.S => movable.velocity = new Vector2(0, 0)
      case Input.Keys.D => movable.velocity = new Vector2(0, 0)
      case Input.Keys.A => movable.velocity = new Vector2(0, 0)
      case _ => Unit
    }
    true
  }

  override def scrolled(amount: Int): Boolean = false

  override def keyTyped(character: Char): Boolean = false

  override def touchDragged(screenX: Int, screenY: Int, pointer: Int): Boolean = false

  override def keyDown(keycode: Int): Boolean = {
    val speed = 100;
    val movable = Mappers.movable.get(entity)
    keycode match {
      case Input.Keys.W => movable.velocity = new Vector2(0, speed)
      case Input.Keys.S => movable.velocity = new Vector2(0, -speed)
      case Input.Keys.D => movable.velocity = new Vector2(speed, 0)
      case Input.Keys.A => movable.velocity = new Vector2(-speed, 0)
      case _ => Unit
    }
    true
  }
  override def mouseMoved(screenX: Int, screenY: Int): Boolean = false
}
