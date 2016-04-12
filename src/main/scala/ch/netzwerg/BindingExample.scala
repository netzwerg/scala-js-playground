package ch.netzwerg

import com.thoughtworks.binding.Binding.{BindingSeq, Var, Vars}
import com.thoughtworks.binding.{Binding, dom}
import org.scalajs.dom.html.Button
import org.scalajs.dom.raw.Event
import org.scalajs.dom.{Node, document}

import scala.scalajs.js

object BindingExample extends js.JSApp {

  case class Contact(name: Var[String], email: Var[String])

  @dom
  def table(data: Vars[Contact]) = {
    <div>
      { addButton(data).each }
    </div>
      <table border="1" cellPadding="5">
        <thead>
          <tr>
            <th>Name</th>
            <th>E-mail</th>
            <th>Operation</th>
          </tr>
        </thead>
        <tbody>
          {for (contact <- data) yield {
          <tr>
            <td>
              {contact.name.each}
            </td>
            <td>
              {contact.email.each}
            </td>
            <td>
              <button
              onclick={event: Event =>
                contact.name := "Modified Name"}>
                Modify the name
              </button>
            </td>
          </tr>
        }}
        </tbody>
      </table>
  }

  @dom
  def addButton(data: Vars[Contact]): Binding[Button] = {
    val button: Button = <button
    onclick={event: Event =>
      data.get += Contact(Var("Foo Bar"), Var("foo.bar@gmail.com"))}>
      Add a contact
    </button>

    button.classList.add("row")

    button
  }

  def main(): Unit = {
    val data = Vars.empty[Contact]
    dom.render(document.body, table(data))
  }

}
