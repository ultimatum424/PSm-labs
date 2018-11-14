import enums.TypeCondition
import enums.TypeDiscount

fun main(args: Array<String>) {

    val listProduct = listOf<Product>(
            Product(IDGenerator.generateId(), 10f),
            Product(IDGenerator.generateId(), 20f),
            Product(IDGenerator.generateId(), 30f),
            Product(IDGenerator.generateId(), 40f),
            Product(IDGenerator.generateId(), 50f),
            Product(IDGenerator.generateId(), 60f),
            Product(IDGenerator.generateId(), 70f),
            Product(IDGenerator.generateId(), 80f)
    )

    val shopBasket = mutableListOf(listProduct[0].id, listProduct[2].id, listProduct[2].id, listProduct[3].id,
            listProduct[5].id, listProduct[6].id, listProduct[1].id)

    val dis = Discount(IDGenerator.generateId(), listOf(
            ElementDiscount(listProduct[0].id, TypeCondition.REQUIRED, true),
            ElementDiscount(listProduct[6].id, TypeCondition.OUTCAST, false),
            ElementDiscount(listProduct[1].id, TypeCondition.REQUIRED, true),
            ElementDiscount(listProduct[3].id, TypeCondition.OPTIONAL, false),
            ElementDiscount(listProduct[5].id, TypeCondition.OPTIONAL, false)), false, TypeDiscount.FOR_EACH, true, 0.8f)

    val dis3 = Discount(IDGenerator.generateId(), listOf(
            ElementDiscount(listProduct[5].id, TypeCondition.REQUIRED, true),
            ElementDiscount(0, TypeCondition.OPTIONAL, false)), false, TypeDiscount.FOR_EACH, true, 0.9f)

    val md = ModuleShop(shopBasket, listProduct, listOf(dis, dis3))
    val sumWithDiscount = md.discountCalculator()
    val sumWithoutDiscount = md.getSumWithoutDiscount()
    println("Сумма без учёта скидок: $sumWithoutDiscount")
    println("Сумма с учётом скидок: $sumWithDiscount")
}