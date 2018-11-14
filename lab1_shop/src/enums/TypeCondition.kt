package enums

/**
 * Enum класс описывает усовиие товара в скидке
 * [REQUIRED] обязателен
 * [OPTIONAL] наличие одного из товаров этого типа
 * [OUTCAST] должен отсутствывать
 */
enum class TypeCondition {
    REQUIRED, OPTIONAL, OUTCAST
}