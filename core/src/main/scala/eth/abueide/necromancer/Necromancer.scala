package eth.abueide.necromancer

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.{GL20, Texture}
import com.badlogic.gdx.{Game, Gdx}

class Necromancer extends Game {

    lazy val sprite = new Texture("libgdxlogo.png")
    lazy val batch = new SpriteBatch

    override def create(): Unit = {}

    override def render(): Unit = {
        Gdx.gl.glClearColor(0,0,1,0)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        batch.begin()
        batch.draw(sprite, (Gdx.graphics.getWidth - sprite.getWidth) / 2f, (Gdx.graphics.getHeight - sprite.getHeight) / 2f)
        batch.end()
    }
}
