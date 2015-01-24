package org.vaadin.hezamu.elements

import vaadin.scala.server.ScaladinServlet
import javax.servlet.annotation.{ WebInitParam, WebServlet }
import com.vaadin.server.BootstrapListener
import com.vaadin.server.BootstrapPageResponse
import com.vaadin.server.BootstrapFragmentResponse
import com.vaadin.server.SessionInitEvent
import com.vaadin.server.SessionInitListener
import javax.servlet.ServletConfig

@WebServlet(urlPatterns = Array("/*"), initParams = Array(new WebInitParam(name = "ScaladinUI", value = "org.vaadin.hezamu.elements.ElementExampleUI")))
class ElementExampleServlet extends ScaladinServlet {
  // Maybe Scaladin should support a better way to inject script tags
  override def init(servletConfig: ServletConfig) {
    super.init(servletConfig)

    service.sessionInitListeners += {
      _.session.bootstrapPageListeners += {
        _.document.head.appendElement("script").attr("src", "VAADIN/bower_components/webcomponentsjs/webcomponents.js")
      }
    }
  }
}
