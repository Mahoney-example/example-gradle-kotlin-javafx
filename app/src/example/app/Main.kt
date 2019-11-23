package example.app

import example.core.App
import javafx.application.Application
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.scene.layout.GridPane
import javafx.stage.Stage

class Main : Application() {
  override fun start(primaryStage: Stage) {
    val pane = GridPane()
    pane.alignment = Pos.CENTER
    pane.hgap = 10.0
    pane.vgap = 10.0
    pane.padding = Insets(25.0, 25.0, 25.0, 25.0)
    pane.add(Label(App.message()), 0, 1)


    primaryStage.scene = Scene(pane, 640.0, 480.0)

    primaryStage.show()
  }

  companion object {
    @JvmStatic
    fun main(args: Array<String>) {
      launch(Main::class.java)
    }
  }
}
