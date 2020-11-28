package br.com.astrosoft.agenda.viewmodel.agenda

class PedidoPendenteTab(val viewModel: AgendaDevolucaoViewModel) {
  val subView
    get() = viewModel.view.tabPedidoPendente
}

interface IPedidoPendente {

}