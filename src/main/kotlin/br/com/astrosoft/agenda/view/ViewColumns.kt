package br.com.astrosoft.agenda.view

import br.com.astrosoft.agenda.model.beans.Pedido
import br.com.astrosoft.agenda.model.beans.ProdutoPedido
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
//
fun Grid<ProdutoPedido>.produtoCodigo() = addColumnString(ProdutoPedido::codigo) {
  this.setHeader("Cogigo")
}

fun Grid<ProdutoPedido>.produtoDescricao() = addColumnString(ProdutoPedido::descricao) {
  this.setHeader("Descrição")
}

fun Grid<ProdutoPedido>.produtoGrade() = addColumnString(ProdutoPedido::grade) {
  this.setHeader("Grade")
}

fun Grid<ProdutoPedido>.produtoRefFab() = addColumnString(ProdutoPedido::refFab) {
  this.setHeader("Ref Fab")
}

fun Grid<ProdutoPedido>.produtoBarCode() = addColumnString(ProdutoPedido::barcode) {
  this.setHeader("Código de barras")
}

fun Grid<ProdutoPedido>.produtoUnidade() = addColumnString(ProdutoPedido::unidade) {
  this.setHeader("UN")
}

fun Grid<ProdutoPedido>.produtoQuant() = addColumnInt(ProdutoPedido::qtPendente) {
  this.setHeader("Quant")
}

fun Grid<ProdutoPedido>.produtoValorUnit() = addColumnDouble(ProdutoPedido::custoUnitario) {
  this.setHeader("R$ Unit")
}

fun Grid<ProdutoPedido>.produtoAliqICMS() = addColumnDouble(ProdutoPedido::aliqICMS) {
  this.setHeader("Aliq ICMS")
}

fun Grid<ProdutoPedido>.produtoAliqIPI() = addColumnDouble(ProdutoPedido::aliqIPI) {
  this.setHeader("Aliq IPI")
}

fun Grid<ProdutoPedido>.produtoValorTotal() = addColumnDouble(ProdutoPedido::vlPendente) {
  this.setHeader("Valor")
}
