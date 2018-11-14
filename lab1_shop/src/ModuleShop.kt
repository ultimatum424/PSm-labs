import enums.TypeDiscount

/**
 * Класс с расчётом скидки в магазине
 * @param [shopBasket] список товаров в корзине
 * @param [listProduct] список товаров в магазине
 * @param [listDiscounts] список скидок в магазине
 */
class ModuleShop(private var shopBasket: MutableList<Long>,
                 private val listProduct: List<Product>,
                 private val listDiscounts: List<Discount>) {


    private var useDiscounts: MutableList<Discount> = mutableListOf()
    private var i = 0
    private val ZERO: Long = 0

    private var sumWithoutDiscount = shopBasket.map { getProductForId(it).price }.sum()

    /**
     * Поиск доступных скидок
     */
    private fun findAllAvailableDiscount(){
        while (i < listDiscounts.size){
            if (checkProductsForDiscount(listDiscounts[i])){
                listDiscounts[i].getListDiscountPriceProductId().map { shopBasket.remove(it) }
                useDiscounts.add(listDiscounts[i])
                if (listDiscounts[i].isReusable){
                    i--
                }
            }
            i++
        }
    }

    /**
     * Расчёт финальной стоимости корзины
     * @return сумма корзины
     */
    private fun countFinalPrice(): Float {
        var sum: Float = shopBasket.map { getProductForId(it).price }.sum()
        for (i in useDiscounts) {
            val priceList = i.getListDiscountPriceProductId().map { getProductForId(it).price }
            when (i.typeDiscount) {
                TypeDiscount.FOR_EACH -> {
                    val totalPriceGroup = priceList.map { it * i.getPercentDiscount() }.sum()
                    sum += totalPriceGroup
                }
                TypeDiscount.FOR_GROUP -> {
                    val totalPriceGroup = priceList.sum() * i.getPercentDiscount()
                    sum += totalPriceGroup
                }
                TypeDiscount.FOR_ALL -> {
                    val totalPriceGroup = priceList.sum()
                    sum += totalPriceGroup
                    sum *= i.getPercentDiscount()
                }
            }
        }
        return sum
    }

    /**
     * Поиск товара по id
     * @param [id] ID искомого товара
     * @return требуемый товар
     */
    private fun getProductForId(id: Long): Product{
        return listProduct.find { it.id == id }!!
    }

    /**
     * Проверка на возможность применения данной скидки
     * @param [discount] проверяемая скидка
     */
    private fun checkProductsForDiscount(discount: Discount): Boolean {
       val  tempListProducts = shopBasket.filterNot {it in discount.getListOutcastProductId() }
        if (tempListProducts.size < discount.getListCountProductForDiscount()){
            return false
        }
        if (!discount.getListRequiredProductId().all { it in tempListProducts }){
            return false
        }
        if (!discount.getListOptionalProductId().any { it in tempListProducts || it == ZERO}){
            return false
        }
        if (discount.isDependencyNextDiscount) {
            i++
            val res = checkProductsForDiscount(listDiscounts[i])
            if (!res) i--
        }
        return true
    }

    /**
     * Расчёт стоимости товаров с учётом скидок
     * @return суммарная стоимость
     */
    fun discountCalculator(): Float{
        findAllAvailableDiscount()
        return countFinalPrice()
    }

    /**
     * Расчёт стоимости товаров без скидок
     * @return суммарная стоимость
     */
    fun getSumWithoutDiscount(): Float {
        return sumWithoutDiscount
    }

}