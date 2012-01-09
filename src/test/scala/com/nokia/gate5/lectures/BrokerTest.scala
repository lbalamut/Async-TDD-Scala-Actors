package com.nokia.gate5.lectures

import org.junit.Test
import org.junit.Assert._


class BrokerTest {

  @Test
  def acceptsValue {
    val b = new Broker("init");
    b.start

    b ! "new message"

    Thread.sleep(100)

    assertEquals("new message", b.value)

    b ! None //exits broker
  }
}
