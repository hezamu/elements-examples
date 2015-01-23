package org.vaadin.hezamu.elements

import vaadin.scala.server.ScaladinServlet
import javax.servlet.annotation.{WebInitParam, WebServlet}

@WebServlet(urlPatterns = Array("/*"), initParams = Array(new WebInitParam(name = "ScaladinUI", value ="org.vaadin.hezamu.elements.ElementExampleUI")))
class ElementExampleServlet extends ScaladinServlet