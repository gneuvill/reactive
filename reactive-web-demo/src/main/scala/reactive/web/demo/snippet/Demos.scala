package reactive.web.demo.snippet

import scala.xml.{ NodeSeq, Text }

import reactive._
import web._
import html._

import net.liftweb.util._
import Helpers._

object Demos {
  def eventSourceInput(eventSource: EventSource[String])(implicit o: Observing): NodeSeq = {
    val text = TextInput()
    val button = Button("Fire")(eventSource fire text.value.now)
    <xml:group>{ text.render } { button.render }</xml:group>
  }

  def eventStreamOutput(eventStream: EventStream[String]): NodeSeq = Div {
    lazy val events = SeqSignal(
      eventStream.foldLeft(List[String]())((list, event) => event :: list).hold(Nil)
    )
    events.now map { e => RElem(<p>Fired: '{ e }'</p>) } signal
  }.render

  def varInput(v: Var[String])(implicit o: Observing): NodeSeq = {
    val textInput = TextInput(v)
    textInput.value updateOn textInput.keyUp
    textInput.render
  }

  def signalOutput(signal: Signal[String]): NodeSeq = Span {
    signal map Text
  }.render
}

