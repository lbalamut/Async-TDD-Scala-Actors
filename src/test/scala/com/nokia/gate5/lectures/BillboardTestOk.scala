package com.nokia.gate5.lectures

import org.junit.Assert._

import java.lang.Thread.sleep
import TestUtils._

import org.junit.{After, Before, Test}

class BillboardTestOk {

  val b = new Billboard("init");

  @Before
  def startBillboard() {
    b.start
  }

  @After
  def stopBillboard() {
    b ! None
  }

  /**
   * timing issues
   */
  @Test
  def displaysMessage {
    b ! "new message"

    //    sleep(50) // flickering

    retryOnError(20) {
      assertEquals("new message", b.message)
    }
  }

  /**
   * false positive
   */
  @Test
  def rejectsTooLongMessage {
    b ! "way too too long message to accept"
    sleep(100)
    assertEquals("init", b.message)
  }


  /**
   * other party involved
   */
  @Test
  def keepsHistory {
    import Billboard.startTimer
    startTimer(b)

    b ! "new message"

    sleep(100)

    retryOnError(20) {
      assertTrue(b.history.contains("new message"))
    }
  }
}