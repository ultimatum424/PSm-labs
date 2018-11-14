import java.util.*

class Notebook {
    
    private var dictionarySearch: HashMap<String, Int> = hashMapOf()

    /**
     * Добавление контакта в записную книжку поиска, и разбор контакта по символам.
     * При этом каждому новому символу добавляется ключ в HashMap или инкремент, если такой ключ уже есть.
     * @param [contact] имя контакта
     */
    fun addContact(contact: String){
        for (i in 1..contact.length){
            val subStr = contact.substring(0, i)
            if (!dictionarySearch.containsKey(subStr)){
                dictionarySearch.put(subStr, 1)
            } else {
               val value = dictionarySearch[subStr]!!.inc()
                dictionarySearch.put(subStr, value)
            }
        }
    }

    /**
     * Поиск количества совпадений
     * @param [str] искомая строка для поиска
     * @return найденное количество контактов начинающися с [str] строки
     */
    fun searchMatchCount(str: String): Int {
        return dictionarySearch[str] ?: return 0
    }

}