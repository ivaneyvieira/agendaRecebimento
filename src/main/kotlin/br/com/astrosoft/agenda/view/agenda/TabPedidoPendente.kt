package br.com.astrosoft.agenda.view.agenda

import br.com.astrosoft.agenda.model.beans.Pedido
import br.com.astrosoft.agenda.view.pedidoCodigoFor
import br.com.astrosoft.agenda.view.pedidoData
import br.com.astrosoft.agenda.view.pedidoDataPrevisao
import br.com.astrosoft.agenda.view.pedidoFornecedor
import br.com.astrosoft.agenda.view.pedidoLoja
import br.com.astrosoft.agenda.view.pedidoNumero
import br.com.astrosoft.agenda.view.pedidoValor
import br.com.astrosoft.agenda.viewmodel.agenda.IPedidoPendente
import br.com.astrosoft.agenda.viewmodel.agenda.PedidoPendenteTab
import br.com.astrosoft.framework.view.TabPanelGrid
import com.github.mvysny.karibudsl.v10.textField
import com.vaadin.flow.component.grid.Grid
import com.vaadin.flow.component.orderedlayout.HorizontalLayout
import com.vaadin.flow.component.textfield.TextField
import com.vaadin.flow.data.value.ValueChangeMode.TIMEOUT

class TabPedidoPendente(val viewModel: PedidoPendenteTab): TabPanelGrid<Pedido>(Pedido::class), IPedidoPendente {
  private lateinit var edtFiltro: TextField
  
  override fun HorizontalLayout.toolBarConfig() {
    edtFiltro = textField("Pesquisa") {
      valueChangeMode = TIMEOUT
      addValueChangeListener {
        viewModel.processaFiltro()
      }
    }
  }
  
  override fun Grid<Pedido>.gridPanel() {
    pedidoLoja()
    pedidoNumero()
    pedidoData()
    pedidoCodigoFor()
    pedidoFornecedor()
    pedidoDataPrevisao()
    pedidoValor()
  }
  
  override val label: String
    get() = "Pedidos Pendentes"
  
  override fun updateComponent() {
    viewModel.updateGridNota()
  }
  
  override fun updatePedidos(pedidos: List<Pedido>) {
    updateGrid(pedidos)
  }
  
  override fun filtro(): String = edtFiltro.value ?: ""
}