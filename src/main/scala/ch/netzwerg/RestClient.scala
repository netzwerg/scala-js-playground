package ch.netzwerg

import org.scalajs.dom
import org.scalajs.dom.document
import upickle.default._

import scala.scalajs.js.JSApp
import scalatags.JsDom.all._

object RestClient extends JSApp {

  val Url = "https://api.github.com/repos/netzwerg/paleo/contributors"

  case class Contributor(login: String)

  override def main() = {

    import dom.ext._
    import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue

    Ajax.get(Url).onSuccess { case response =>
      val contributors = read[Seq[Contributor]](response.responseText)
      val list = ul(for (contributor <- contributors) yield li(contributor.login))
      document.body.appendChild(list.render)
    }

  }

}