package com.github.zjjfly.ps.chapter5

/**
  * Created by zjjfly on 2016/12/2.
  */
object ImplicitArg {
  def calcTax(amout: Float)(implicit rate: Float) = amout * rate

  def main(args: Array[String]): Unit = {
    //    import SimpleStateSalesTax.rate
    //    val amount = 100F
    //    println(s"Tax on $amount = ${calcTax(amount)}")
    import ComplicatedSalesTax.rate
    implicit val myStore = ComplicatedSalesTaxData(0.06F, false, 1010)
    val amount = 100F
    println(s"Tax on $amount = ${calcTax(amount)}")
  }
}

object SimpleStateSalesTax {
  implicit val rate: Float = 0.05F
}

case class ComplicatedSalesTaxData(baseRate: Float,
                                   isTaxHoliday: Boolean,
                                   storeId: Int)

object ComplicatedSalesTax {
  private def extraTaxRatesForStore(id: Int): Float = {
    0.1F
  }
  //隐式方法不能有参数除了隐式参数
  implicit def rate(implicit cstd: ComplicatedSalesTaxData): Float = {
    if (cstd.isTaxHoliday) 0.0F
    else cstd.baseRate + extraTaxRatesForStore(cstd.storeId)
  }
}
