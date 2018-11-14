class WordDistance(private val text: String) {

    private var wordsList = listOf<String>()

    init {
        wordsList = splitText()

    }

    /**
     * Делим текст [text] на слова
     * @return возращаем список слов
     */
    private fun splitText(): List<String>{
        return text.split(" ", ".", ",", ";", "(", ")", "'", "!", ignoreCase = true).filter { it != "" }
    }

    /**
     * Находим все слова [word] в списке слов [wordsList]
     * @return возвращаем спиосок позиций найденных [word] в [wordsList]
     */
    private fun getWordIndex(word: String) : List<Int>{
        return wordsList.mapIndexed { i, s -> if (s == word) i else null  }.filterNotNull()
    }

    /**
     * Находм максимальное и мнимальное растояние между заданными словами [firstWord] и [secondWord]
     * @return возвращаем пару значений, где первое значение минимальное расстояние, а второе максимальное
     */
    fun findMaxAndMinWordSpacing(firstWord: String, secondWord: String): Pair<Int, Int> {
        var max: Int = Int.MIN_VALUE
        var min: Int = Int.MAX_VALUE
        val wordListIndex1 = getWordIndex(firstWord)
        val wordListIndex2 = getWordIndex(secondWord)
        for (i in wordListIndex1){
            for (j in wordListIndex2){
                val a = Math.abs(i - j)
                if (a > max) max = a
                if (a < min) min = a
            }
        }
        max--
        min--
        return Pair(min, max)
    }
}