scalaVersion := "3.1.3"
organization := "dev.zio"
name         := "zio-quickstart-restful-webservice"

libraryDependencies ++= Seq(
  "dev.zio"       %% "zio"            % "2.0.1",
  "dev.zio"       %% "zio-json"       % "0.3.0-RC11",
  "dev.zio" %% "zio-logging"       % "2.0.0",
   "dev.zio" %% "zio-logging-slf4j" % "2.0.0",
   "org.slf4j" % "slf4j-simple" % "1.7.36",
  "io.d11"        %% "zhttp"          % "2.0.0-RC10",
  "io.getquill"   %% "quill-zio"      % "4.3.0",
  "io.getquill"   %% "quill-jdbc-zio" % "4.3.0",
  "com.h2database" % "h2"             % "2.1.214"
)
