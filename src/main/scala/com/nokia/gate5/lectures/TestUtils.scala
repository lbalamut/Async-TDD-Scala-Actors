package com.nokia.gate5.lectures

object TestUtils {

  def retryOnError(count: Int)(body: => Unit) = {
    var retriesLeft = count
    while (retriesLeft > 0) {
      try {
        retriesLeft = retriesLeft - 1;
        body
        retriesLeft = 0
      } catch {
        case ae: AssertionError => {
          Thread.sleep(10)
          if (retriesLeft <= 0) throw ae
        }
      }
    }
  }
}