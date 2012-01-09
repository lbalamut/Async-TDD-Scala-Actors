package com.nokia.gate5.lectures

import org.junit.Assert._

import actors.Actor._
import java.util.Date
import java.lang.Thread.sleep
import org.junit.{After, Before, Test}
import com.nokia.gate5.lectures.TestUtils._
import Billboard.startTimer

class BillboardTest {

  val b = new Billboard("init");

  @Before
  def startBillboard() {
    b.start
  }

  @After
  def stopBillboard() {
    b ! None
  }

  @Test
  def displaysMessage {
    b ! "new message"
    assertEquals("new message", b.message)
  }

  @Test
  def rejectsTooLongMessage {
    b ! "way too too long message to accept"
    assertEquals("init", b.message)
  }

  @Test
  def keepsHistory {

    startTimer(b)

    b ! "new message"

    sleep(200)

    retryOnError(20) {
      assertEquals("new message", b.message)
    }
  }
}