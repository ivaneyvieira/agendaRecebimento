package br.com.astrosoft.agenda.viewmodel.agenda

import br.com.astrosoft.agenda.model.beans.Pedido

class PedidoPendenteTab(val viewModel: AgendaDevolucaoViewModel) {
  val pedidos = mutableListOf<Pedido>()
  val subView
    get() = viewModel.view.tabPedidoPendente
  
  fun processaFiltro() {
    val filtro = subView.filtro()
    subView.updatePedidos(pedidos.filtroView(filtro))
  }
  
  fun updateGridNota() {
    pedidos.clear()
    pedidos.addAll(Pedido.findPedidos())
    processaFiltro()
  }
}

private fun List<Pedido>.filtroView(filtro: String): List<Pedido> {
  return this.filter {it.filtroView(filtro)}
}

interface IPedidoPendente {
  fun updatePedidos(pedidos: List<Pedido>)
  fun filtro(): String
}