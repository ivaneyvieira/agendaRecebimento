package br.com.astrosoft.agenda.view.reports

import br.com.astrosoft.agenda.model.beans.ProdutoPedido
import br.com.astrosoft.framework.util.format
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder
import net.sf.dynamicreports.report.builder.DynamicReports
import net.sf.dynamicreports.report.builder.DynamicReports.col
import net.sf.dynamicreports.report.builder.DynamicReports.sbt
import net.sf.dynamicreports.report.builder.DynamicReports.stl
import net.sf.dynamicreports.report.builder.DynamicReports.type
import net.sf.dynamicreports.report.builder.column.ColumnBuilder
import net.sf.dynamicreports.report.builder.component.ComponentBuilder
import net.sf.dynamicreports.report.builder.subtotal.SubtotalBuilder
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment.CENTER
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment.LEFT
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment.RIGHT
import net.sf.jasperreports.engine.export.JRPdfExporter
import net.sf.jasperreports.export.SimpleExporterInput
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput
import java.io.ByteArrayOutputStream
import java.time.LocalDate
import java.time.LocalTime

class RelatorioProdutos(val listaProdutos: List<ProdutoPedido>) {
  val pedido = listaProdutos.firstOrNull()
  val codigoCol = col.column("Código", ProdutoPedido::codigo.name, type.stringType())
    .apply {
      this.setHorizontalTextAlignment(RIGHT)
      this.setFixedWidth(50)
    }
  val descricaoCol = col.column("Descrição", ProdutoPedido::descricao.name, type.stringType())
    .apply {
      this.setHorizontalTextAlignment(LEFT)
      //this.setFixedWidth(60 * 4)
    }
  val gradeCol = col.column("Grade", ProdutoPedido::grade.name, type.stringType())
    .apply {
      this.setHorizontalTextAlignment(CENTER)
      this.setFixedWidth(50)
    }
  val qtdeCol = col.column("Quant", ProdutoPedido::qtPendente.name, type.integerType())
    .apply {
      this.setHorizontalTextAlignment(RIGHT)
      this.setPattern("0")
      this.setFixedWidth(40)
    }
  val valorUnitarioCol = col.column("V. Unit", ProdutoPedido::custoUnitario.name, type.doubleType())
    .apply {
      this.setHorizontalTextAlignment(RIGHT)
      this.setPattern("#,##0.00")
      this.setFixedWidth(50)
    }
  val valorTotalCol = col.column("V. Total", ProdutoPedido::vlPendente.name, type.doubleType())
    .apply {
      this.setHorizontalTextAlignment(RIGHT)
      this.setPattern("#,##0.00")
      this.setFixedWidth(60)
    }
  val barcodeCol = col.column("Cód Barra", ProdutoPedido::barcode.name, type.stringType())
    .apply {
      this.setHorizontalTextAlignment(CENTER)
      this.setFixedWidth(80)
    }
  val unCol = col.column("Unid", ProdutoPedido::unidade.name, type.stringType())
    .apply {
      this.setHorizontalTextAlignment(CENTER)
      this.setFixedWidth(30)
    }
  val itemCol = col.column("Item", ProdutoPedido::item.name, type.integerType())
    .apply {
      this.setHorizontalTextAlignment(CENTER)
      this.setPattern("000")
      this.setFixedWidth(25)
    }
  
  private fun columnBuilder(): List<ColumnBuilder<*, *>> {
    return listOf(
      itemCol,
      barcodeCol,
      codigoCol,
      descricaoCol,
      gradeCol,
      unCol,
      qtdeCol,
      valorUnitarioCol,
      valorTotalCol
                 )
  }
  
  private fun titleBuider(): ComponentBuilder<*, *> {
    return verticalBlock {
      horizontalList {
        val dataAtual =
          LocalDate.now()
            .format()
        val horaAtual = LocalTime.now()
          .format()
        text("ENGECOPI   ${pedido?.sigla}   PEDIDO ${pedido?.numeroPedido}  -  ${pedido?.dataPedido?.format()}",
             LEFT)
        text("$dataAtual-$horaAtual", RIGHT, 100)
      }
      horizontalList {
        text("${pedido?.codigoFor} ${pedido?.fornecedor}")
      }
    }
  }
  
  private fun sumaryBuild(): ComponentBuilder<*, *>? {
    return verticalBlock {
    }
  }
  
  private fun pageFooterBuilder(): ComponentBuilder<*, *>? {
    return DynamicReports.cmp.verticalList()
  }
  
  private fun subtotalBuilder(): List<SubtotalBuilder<*, *>> {
    return listOf(
      sbt.text("Total R$", valorUnitarioCol),
      sbt.sum(valorTotalCol)
                 )
  }
  
  fun makeReport(): JasperReportBuilder? {
    val colunms = columnBuilder().toTypedArray()
    var index: Int = 1
    val itens =
      listaProdutos
        .sortedBy {it.codigo.toIntOrNull() ?: 0}
        .map {
          it.apply {
            item = index++
          }
        }
    return DynamicReports.report()
      .title(titleBuider())
      .setTemplate(Templates.reportTemplate)
      .columns(* colunms)
      .columnGrid(* colunms)
      .setDataSource(itens)
      .summary(sumaryBuild())
      .summary(pageFooterBuilder())
      .subtotalsAtSummary(* subtotalBuilder().toTypedArray())
      .setSubtotalStyle(stl.style()
                          .setPadding(2)
                          .setTopBorder(stl.pen1Point()))
      .pageFooter(DynamicReports.cmp.pageNumber()
                    .setHorizontalTextAlignment(RIGHT)
                    .setStyle(stl.style()
                                .setFontSize(8)))
  }
  
  companion object {
    fun processaRelatorio(listProdutos: List<ProdutoPedido>): ByteArray {
      val report = RelatorioProdutos(listProdutos).makeReport()
      val printList = listOf(report?.toJasperPrint())
      val exporter = JRPdfExporter()
      val out = ByteArrayOutputStream()
      exporter.setExporterInput(SimpleExporterInput.getInstance(printList))
      
      exporter.exporterOutput = SimpleOutputStreamExporterOutput(out)
      
      exporter.exportReport()
      return out.toByteArray()
    }
  }
}