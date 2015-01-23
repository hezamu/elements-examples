name := "elements-example"

scalaVersion := "2.11.4"

seq(vaadinWebSettings: _*)

resolvers += "Vaadin add-ons repository" at "http://maven.vaadin.com/vaadin-addons"

// basic dependencies
libraryDependencies ++= Seq(
  "com.vaadin" % "vaadin-server" % "7.4.0.beta2",
  "com.vaadin" % "vaadin-client-compiled" % "7.4.0.beta2",
  "com.vaadin" % "vaadin-themes" % "7.4.0.beta2",
  "org.vaadin.addons" % "scaladin" % "3.1.0",
  "org.vaadin" % "elements" % "0.1.0",
  "org.ow2.asm" % "asm" % "5.0.2" % "container",  
  "org.ow2.asm" % "asm-commons" % "5.0.2" % "container",
  "javax.servlet" % "javax.servlet-api" % "3.0.1" % "provided",
  "org.eclipse.jetty" % "jetty-webapp" % "9.1.3.v20140225" % "container",
  "org.eclipse.jetty" % "jetty-annotations" % "9.1.3.v20140225" % "container"
)

libraryDependencies ++= Seq(
	//Add add-ons from the directory here. e.g.
	//"com.vaadin.addon" % "vaadin-charts" % "1.1.5"
)

// Settings for the Vaadin plugin widgetset compilation
// Widgetset compilation needs memory and to avoid an out of memory error it usually needs more memory:
javaOptions in compileVaadinWidgetsets := Seq("-Xss8M", "-Xmx512M", "-XX:MaxPermSize=512M")

vaadinOptions in compileVaadinWidgetsets := Seq("-logLevel", "DEBUG", "-strict")

// Compile widgetsets into the source directory (by default themes are compiled into the target directory)
target in compileVaadinWidgetsets := (sourceDirectory in Compile).value / "webapp" / "VAADIN" / "widgetsets"

// This makes possible to attach a remote debugger when development mode is started from the command line
// javaOptions in vaadinDevMode ++= Seq("-Xdebug", "-Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5005")
