package br.com.astrosoft.agenda.model.beans

import br.com.astrosoft.agenda.model.saci
import br.com.astrosoft.framework.util.format
import java.time.LocalDate

class Pedido(val codigoFor: Int, val dataEntrega: LocalDate, val dataPedido: LocalDate, val fornecedor: String,
             val loja: Int, val numeroPedido: Int, val produtos: List<ProdutoPedido>) {
  fun filtroView(filtro: String): Boolean {
    return fornecedor.startsWith(filtro, ignoreCase = true) || codigoFor == filtro.toIntOrNull()
           || numeroPedido == filtro.toIntOrNull() || dataPedido.format() == filtro
  }
  
  val valorTotal = produtos.sumByDouble {it.vlPendente}
  
  companion object {
    fun findPedidos(): List<Pedido> {
      return saci.findProdutoPedido()
        .groupBy {Pair(it.loja, it.numeroPedido)}
        .mapNotNull {ent ->
          val produto = ent.value.firstOrNull() ?: return@mapNotNull null
          Pedido(codigoFor = produto.codigoFor, dataEntrega = produto.dataEntrega, dataPedido = produto.dataPedido,
                 fornecedor = produto.fornecedor, loja = produto.loja, numeroPedido = produto.numeroPedido,
                 produtos = ent.value)
        }
    }
  }
}