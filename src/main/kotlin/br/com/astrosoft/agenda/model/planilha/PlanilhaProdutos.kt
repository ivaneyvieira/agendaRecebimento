package br.com.astrosoft.agenda.model.planilha

import br.com.astrosoft.agenda.model.beans.ProdutoPedido
import br.com.astrosoft.framework.model.Planilha
import com.github.nwillc.poink.workbook
import org.apache.commons.io.output.ByteArrayOutputStream
import org.apache.poi.ss.usermodel.FillPatternType
import org.apache.poi.ss.usermodel.IndexedColors
import org.apache.poi.ss.usermodel.VerticalAlignment
import br.com.astrosoft.framework.util.format

class PlanilhaProdutos: Planilha() {
  private val campos: List<Campo<*, ProdutoPedido>> = listOf(
    CampoString("Loja") {sigla},
    CampoString("Data do Pedido") {dataPedido.format()},
    CampoInt("Código Fornecedor") {codigoFor},
    CampoString("Fornecedor") {fornecedor},
    CampoString("Código de barras") {barcode},
    CampoString("Código") {codigo},
    CampoString("Descrição") {descricao},
    CampoString("Grade") {grade},
    CampoString("UN") {unidade},
    CampoInt("Quant") {qtPendente},
    CampoNumber("R$ Unit") {custoUnitario},
    CampoNumber("R$ Total") {vlPendente},
                                                        )
  
  fun grava(listaBean: List<ProdutoPedido>): ByteArray {
    val wb = workbook {
      val headerStyle = cellStyle("Header") {
        fillForegroundColor = IndexedColors.GREY_25_PERCENT.index
        fillPattern = FillPatternType.SOLID_FOREGROUND
        this.verticalAlignment = VerticalAlignment.TOP
      }
      val rowStyle = cellStyle("Row") {
        this.verticalAlignment = VerticalAlignment.TOP
      }
      val sheet = sheet("Produtos") {
        val headers = campos.map {it.header}
        row(headers, headerStyle)
        listaBean.forEach {bean ->
          val valores = campos.map {it.produceVakue(bean)}
          row(valores, rowStyle)
        }
      }
      
      campos.forEachIndexed {index, _ ->
        sheet.autoSizeColumn(index)
      }
    }
    val outBytes = ByteArrayOutputStream()
    wb.write(outBytes)
    return outBytes.toByteArray()
  }
}
