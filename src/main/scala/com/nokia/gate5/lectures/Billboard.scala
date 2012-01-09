package com.nokia.gate5.lectures

import actors.Actor
import collection.mutable.ListBuffer
import actors.Actor._
import java.util.Date
import java.lang.Thread.sleep


object Billboard {
  def startTimer(b: Billboard) {
    actor {
      loop {
        b ! new Date()
      }
    }
  }
}

class Billboard(var message: String = "") extends Actor {

  var history = ListBuffer.empty[String]

  def act() {
    loop {
      react {
        case message: String => {
          processMessage()
          displayMessage(message)
        }
        case date: Date => displayMessage(date.toString)
        case None => exit()
      }
    }
  }

  private def displayMessage(i: String) {
    //BUG
//    if (i.length() < 20) {
          if (i.length() != 20) {
      history += message
      message = i
    }
  }

  private def processMessage() {
    sleep((math.random * 100).asInstanceOf[Long])
  }
}
