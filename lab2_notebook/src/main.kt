

fun main(args: Array<String>) {
    val notebook = Notebook()
    notebook.addContact("alexey")
    notebook.addContact("dima")
    notebook.addContact("albon")
    notebook.addContact("alex")
    print(notebook.searchMatchCount("ale"))
}