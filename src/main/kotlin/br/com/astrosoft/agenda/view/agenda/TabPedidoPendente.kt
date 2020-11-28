package br.com.astrosoft.agenda.view.agenda

import br.com.astrosoft.agenda.model.beans.Pedido
import br.com.astrosoft.agenda.viewmodel.agenda.IPedidoPendente
import br.com.astrosoft.agenda.viewmodel.agenda.PedidoPendenteTab
import br.com.astrosoft.framework.view.TabPanelGrid
import com.vaadin.flow.component.grid.Grid
import com.vaadin.flow.component.orderedlayout.HorizontalLayout

class TabPedidoPendente(val viewModel: PedidoPendenteTab):  TabPanelGrid<Pedido>(Pedido::class), IPedidoPendente {
  override fun HorizontalLayout.toolBarConfig() {
    TODO("Not yet implemented")
  }
  
  override fun Grid<Pedido>.gridPanel() {
    TODO("Not yet implemented")
  }
  
  override val label: String
    get() = TODO("Not yet implemented")
  
  override fun updateComponent() {
    TODO("Not yet implemented")
  }
}