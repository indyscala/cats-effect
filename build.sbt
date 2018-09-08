lazy val baseSettings: Seq[Setting[_]] = Seq(
  scalaVersion       := "2.12.6",
  scalacOptions     ++= Seq(
    "-deprecation",
    "-encoding", "UTF-8",
    "-feature",
    "-language:higherKinds",
    "-language:implicitConversions", "-language:existentials",
    "-unchecked",
    "-Yno-adapted-args",
    "-Ywarn-numeric-widen",
    "-Ywarn-value-discard",
    "-Xfuture"
  ),
  resolvers += Resolver.sonatypeRepo("releases")
)

lazy val cats-effect = project.in(file("."))
  .settings(moduleName := "cats-effect")
  .settings(baseSettings: _*)
  .aggregate(core, slides)
  .dependsOn(core, slides)

lazy val core = project
  .settings(moduleName := "cats-effect-core")
  .settings(baseSettings: _*)


lazy val slides = project
  .settings(moduleName := "cats-effect-slides")
  .settings(baseSettings: _*)
  .settings(
    tutSourceDirectory := baseDirectory.value / "tut",
    tutTargetDirectory := baseDirectory.value / "../docs",
    watchSources ++= (tutSourceDirectory.value ** "*.html").get
  ).dependsOn(core)
  .enablePlugins(TutPlugin)