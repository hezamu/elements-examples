package org.vaadin.hezamu.elements

import vaadin.scala.server.ScaladinServlet
import javax.servlet.annotation.{ WebInitParam, WebServlet }
import com.vaadin.server.BootstrapListener
import com.vaadin.server.BootstrapPageResponse
import com.vaadin.server.BootstrapFragmentResponse
import com.vaadin.server.SessionInitEvent
import com.vaadin.server.SessionInitListener

// Nasty hack until Scaladin supports @Javascript or some other way to inject script tags
class BootstrapListenerImplementation extends BootstrapListener {
  override def modifyBootstrapPage(response: BootstrapPageResponse) {
    response.getDocument.head.appendElement("script").attr("src", "VAADIN/bower_components/webcomponentsjs/webcomponents.js")
  }

  override def modifyBootstrapFragment(response: BootstrapFragmentResponse) {
    // nothing to do
  }
}

@WebServlet(urlPatterns = Array("/*"), initParams = Array(new WebInitParam(name = "ScaladinUI", value = "org.vaadin.hezamu.elements.ElementExampleUI")))
class ElementExampleServlet extends ScaladinServlet {
  override def servletInitialized {
    super.servletInitialized

    getService.addSessionInitListener(new SessionInitListener() {
      def sessionInit(event: SessionInitEvent) {
        event.getSession.addBootstrapListener(new BootstrapListenerImplementation())
      }
    })
  }
}
