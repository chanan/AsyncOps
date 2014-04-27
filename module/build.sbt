name := "AsyncOps"

version := "0.1.0-SNAPSHOT"

libraryDependencies ++= Seq(
	"signalJ" %% "signalj" % "0.1.3"
)

play.Project.playJavaSettings

resolvers += "release repository" at "http://chanan.github.io/maven-repo/releases/"

resolvers += "snapshot repository" at "http://chanan.github.io/maven-repo/snapshots/"
