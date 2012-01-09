package com.nokia.gate5.lectures

import org.junit.Test
import org.junit.Assert._

class BrokerTest {

  @Test
  def acceptsValue {
    val b = new Broker("init");
    b.start

    b ! "new message"

    retryOnError(10) {
      assertEquals("new message", b.value)
    }

    b ! None //exits broker
  }

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
