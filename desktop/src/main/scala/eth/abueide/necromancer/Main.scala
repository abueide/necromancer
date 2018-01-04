package eth.abueide.necromancer

import com.badlogic.gdx.backends.lwjgl.{LwjglApplication, LwjglApplicationConfiguration}

object Main extends App {
    val cfg = new LwjglApplicationConfiguration
    cfg.title = "necromancer"
    cfg.height = 480
    cfg.width = 800
    cfg.forceExit = false
    new LwjglApplication(new Necromancer, cfg)
}
