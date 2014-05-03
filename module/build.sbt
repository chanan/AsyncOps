name := "AsyncOps"

version := "0.1.1"

libraryDependencies ++= Seq(
	"signalJ" %% "signalj" % "0.1.4"
)

play.Project.playJavaSettings

resolvers += "release repository" at "http://chanan.github.io/maven-repo/releases/"

resolvers += "snapshot repository" at "http://chanan.github.io/maven-repo/snapshots/"

publishTo <<= version { (v: String) =>
  if (v.trim.endsWith("SNAPSHOT"))
    Some(Resolver.file("file",  new File( "../../maven-repo/snapshots" )) )
  else
    Some(Resolver.file("file",  new File( "../../maven-repo/releases" )) )
}

publishArtifact in(Compile, packageDoc) := false

publishMavenStyle := true