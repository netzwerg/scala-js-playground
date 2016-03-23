package ch.netzwerg

import org.scalajs.dom
import org.scalajs.dom.document
import org.singlespaced.d3js.Ops._
import org.singlespaced.d3js.d3

import scala.scalajs.js
import scala.scalajs.js.JSConverters._
import scalatags.JsDom.all._

object BouncingLettersApp extends js.JSApp {

  def main(): Unit = {

    val height = 400

    val inputField = input(
      `type` := "text",
      autofocus := true,
      placeholder := "Type here!"
    ).render

    document.body.appendChild(inputField)

    val svg = d3.select("body").append("svg").attr("width", "100%").attr("height", height)

    inputField.onkeyup = (e: dom.Event) => {
      val data = inputField.value.map(c => c.toString).seq

      svg.selectAll("*").remove()

      val text = svg.selectAll("text").data(data.toJSArray)

      text.enter().append("text")
        .style("font-size", "x-large")
        .attr("class", (s: String, i: Int) => i)
        .attr("text-anchor", "start")
        .attr("y", height / 2)
        .attr("dx", (s: String, i: Int) => (i + 1) + "em")
        .text((s: String) => s)
        .on("mouseover", bounce)

      def bounce: (String, Int) => Unit = (s, index) => {
        text.filter((s: String, i: Int) => i == index).transition()
          .duration(300)
          .ease("cubic-out")
          .attr("transform", "translate(0,-30)")
          .transition()
          .duration(500)
          .ease("cubic-in")
          .attr("transform", "translate(0,0)")
      }
    }

  }

}
