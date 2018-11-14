import enums.TypeCondition

/**
 * Класс описыват товар в скидке
 * @param [idProduct] ID товара
 * @param [condition] тип условия для скидки (наличие обязательно; наличие опционально; товар не участвует в акции)
 * @param [isDiscount] участвует ли этот товар в скидке
 */
data class ElementDiscount(
        val idProduct: Long,
        val condition: TypeCondition,
        val isDiscount: Boolean
)
