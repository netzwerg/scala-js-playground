package ch.netzwerg

import org.scalajs.dom
import org.scalajs.dom.document
import org.singlespaced.d3js.Ops._
import org.singlespaced.d3js.d3

import scala.scalajs.js
import scala.scalajs.js.JSConverters._
import scalatags.JsDom.all._

object BouncingLettersApp extends js.JSApp {

  val BallRadius = 50
  val BallOffset = 10
  val Height = 400

  def translate(x: Int, y: Int) = s"translate($x,$y)"

  def xPos(i: Int): Int = {
    BallRadius + (i * (2 * BallRadius + BallOffset))
  }

  def yPos = Height - BallRadius - 5

  def main(): Unit = {

    val inputField = input(
      `type` := "text",
      autofocus := true,
      placeholder := "Type here!"
    ).render

    document.body.appendChild(inputField)

    val svg = d3.select("body").append("svg").attr("width", "100%").attr("height", Height)

    inputField.onkeyup = (e: dom.Event) => {
      val data = inputField.value.map(c => c.toString.toUpperCase).seq

      svg.selectAll("*").remove()

      val balls = svg.selectAll("g").data(data.toJSArray)

      val ballsEnter = balls.enter().append("g")
        .attr("transform", (s: String, i: Int) => translate(xPos(i), yPos))

      def bounce: (String, Int) => Unit = (s, index) => {
        ballsEnter.filter((s: String, i: Int) => i == index).transition()
          .duration(300)
          .ease("cubic-out")
          .attr("transform", translate(xPos(index), BallRadius))
          .transition()
          .duration(500)
          .ease("cubic-in")
          .attr("transform", translate(xPos(index), yPos))
      }

      ballsEnter.on("mouseover", bounce)

      val colors = d3.scale.category20().range()
      ballsEnter.append("circle")
        .attr("r", BallRadius)
        .attr("fill", (s: String, i: Int) => colors(i))
        .attr("stroke", "black")

      ballsEnter.append("text")
        .style("font-size", "x-large")
        .attr("dy", ".35em")
        .attr("text-anchor", "middle")
        .text((s: String) => s)

    }

  }

}
