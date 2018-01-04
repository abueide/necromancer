package eth.abueide.necromancer.ecs.components

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite

/**
  * Created by andrew on 12/17/17.
  */
class Drawable(var sprite: Sprite, var invisible: Boolean) extends Component{
  def this(sprite: Sprite) = this(sprite, false);
}

