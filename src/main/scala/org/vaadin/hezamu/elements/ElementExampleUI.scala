package org.vaadin.hezamu.elements

import vaadin.scala._
import org.vaadin.elements._
import elemental.json.JsonArray
import com.vaadin.ui.JavaScriptFunction
import java.util.Optional

object Implicits {
  implicit class JavaOptional2Option[T](o: Optional[T]) {
    def toOption = if (o.isPresent) Some(o.get) else None
  }
}

/**
 * Vaadin Elements demos implemented with Scaladin.
 *
 * @author henri@vaadin.com
 */
class ElementExampleUI extends UI(title = "elements-example", theme = "valo") {
  import Implicits._

  content = new TabSheet {
    addTab(HTML5InputsDemo, "HTML5 inputs")
    addTab(PaperElementsDemo, "Paper elements")
  }

  def HTML5InputsDemo = new CssLayout {
    val root: Element = ElementIntegration getRoot this.p
    val input = Elements create "input"
    root appendChild input

    input.bindAttribute("value", "change") // Bind an event listener

    input.addEventListener("change", new JavaScriptFunction {
      override def call(e: JsonArray) =
        Notification show s"Value changed to ${input.getAttribute("value")}"
    })

    // Set type and value to generate an event
    input.setAttribute("type", "range")
    input.setAttribute("value", "0")
  }

  def PaperElementsDemo = new CssLayout {
    lazy val root: Root = ElementIntegration.getRoot(button.p)

    val button = add(Button("Click to modify my styles", { e =>
      root.querySelector("span.v-button-wrap").toOption foreach { elem =>
        if (elem.hasAttribute("style")) elem.removeAttribute("style")
        else elem.setAttribute("style", "color: red")
      }
    }))

    root.fetchDom(null) // Fetch dom so that it's available when clicking
  }

  def ModifyingHTMLDemo = new CssLayout {
    // TODO
  }
}
