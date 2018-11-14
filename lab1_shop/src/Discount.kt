import enums.TypeCondition
import enums.TypeDiscount

/**
 * Класс описывает скидку в магазине
 * @param [id] ID скидки
 * @param [listProduct] список  товаров участвующий в скидке
 * @param [isDependencyNextDiscount] завист ли скидка от следующей
 * @param  [typeDiscount] тип скидки (для каждого товара; для группу товаров; для всего заказа)
 * @param [isReusable] может ли скидка быть многоразовой
 * @param [percent] процент скидки
 */
class Discount(
        val id: Long,
        val listProduct: List<ElementDiscount>,
        val isDependencyNextDiscount: Boolean,
        val typeDiscount: TypeDiscount,
        val isReusable: Boolean,
        private val percent: Float){

    fun getListOutcastProductId(): List<Long>{
        val listOutcast = listProduct.filter { it.condition == TypeCondition.OUTCAST }
        return listOutcast.map { it.idProduct }
    }
    fun getListRequiredProductId(): List<Long>{
        val listOutcast = listProduct.filter { it.condition == TypeCondition.REQUIRED }
        return listOutcast.map { it.idProduct }
    }

    fun getListOptionalProductId(): List<Long>{
        val listOutcast = listProduct.filter { it.condition == TypeCondition.OPTIONAL }
        return listOutcast.map { it.idProduct }
    }

    fun getListCountProductForDiscount(): Int {
        if (getListOptionalProductId().isNotEmpty()){
            return getListRequiredProductId().size + 1
        }
        return getListRequiredProductId().size
    }

    fun getListDiscountPriceProductId() : List<Long> = listProduct.filter { it.isDiscount }.map { it.idProduct }

    fun getPercentDiscount(): Float = 1 - percent
}
