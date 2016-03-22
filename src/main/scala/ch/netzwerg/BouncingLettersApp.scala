package ch.netzwerg

import org.singlespaced.d3js.Ops._
import org.singlespaced.d3js.d3

import scala.scalajs.js

object BouncingLettersApp extends js.JSApp {

  def main(): Unit = {

    val height = 400

    val svg = d3.select("body").append("svg").attr("width", "100%").attr("height", height)
    val data = js.Array("H", "E", "L", "L", "O")
    val text = svg.selectAll("text").data(data)

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
        .attr("transform", s"translate(0,-30)")
        .transition()
        .duration(500)
        .ease("cubic-in")
        .attr("transform", s"translate(0,0)")
    }

  }

}
