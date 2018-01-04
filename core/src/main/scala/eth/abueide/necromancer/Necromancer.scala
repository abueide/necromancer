package eth.abueide.necromancer

import com.badlogic.ashley.core.{Engine, Entity, Family}
import com.badlogic.gdx.graphics.g2d.{Sprite, SpriteBatch}
import com.badlogic.gdx.graphics.{GL20, OrthographicCamera, Texture}
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.{ApplicationAdapter, Gdx, Input, InputProcessor}
import eth.abueide.math2d.Vector2
import eth.abueide.necromancer.ecs.components.{Drawable, Mappers, Movable, Player}
import eth.abueide.necromancer.ecs.systems.MovementSystem

import scala.collection.JavaConverters._

class Necromancer extends ApplicationAdapter() with InputProcessor {
  //TiledMapRenderer
  //GameState
  var cam: OrthographicCamera = null
  var batch: SpriteBatch = null
  var engine: Engine = null
  var wiz: Entity = null

  override def create(): Unit = {
    batch = new SpriteBatch()
    engine = new Engine()

    for (i <- 0 to 360 by 45) {
      val skeletx = new Texture("skeleton-front.png")
      var skelesp = new Sprite(skeletx)
      skelesp.setSize(8, 8)
      val entity = new Entity
      entity.add(new Player(new Vector2(0, 0), i, true))
      entity.add(new Movable)
      entity.add(new Drawable(skelesp));
      engine.addEntity(entity)
    }
    val wiztx = new Texture("wizard-front.png")
    var wizsp = new Sprite(wiztx)
    wizsp.setSize(8, 8)
    wiz = new Entity
    wiz.add(new Player(new Vector2(-4, -4), 0, false))
    wiz.add(new Movable)
    wiz.add(new Drawable(wizsp));
    Gdx.input.setInputProcessor(this)
    engine.addEntity(wiz)
    engine.addSystem(new MovementSystem)

    val w = Gdx.graphics.getWidth()
    val h = Gdx.graphics.getHeight()
    cam = new OrthographicCamera(50, 50 * (h / w))
    cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0)
    cam.update()

  }

  override def render(): Unit = {
    cam.update()
    batch.setProjectionMatrix(cam.combined)

    engine.update(Gdx.graphics.getDeltaTime)
    Gdx.gl.glClearColor(0, 1, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    batch.begin()
    for (entity: Entity <- engine.getEntities().asScala) {
      val player = Mappers.player.get(entity)
      val drawable = Mappers.drawable.get(entity)
      drawable.sprite.setPosition(player.pos.x, player.pos.y)
      drawable.sprite.draw(batch)
    }
    batch.end()

  }

  override def dispose(): Unit = {
    for (entity: Entity <- engine.getEntitiesFor(Family.all(classOf[Drawable]).get()).asScala) {
      Mappers.drawable.get(entity).sprite.getTexture.dispose()
    }
    batch.dispose()
  }

  override def resize(width: Int, height: Int): Unit = {
    val aspectRatio = width.asInstanceOf[Float] / height.asInstanceOf[Float]
    cam = new OrthographicCamera(50f * aspectRatio, 50f)
  }

  override def touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean = false

  override def touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean = {
    val sprite = Mappers.drawable.get(wiz).sprite.getTexture;
    val pos = cam.unproject(new Vector3(screenX, screenY, 0))
    val player = Mappers.player.get(wiz).pos = new Vector2(pos.x - 4, pos.y -4)
    true
  }

  override def keyUp(keycode: Int): Boolean = {
    val movable = Mappers.movable.get(wiz)
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
    val speed = 10;
    val movable = Mappers.movable.get(wiz)
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