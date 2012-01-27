package reactive.web.javascript

import JsTypes._

sealed trait HTMLElement extends JsStub {
  def focus(): JsExp[JsVoid]
}
