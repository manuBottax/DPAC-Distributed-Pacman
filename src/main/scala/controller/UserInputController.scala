package controller

import java.awt.event.{KeyEvent, KeyListener}

import view.PlaygroundPanel

/**
  * Created by Manuel Bottax on 06/07/2017.
  */
class UserInputController (val view: PlaygroundPanel)/*(val character: Character) */ extends KeyListener{

  var currentX : Int = 45
  var currentY: Int = 17

  override def keyPressed(e: KeyEvent): Unit = {
    //todo: controllare bordi
    e.getKeyCode match {
      case KeyEvent.VK_LEFT => {
        if (check(currentX - 1, currentY)) {
          view.removeCharacter(currentX, currentY)
          currentX = currentX - 1
          view.renderCharacter(currentX, currentY, "pacman", "left")
        }
      }


      case KeyEvent.VK_RIGHT => {
        if (check(currentX +1, currentY)) {
          view.removeCharacter(currentX, currentY)
          currentX = currentX + 1
          view.renderCharacter(currentX, currentY, "pacman", "right")
        }
      }

      case KeyEvent.VK_UP => {
        if (check(currentX , currentY-1)) {
          view.removeCharacter(currentX, currentY)
          currentY = currentY - 1
          view.renderCharacter(currentX, currentY, "pacman", "up")
        }
      }

      case KeyEvent.VK_DOWN => {
        if (check(currentX , currentY + 1)) {
          view.removeCharacter(currentX, currentY)
          currentY = currentY + 1
          view.renderCharacter(currentX, currentY, "pacman", "down")
        }
      }

      case KeyEvent.VK_A => {
        if (check(currentX - 1, currentY)) {
          view.removeCharacter(currentX, currentY)
          currentX = currentX - 1
          view.renderCharacter(currentX, currentY, "pacman", "left")
        }
      }

      case KeyEvent.VK_D => {
        if (check(currentX + 1, currentY)) {
          view.removeCharacter(currentX, currentY)
          currentX = currentX + 1
          view.renderCharacter(currentX, currentY, "pacman", "right")
        }
      }

      case KeyEvent.VK_W => {
        if (check(currentX, currentY - 1)) {
          view.removeCharacter(currentX, currentY)
          currentY = currentY - 1
          view.renderCharacter(currentX, currentY, "pacman", "up")
        }
      }

      case KeyEvent.VK_S => {
        if (check(currentX, currentY - 1)) {
          view.removeCharacter(currentX, currentY)
          currentY = currentY + 1
          view.renderCharacter(currentX, currentY, "pacman", "down")
        }
      }
      case _ =>
    }

  }

  override def keyTyped(e: KeyEvent): Unit = {}

  override def keyReleased(e: KeyEvent): Unit = {}

  private def check(x: Int, y: Int): Boolean = x>=0 && y>=0 && x<60 && y<30 //TODO passare le columns e le rows del playground

}

