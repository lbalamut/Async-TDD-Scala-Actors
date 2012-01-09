package com.nokia.gate5.lectures

import actors.Actor

class Broker(var value: String = "") extends Actor {

  def act() {
    loop {
      react {
        case i: String => {
          value = i
        }
        case None => exit()
      }
    }
  }
}
