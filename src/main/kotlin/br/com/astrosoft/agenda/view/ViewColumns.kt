package br.com.astrosoft.agenda.view

import br.com.astrosoft.agenda.model.beans.Pedido
import br.com.astrosoft.framework.view.addColumnDouble
import br.com.astrosoft.framework.view.addColumnInt
import br.com.astrosoft.framework.view.addColumnLocalDate
import br.com.astrosoft.framework.view.addColumnString
import com.vaadin.flow.component.grid.Grid

fun Grid<Pedido>.pedidoLoja() = addColumnInt(Pedido::loja) {
  this.setHeader("Loja")
}

fun Grid<Pedido>.pedidoNumero() = addColumnInt(Pedido::numeroPedido) {
  this.setHeader("Pedido")
}

fun Grid<Pedido>.pedidoData() = addColumnLocalDate(Pedido::dataPedido) {
  this.setHeader("Data")
}

fun Grid<Pedido>.pedidoDataPrevisao() = addColumnLocalDate(Pedido::dataEntrega) {
  this.setHeader("Data Previsão")
}

fun Grid<Pedido>.pedidoCodigoFor() = addColumnInt(Pedido::codigoFor) {
  this.setHeader("Código")
}

fun Grid<Pedido>.pedidoFornecedor() = addColumnString(Pedido::fornecedor) {
  this.setHeader("Fornecedor")
}

fun Grid<Pedido>.pedidoValor() = addColumnDouble(Pedido::valorTotal) {
  this.setHeader("Valor")
}

