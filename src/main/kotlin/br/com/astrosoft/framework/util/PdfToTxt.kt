package br.com.astrosoft.framework.util

import com.itextpdf.text.pdf.PdfReader
import com.itextpdf.text.pdf.parser.PdfReaderContentParser
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy
import java.io.IOException

fun pdfToText(pdf: String): String {
  val text = StringBuffer()
  
  try {
    val reader = PdfReader(pdf)
    val parser = PdfReaderContentParser(reader)
    //val out = PrintWriter(FileOutputStream(txt))
    for(i in 1..reader.numberOfPages) {
      
      val strategy = parser.processContent(i, TextExtractionStrategy())
      text.append(strategy.resultantText)
    }
    val resultText = text.toString()
    
    
    return resultText
  } catch(e: IOException) {
    // TODO Auto-generated catch block
    e.printStackTrace();
  }
  return ""
}

fun main() {
  val text = pdfToText("/home/ivaneyvieira/pedido_venda_286910.pdf")
  println(text)
  val text2 = pdfToText("/home/ivaneyvieira/tigre.pdf")
  println(text2)
}


class TextExtractionStrategy : SimpleTextExtractionStrategy(){
  override fun endTextBlock() {
    super.endTextBlock()
    appendTextChunk("\t")
  }
}