package br.com.astrosoft.agenda.view.agenda

import br.com.astrosoft.agenda.model.beans.Pedido
import br.com.astrosoft.agenda.model.beans.ProdutoPedido
import br.com.astrosoft.agenda.view.pedidoCodigoFor
import br.com.astrosoft.agenda.view.pedidoData
import br.com.astrosoft.agenda.view.pedidoDataPrevisao
import br.com.astrosoft.agenda.view.pedidoFornecedor
import br.com.astrosoft.agenda.view.pedidoLoja
import br.com.astrosoft.agenda.view.pedidoNumero
import br.com.astrosoft.agenda.view.pedidoValor
import br.com.astrosoft.agenda.view.produtoAliqICMS
import br.com.astrosoft.agenda.view.produtoAliqIPI
import br.com.astrosoft.agenda.view.produtoBarCode
import br.com.astrosoft.agenda.view.produtoCodigo
import br.com.astrosoft.agenda.view.produtoDescricao
import br.com.astrosoft.agenda.view.produtoGrade
import br.com.astrosoft.agenda.view.produtoQuant
import br.com.astrosoft.agenda.view.produtoRefFab
import br.com.astrosoft.agenda.view.produtoUnidade
import br.com.astrosoft.agenda.view.produtoValorTotal
import br.com.astrosoft.agenda.view.produtoValorUnit
import br.com.astrosoft.agenda.viewmodel.agenda.IPedidoPendente
import br.com.astrosoft.agenda.viewmodel.agenda.PedidoPendenteTab
import br.com.astrosoft.framework.util.format
import br.com.astrosoft.framework.view.SubWindowForm
import br.com.astrosoft.framework.view.TabPanelGrid
import br.com.astrosoft.framework.view.addColumnButton
import com.github.mvysny.karibudsl.v10.textField
import com.vaadin.flow.component.Html
import com.vaadin.flow.component.grid.Grid
import com.vaadin.flow.component.grid.GridVariant.LUMO_COMPACT
import com.vaadin.flow.component.icon.VaadinIcon.BULLETS
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
    addColumnButton(BULLETS, "Produtos", "Produtos do pedido") {pedido ->
      showDialogProdutos(pedido)
    }
    pedidoLoja()
    pedidoNumero()
    pedidoData()
    pedidoCodigoFor()
    pedidoFornecedor()
    pedidoDataPrevisao()
    pedidoValor()
  }
  
  private fun showDialogProdutos(pedido: Pedido?) {
    pedido ?: return
    val listProdutos = pedido.produtos
    val form = SubWindowForm("Pedido: ${pedido.loja}-${pedido.numeroPedido}") {
      createGridProdutos(listProdutos)
    }
    form.open()
  }
  
  private fun createGridProdutos(listProdutos: List<ProdutoPedido>): Grid<ProdutoPedido> {
    val gridDetail = Grid(ProdutoPedido::class.java, false)
    return gridDetail.apply {
      addThemeVariants(LUMO_COMPACT)
      isMultiSort = false
      setItems(listProdutos)
      
      produtoCodigo()
      produtoDescricao()
      produtoGrade()
      produtoRefFab()
      produtoBarCode()
      produtoUnidade()
      produtoQuant()
      produtoValorUnit()
      produtoAliqICMS()
      produtoAliqIPI()
      produtoValorTotal().apply {
        val totalPedido =
          listProdutos.sumByDouble {it.vlPendente}
            .format()
        setFooter(Html("<b><font size=4>Total R$ &nbsp;&nbsp;&nbsp;&nbsp; ${totalPedido}</font></b>"))
      }
    }
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